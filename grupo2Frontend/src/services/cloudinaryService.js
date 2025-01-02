import axios from 'axios';

const cloudinaryuploadpreset = import.meta.env.VITE_CLOUDINARY_UPLOAD_PRESET;
const cloudinarycloudname = import.meta.env.VITE_CLOUDINARY_CLOUD_NAME;

export const uploadFile = async (image) => {
    try{
        const formData = new FormData();
        formData.append("file", image);
        formData.append("upload_preset", cloudinaryuploadpreset);
        const response = await axios.post(`https://api.cloudinary.com/v1_1/${cloudinarycloudname}/upload`, formData);
        return {public_id: response.data.public_id, url: response.data.
            secure_url , status: response.status};
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