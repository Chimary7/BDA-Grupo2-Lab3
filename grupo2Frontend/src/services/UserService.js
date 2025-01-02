import httpClient from "../http-common";

export const createClient = async (user) => {
    try{
        const response = await httpClient.post("/auth/register", user);
        return {data: response.data, status: response.status};
    }
    catch (error){
        if(error.response){
            console.log("Error en la respuesta del servidor: ", error.response.data);
            return {data: error.response.data, status: error.response.status};
        }
        else if (error.request){
            console.log("No se recibe respuesta del servidor: ", error.request);
        }
        else{
            console.log("Error al enviar la petición: ", error.message);
        }
    }
}

export const login = async (user) => {
    try{
        const response = await httpClient.post("/auth/login", user);
        return {data: response.data, status: response.status};
    }
    catch (error){
        if(error.response){
            console.log("Error en la respuesta del servidor: ", error.response.data);
            return {data: error.response.data, status: error.response.status};
        }
        else if (error.request){
            console.log("No se recibe respuesta del servidor: ", error.request);
        }
        else{
            console.log("Error al enviar la petición: ", error.message);
        }
    }
}

export const getUser = async (idCliente) => {
    try{
        const response = await httpClient.get(`/cliente/${idCliente}`);
        return {data: response.data, status: response.status};
    }
    catch (error){
        if(error.response){
            console.log("Error en la respuesta del servidor: ", error.response.data);
            return {data: error.response.data, status: error.response.status};
        }
        else if (error.request){
            console.log("No se recibe respuesta del servidor: ", error.request);
        }
        else{
            console.log("Error al enviar la petición: ", error.message);
        }
    }
}