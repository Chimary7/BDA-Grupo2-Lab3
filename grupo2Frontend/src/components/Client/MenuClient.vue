<script setup>
import { ref } from 'vue';
import { getAllCategories } from '../../services/categoryService';
import { onMounted } from 'vue';
import { useRouter } from 'vue-router';
import SearchIcon from '../../assets/search-icon.png';

const searchQuery = ref('');
const categories = ref([]);
const router = useRouter();

const emit = defineEmits(['searchQuery-Product']);
const handleSearch = () => {
    emit('searchQuery-Product', searchQuery.value);
}

const getCategories = async () => {
    const response = await getAllCategories();
    categories.value = response.data;
}

onMounted(() => {
    getCategories();
})

const navigateToCategory = (category) => {
    router.push({
        name: 'Category',
        params: { categoryName: category.nombre },
        query: { id: category.id }
});
}

</script>

<template>
    <div class="h-full w-96 flex items-center justify-center flex-col bg-white border-r-2 border-color-quaternary">
        <div class="h-1/6 w-full flex items-center justify-center relative">
            <img 
                :src="SearchIcon" 
                alt="Buscar" 
                class="absolute left-6 w-6 h-6"
            />
            <input 
                v-model="searchQuery" 
                type="text"
                @input="handleSearch"
                placeholder="Buscar producto"
                class="h-12 w-11/12 border-2 border-color-quaternary bg-white rounded-3xl p-4 pl-10 focus:border-quinary focus:outline-none focus:ring-1 focus:ring-color-quinary text-black"
                >
        </div>
        <hr class="w-11/12 border border-color-secondary" />
        <ul class="h-5/6 w-full">
            <h2 class="text-xl font-bold py-4 text-color-quinary">Categorias</h2>

            <li v-for="category in categories" :key="category.id" class="w-full p-2">
                <button class="h-12 w-full flex items-center justify-center bg-color-quaternary text-white font-semibold border-2 border-color-quaternary rounded-3xl hover:bg-white hover:border-color-quinary hover:text-color-quinary"
                    @click="navigateToCategory(category)"
                >
                    {{ category.nombre }}
                </button>
            </li>
        </ul>
    </div>
</template>