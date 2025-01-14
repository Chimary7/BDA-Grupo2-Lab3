import httpClient from "../http-common";

export const getDetalleOrdenByIdOrden = async (idOrden) => {
    try{
        const response = await httpClient.get(`/detalleOrden/byIdOrden/${idOrden}`);
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

export const saveDetalleOrden = async (detalleOrden) => {
    try {
        const response = await httpClient.post("/detalleOrden/", detalleOrden);
        return response.data;
    } catch (error) {
        console.error("Error al guardar el detalle de la orden:", error.response ? error.response.data : error);
        throw error;
    }
};