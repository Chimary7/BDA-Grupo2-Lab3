<script setup>
import { onMounted, ref, computed } from 'vue';
import { getAll } from '../../services/productService';
import { getFilesProductByType } from '../../services/filesService';
import { useRouter } from 'vue-router';

const products = ref([]);
const loading = ref(false);
const error = ref(false);
const imageUrls = ref([]);
const showModal = ref(false);
const selectedProduct = ref(null);

const props = defineProps({
    searchQuery: String
})

const router = useRouter();

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

const showProductDetails = (product) => {
    selectedProduct.value = product;
    showModal.value = true;
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
    selectedProduct.value = null;
};

const ImageProduct = async (id) => {
    try {
        const response = await getFilesProductByType(id, 'PORTADA');  
        if (response.data.length > 0) {
            return response.data[0].url;  
        }
        return '';  
    } catch (error) {
        console.log(error);
        return '';  
    }
}
//--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
// BOTON PENDIENTE DE IMPLEMENTAR
const addToCart = () => {
    // Logic to add the product to the cart
    console.log('Producto añadido al carro:', selectedProduct.value.id);
    closeModal();
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
        <div class="bg-white p-8 rounded-lg w-1/2 h-3/4 relative flex">
            <div class="bg-white p-8 rounded-lg w-full h-full flex">
                <img :src="imageUrls[selectedProduct.id]" alt="Producto" class="h-full w-1/2 object-contain" />
                <div class="ml-8 flex flex-col justify-between">
                    <div class="flex flex-col">
                        <h2 class="text-2xl font-bold text-black">{{ selectedProduct.nombre }}</h2>
                        <div class="bg-white p-4 rounded-lg mt-4 border border-blue-500">
                            <p class="text-lg text-left text-black mt-2">Categoría: {{selectedProduct.idCategoria}}</p>
                            <p class="text-lg text-left text-black mt-4">Precio: CLP${{ selectedProduct.precio }}</p>
                            <p class="text-lg text-left text-black mt-2">Descripción: {{ selectedProduct.descripcion }}</p>
                            <p class="text-lg text-left text-black mt-2">Stock disponible: {{ selectedProduct.stock }}</p>
                        </div>
                    </div>
                    <div class="mt-8 flex flex-col">
                        <button @click="valoracion" class="bg-red-500 text-white p-2 rounded-lg m-2">Valoración Producto</button>
                        <div class="flex">
                            <button @click="continueShopping" class="bg-blue-500 text-white p-2 rounded-lg m-2 flex-1">Seguir comprando</button>
                            <button @click="addToCart" class="bg-green-500 text-white p-2 rounded-lg m-2 flex-1">Añadir al carro</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>