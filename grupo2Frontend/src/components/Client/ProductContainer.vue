<script setup>
import Menu from './MenuClient.vue'
import { ref } from 'vue';

const searchQuery = ref('');

const updatesearchQuery = (query) => {
    searchQuery.value = query;
}

</script>

<template>
    <div class="h-screen w-full flex items-center justify-center">
        <Menu @searchQuery-Product="updatesearchQuery" />
        <section class="w-full h-full bg-gray-100">
            <router-view :searchQuery="searchQuery"/>
        </section>
    </div>
</template>