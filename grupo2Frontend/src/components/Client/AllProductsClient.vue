<script setup>
import { onMounted, ref, computed } from 'vue';
import { getAll } from '../../services/productService';
import { getFilesProductByType } from '../../services/filesService';
import { useRouter } from 'vue-router';
import { getValorationsByIdProducto, getPromedioValoracionProducto } from '../../services/ValorationService';
import Carousel from './component/CarrouselProduct.vue';
import { useStore } from 'vuex';


const store = useStore();
const products = ref([]);
const loading = ref(false);
const error = ref(false);
const imageUrls = ref([]);
const showModal = ref(false);
const selectedProduct = ref(null);
const archivosURLSelectedProduct = ref([]);
const valoracionesSelectedProduct = ref([]);
const PromedioSelectedProduct = ref(0);
const cantidad = ref(1);

const props = defineProps({
    searchQuery: String
})

const router = useRouter();

const fetchValoraciones = async () =>{
    try{
        const valoracionesData = await getValorationsByIdProducto(selectedProduct.value.id);
        if (valoracionesData && valoracionesData.length > 0) {
            valoracionesSelectedProduct.value = valoracionesData;
        } else {
            valoracionesSelectedProduct.value = [];
            error.value = "No se encontraron valoraciones para este producto.";
        }

        const promedioData = await getPromedioValoracionProducto(selectedProduct.value.id);
        if (promedioData) {
            PromedioSelectedProduct.value= promedioData;
        } else {
            PromedioSelectedProduct.value= 0;
        }
    } catch(error){
        console.log(error);
    }
}

const getProducts = async () => {
    loading.value = true;
    error.value = false;
    try {
        const response = await getAll();
        products.value = response.data;
        for (const product of products.value) {
            imageUrls.value[product.id] = await ImageProduct(product.id);
        }
    } catch (error){
        console.log(error);
    } finally {
        loading.value = false;
    }
}

const filteredProducts = computed(() => {
    if (!props.searchQuery) return products.value;
    return products.value.filter(product =>
        product.nombre.toLowerCase().includes(props.searchQuery.toLowerCase())
    );
});

onMounted(() => {
    getProducts();
})

const showProductDetails = async (product) => {
    try {
        selectedProduct.value = product;
        const responseArchivos = await getFilesProductByType(product.id, 'ARCHIVO');
        
        archivosURLSelectedProduct.value = [
            imageUrls.value[product.id],
            ...responseArchivos.data.map(file => typeof file === 'string' ? file : file.url) 
        ].filter(url => url && url.trim() !== '');
        
        console.log('Archivos del producto seleccionado:', archivosURLSelectedProduct.value);
        await fetchValoraciones();
        showModal.value = true;
    } catch (error) {
        console.error('Error showing product details:', error);
    }
};


//--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
// CASO REEMPLAZADO, VER SI SE PUEDE REUTILIZAR
const DetailsProduct = (id) => {
    //router.push({ name: 'DetailsProduct', params: { id } });
    console.log('se redirige al producto con id: ', id);
}
//--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

const closeModal = () => {
    showModal.value = false;
    archivosURLSelectedProduct.value = [];
    selectedProduct.value = null;
};

const ImageProduct = async (id) => {
    try {
        const response = await getFilesProductByType(id, 'PORTADA');
        if (response.data && response.data.length > 0) {
            return response.data[0].url;
        }
        return ''; // Return an empty string if no image is found
    } catch (error) {
        console.error('Error fetching image for product:', id, error);
        return ''; // Always return a string
    }
};

//--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
// BOTON PENDIENTE DE IMPLEMENTAR
const addToCart = () => {
    if (selectedProduct.value) {
        const productWithQuantity = {
            ...selectedProduct.value,
            cantidad: cantidad.value
        };
        store.dispatch('addProductoToCarrito', productWithQuantity);
        closeModal();
    }
};
//--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

const continueShopping = () => {
    closeModal();
};

const valoracion = () => {
    router.push({ name: 'ValorationProduct', params: { idProducto: selectedProduct.value.id } });
    closeModal();
};
</script>

<template>
    <div class="h-full w-full bg-transparent p-4 flex items-center flex-wrap scroll overflow-y-auto">
        <div v-if="loading" class="h-full w-full bg-transparent flex items-center justify-center">
            <p>Cargando...</p>
        </div>
        <div v-for="product in filteredProducts" :key="product.id" @click="showProductDetails(product)" class="h-96 w-88 bg-white m-4 flex items-center justify-center flex-col rounded-lg border-2 border-color-secondary hover:shadow-lg hover:cursor-pointer">
            <img :src="imageUrls[product.id]" alt="Producto" class="h-1/2 w-1/2 bg-color-primary" />
            <h2 class="text-xl font-bold text-black p-2">{{ product.nombre }}</h2>
            <p class="text-lg text-black p-2">${{ product.precio }}</p>
        </div>
    </div>
    <div v-if="showModal" class="fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center">
        <div class="bg-white p-8 rounded-lg w-11/12 p-2 h-5/6 relative flex">
            <div class="bg-white p-8 rounded-lg w-2/3 h-full flex">
                <Carousel :urls="archivosURLSelectedProduct" />
                <div class="ml-8 flex flex-col justify-between">
                    <div class="flex flex-col">
                        <h2 class="text-2xl font-bold text-black">{{ selectedProduct.nombre }}</h2>
                        <div class="bg-white p-4 rounded-lg mt-4 border border-blue-500">
                            <p class="text-lg text-left text-black mt-2">Categoría: {{selectedProduct.idCategoria}}</p>
                            <p class="text-lg text-left text-black font-bold mt-4">Precio: CLP${{ selectedProduct.precio }}</p>
                            <p class="text-lg text-left text-black mt-2">Descripción: {{ selectedProduct.descripcion }}</p>
                            <p class="text-lg text-left text-black font-medium mt-2">valoración: {{ PromedioSelectedProduct }}</p>
                            <p class="text-lg text-left text-black mt-2">Stock disponible: {{ selectedProduct.stock }}</p>
                            <input type="number" v-model="cantidad" :max="selectedProduct.stock" min="1" required class="mt-4 p-4 border rounded w-full bg-white" placeholder="Cantidad" />
                        </div>
                    </div>
                    <div class="mt-8 flex flex-col">
                        <button @click="valoracion" class="bg-red-500 text-white p-2 rounded-lg m-2">Valorar Producto</button>
                        <div class="flex">
                            <button @click="continueShopping" class="bg-blue-500 text-white p-2 rounded-lg m-2 flex-1">Seguir comprando</button>
                            <button @click="addToCart" class="bg-green-500 text-white p-2 rounded-lg m-2 flex-1">Añadir al carro</button>
                        </div>
                    </div>
                </div>
            </div>
            <div class="w-1/3 h-full flex flex-col border-l-2 border-color-secondary">
                <h2 class="p-4 text-2xl font-bold text-color-quinary">Valoraciones</h2>
                <div class="flex flex-col p-4 h-full overflow-y-auto overflow-x-hidden w-full p-4">
                    <div v-for="valoracion in valoracionesSelectedProduct" :key="valoracion.id" class="bg-color-primary border border-color-secondary p-4 rounded-lg w-full flex space-between items-center">
                        <p class="text-lg text-left font-medium text-black mt-2 p-4">Valoración: {{ valoracion.valoracion }}</p>
                        <p class="text-lg text-left font-medium text-black mt-2 p-4">Comentario: {{ valoracion.comentario }}</p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>