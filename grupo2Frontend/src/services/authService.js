import httpClient from "../http-common";

export const auth = async (to, from, next) => {
  try {
    const response = await httpClient.post("/auth/verify", {
      withCredentials: true,
    });

    if (response.status === 200) {
      const userRole = response.data.role;

      if (to.meta.roles && !to.meta.roles.includes(userRole)) {
        console.log("usuario no autorizado, redirigiendo");
        next({name: "Login"});
      }
      console.log("Usuario autenticado");
      next();
    } else {
      console.log("No autenticado");
      next({name: "login"});
    }
  } catch (error) {
    console.log("Error de autenticación:", error);
    next({name: "Login"});
  }
};
export const verifyToken = async () => {
    const response = await httpClient.post("/auth/verify", {
      withCredentials: true,
    });
    if (response.status === 200) {
      console.log("Usuario autenticado");
      return response.data;
    } else {
      return false;
    }
};

export const logout = async () => {
  try {
    const response = await httpClient.post("/auth/logout", {
      withCredentials: true,
    });
    if (response.status === 200) {
      console.log("Usuario deslogueado");
      return true;
    } else {
      console.log("Error al desloguear");
      return false;
    }
  } catch (error) {
    console.log("Error al desloguear", error);
    return false;
  }
};