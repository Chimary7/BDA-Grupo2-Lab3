import httpClient from '../http-common';

export const getEntregaById = async (id) => {
    try {
        const response = await httpClient.get(`/entrega/${id}`);
        return response.data; 
    } catch (error) {
        console.error("Error en la respuesta del servidor:", error.response ? error.response.data : error);
        throw error;
    }
}