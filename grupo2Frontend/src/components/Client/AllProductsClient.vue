<script setup>
import { onMounted, ref, computed } from 'vue';
import { getAll } from '../../services/productService';


const products = ref([]);
const loading = ref(false);
const error = ref(false);

const props = defineProps({
    searchQuery: String
})

const getProducts = async () => {
    loading.value = true;
    error.value = false;
    try {
        const response = await getAll();
        products.value = response.data;
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

const DetailsProduct = (id) => {
    //router.push({ name: 'DetailsProduct', params: { id } });
    console.log('se redirige al producto con id: ', id);
}

</script>
<template>
    <div class="h-full w-full bg-transparent p-4 flex items-center flex-wrap scroll overflow-y-auto">
        <div v-if="loading" class="h-full w-full bg-transparent flex items-center justify-center">
            <p>Cargando...</p>
        </div>
        <div v-for="product in filteredProducts" :key="product.id" @click="DetailsProduct(product.id)" class="h-96 w-88 bg-white m-4 flex items-center justify-center flex-col rounded-lg border-2 border-color-secondary hover:shadow-lg hover:cursor-pointer">
            <img :src="product.imagen" alt="Producto" class="h-1/2 w-1/2 bg-color-primary" />
            <h2 class="text-xl font-bold text-black p-2">{{ product.nombre }}</h2>
            <p class="text-lg text-black p-2">${{ product.precio }}</p>
        </div>
    </div>
</template>