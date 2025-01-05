import httpClient from "../http-common";

export const getOrdenesByIdCliente = async(idCliente) => {
    try{
        const response = await httpClient.get(`/orden/getByIdUser/${idCliente}`);
        return response.data
    }catch (error) {
        console.error("Error en la respuesta del servidor:", error.response.data);
        throw error;
    }
}

export const saveOrden = async (orden) => {
    try {
        const response = await httpClient.post("/orden/", orden);
        return response.data;
    } catch (error) {
        console.error("Error al guardar la orden:", error.response ? error.response.data : error);
        throw error;
    }
};