<script setup>
import { onMounted, ref, computed, watch } from 'vue';
import { getProductCategory } from '../../services/productService';
import { useRoute } from 'vue-router';
import { getFilesProductByType } from '../../services/filesService';

const products = ref([]);
const loading = ref(false);
const route = useRoute();
const imageUrls = ref([]);

const props = defineProps({
    searchQuery: String
})

const fetchProductsByCategory = async () => {
    loading.value = true;
    try {
        const categoryId = route.query.id;
        const response = await getProductCategory(categoryId);
        products.value = response.data;
        for (const product of products.value) {
            imageUrls.value[product.id] = await ImageProduct(product.id);
        }
    } catch (error) {
        console.error('Error al cargar productos:', error);
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

const DetailsProduct = (id) => {
    //router.push({ name: 'DetailsProduct', params: { id } });
    console.log('se redirige al producto con id: ', id);
}

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

watch(() => route.query.id, fetchProductsByCategory, { immediate: true });

</script>

<template>
    <div class="h-full w-full bg-transparent p-4 flex items-center flex-wrap scroll overflow-y-auto">
        <div v-if="loading" class="h-full w-full bg-transparent flex items-center justify-center">
            <p>cargando...</p>
        </div>
        <div v-for="product in filteredProducts" :key="product.id" @click="DetailsProduct(product.id)" class="h-96 w-88 bg-white m-4 flex items-center justify-center flex-col rounded-lg border-2 border-color-secondary hover:shadow-lg hover:cursor-pointer">
            <img :src="imageUrls[product.id]" alt="Producto" class="h-1/2 w-1/2 bg-color-primary" />
            <h2 class="text-xl font-bold text-black p-2">{{ product.nombre }}</h2>
            <p class="text-lg text-black p-2">${{ product.precio }}</p>
        </div>
    </div>
</template>