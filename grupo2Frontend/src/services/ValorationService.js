import httpClient from "../http-common";

export const getValorationsByIdProducto = async (idProducto) => {
    try{
        const response = await httpClient.get(`/public/producto/${idProducto}`);
        console.log(response.data)
        return response.data;
    }
    catch(error){
        console.log("Error al obtener valoraciones por idProducto: ", error.message);
    }
}

export const getPromedioValoracionProducto = async (idProducto) => {
    try{
        const response = await httpClient.get(`/public/valoracionProducto/${idProducto}`);
        return response.data;
    }
    catch(error){
        console.log("Error al obtener promedio de valoraciones por idProducto: ", error.message);
    }
}

export const createValoracion = async (valoracionData) => {
    try {
        const response = await httpClient.post('/valoracion/', valoracionData);
        return response.data;
    } catch (error) {
        console.log("Error al crear la valoración: ", error.message);
    }
};
