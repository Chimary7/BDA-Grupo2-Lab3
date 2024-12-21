package com.example.grupo2backend.Controllers;

import com.example.grupo2backend.Entities.Cliente;
import com.example.grupo2backend.Services.AuthenticationService;
import com.example.grupo2backend.Services.ClienteService;
import com.example.grupo2backend.dto.login;
import com.example.grupo2backend.dto.loginResponse;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    @Autowired
    AuthenticationService authenticationService;

    @Autowired
    ClienteService clienteService;

    @PostMapping("/login")
    public ResponseEntity<Map<String,String>> login(@RequestBody login log, HttpServletResponse response){
        loginResponse Response = authenticationService.Login(log);
        String token = Response.getToken();
        String cookieValue = "JWT=" + token + "; HttpOnly; Secure; SameSite=None; Path=/; Max-Age=" + (60 * 60 * 24);
        HashMap<String, String> message = new HashMap<>();
        response.addHeader("Set-Cookie", cookieValue);
        message.put("id_user", Response.getIdUser());
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody Cliente user) {
        return new ResponseEntity<>(authenticationService.Register(user), HttpStatus.CREATED);
    }

    @PostMapping("/logout")
    public ResponseEntity<Map<String, Boolean>> logout(HttpServletResponse response){
        Cookie jwtCookie = new Cookie("JWT", null);
        jwtCookie.setHttpOnly(true);
        jwtCookie.setSecure(true);
        jwtCookie.setPath("/");
        jwtCookie.setMaxAge(0);

        response.addCookie(jwtCookie);

        HashMap<String, Boolean> message = new HashMap<>();
        message.put("success", true);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @PostMapping("/verify")
    public ResponseEntity<Map<String, Object>> verifyToken(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        String role = authenticationService.verify(cookies);
        HashMap<String, Object> message = new HashMap<>();
        message.put("success", true);
        message.put("role", role);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
