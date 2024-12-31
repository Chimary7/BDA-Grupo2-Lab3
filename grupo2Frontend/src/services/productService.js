import httpClient from "../http-common";

export const getAll = async () => {
    try{
        const response = await httpClient.get("/public/productos");
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

export const getProductCategory = async (idcategory) => {
    try{
        const response = await httpClient.get(`/public/productos/categoria/${idcategory}`);
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