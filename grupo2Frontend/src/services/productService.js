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

export const getProductByID = async (id) => {
    try{
        const response = await httpClient.get(`/public/productos/${id}`);
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

export const editProduct = async (product) => {
    try {
        const formattedProduct = {
            id: String(product.id),
            idCategoria: String(product.idCategoria),
            nombre: product.nombre,
            descripcion: product.descripcion,
            precio: Number(product.precio),
            stock: Number(product.stock),
            estado: product.estado
        };

        console.log('Datos a enviar:', formattedProduct);
        
        const response = await httpClient.put("/producto/", formattedProduct, {
            headers: {
                'Content-Type': 'application/json'
            }
        });
        
        console.log('Respuesta:', response.data);
        return response.data;
    } catch (error) {
        console.error("Error completo:", error);
        console.error("Error en la respuesta del servidor:", error.response?.data);
        throw error;
    }
}


export const deleteProductByID = async (id) =>{
    try{
        const response = await httpClient.delete(`/public/productos/${id}`);
        return response.data;
    }
    catch (error) {
        console.error("Error en la respuesta del servidor:", error.response.data);
        throw error;
    }
}