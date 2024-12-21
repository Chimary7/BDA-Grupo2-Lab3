package com.example.grupo2backend.Services;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.grupo2backend.Entities.Cliente;
import com.example.grupo2backend.JWT.JWTUtil;
import com.example.grupo2backend.Repositories.ClienteRepository;
import com.example.grupo2backend.dto.login;
import com.example.grupo2backend.dto.loginResponse;
import jakarta.servlet.http.Cookie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    @Autowired
    private ClienteRepository clienteRepository;

    public loginResponse Login(login log){
        Cliente clienteEntity = clienteRepository.findByEmail(log.getEmail());
        if (clienteEntity == null){
            throw new IllegalStateException("The name or password is incorrect");
        }
        if (!log.getPassword().equals(clienteEntity.getPassword())){
            throw new IllegalStateException("The name or password is incorrect");
        }

        return loginResponse.builder()
                .token(JWTUtil.createToken(log.getEmail(), clienteEntity.getRole()))
                .idUser(clienteEntity.getId())
                .build();
    }

    public ResponseEntity<Object> Register(Cliente cliente){
        try{
            clienteRepository.save(cliente);
            return new ResponseEntity<>("se ingreso correctamente el cliente", HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>("Error al ingresar el cliente" + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public String verify(Cookie[] cookies){
        if (cookies != null){
            for (Cookie cookie: cookies){
                if (cookie.getName().equals("JWT")){
                    try{
                        String token = cookie.getValue();
                        DecodedJWT decodedJWT = JWTUtil.verifyToken(token);
                        return decodedJWT.getClaim("rol").asString();
                    } catch (JWTVerificationException e){
                        throw new IllegalStateException("JWT invalid");
                    }

                }
            }
            throw new IllegalStateException("JWT not found");
        }
        throw new IllegalStateException("cookies not found");
    }
}
