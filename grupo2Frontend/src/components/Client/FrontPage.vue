<script setup>
import Carrousel from './component/carrousel.vue';
import { ref, onMounted } from 'vue';
import { useRouter } from 'vue-router';
import { getFilesProductByType } from '../../services/filesService';

import { getProductCategory } from '../../services/productService';

const fotos = [
    "https://res.cloudinary.com/dqyerfklb/image/upload/v1736093134/carpeta/Eccomerce/he5j7z6rsenynsxbl5iy.jpg",
    "https://res.cloudinary.com/dqyerfklb/image/upload/v1736093134/carpeta/Eccomerce/d9zrsbxtnoswbzczfm9c.jpg",
    "https://res.cloudinary.com/dqyerfklb/image/upload/v1736093134/carpeta/Eccomerce/n96jz5ojgmms5x6abkio.jpg"
];

const router = useRouter();
const featuredProducts = ref([]);
const imageUrls = ref({});
const loading = ref(false);

const fetchFeaturedProducts = async () => {
    loading.value = true;
    try {
        const response1 = await getProductCategory(1);
        const response5 = await getProductCategory(5);

        console.log('Response 1:', response1);
        console.log('Response 5:', response5);
        
        // Combinar productos de ambas categorías
        featuredProducts.value = [...response1.data, ...response5.data];
        
        for (const product of featuredProducts.value) {
            const imageResponse = await getFilesProductByType(product.id, 'PORTADA');
            imageUrls.value[product.id] = imageResponse.data[0]?.url || '';
        }
    } catch (error) {
        console.error('Error loading featured products:', error);
    } finally {
        loading.value = false;
    }
};

const goToAllProducts = () => {
    router.push({name: 'All'});
}

onMounted(() => {
    fetchFeaturedProducts();
});
</script>

<template>
    <div class="h-full w-full bg-white flex flex-col overflow-y-auto">
        <div class="mt-8">
            <Carrousel :items="fotos"/>
        </div>
        
        <div class="w-full px-4 py-8">
            <h2 class="text-2xl font-bold text-center mb-8">Productos Destacados</h2>
            
            <div v-if="loading" class="flex justify-center items-center h-40">
                <p>Cargando productos...</p>
            </div>
            
            <div class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6 max-w-5xl mx-auto px-4">
                <button v-for="product in featuredProducts" 
                    :key="product.id"
                    @click="goToAllProducts"
                    class="bg-white rounded-lg shadow-md hover:shadow-lg transition-all duration-300 hover:scale-105 cursor-pointer text-left">
                    <img :src="imageUrls[product.id]" 
                        :alt="product.nombre"
                        class="w-full h-40 object-cover rounded-t-lg" />
                    <div class="p-4">
                        <h3 class="text-lg font-semibold">{{ product.nombre }}</h3>
                        <p class="text-gray-600 mt-2">${{ product.precio }}</p>
                    </div>
                </button>
            </div>

            <div class="w-full flex justify-center my-8">
                <button 
                    @click="goToAllProducts"
                    class="px-8 py-2 bg-white text-purple-600 border-2 border-purple-600 rounded-full hover:bg-purple-600 hover:text-white transition-colors duration-300"
                >
                    ver todos
                </button>
            </div>
        </div>
    </div>
</template>
