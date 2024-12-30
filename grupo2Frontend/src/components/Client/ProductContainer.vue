<script setup>
import Menu from './MenuClient.vue'
</script>

<template>
    <div class="h-screen w-full flex items-center justify-center">
        <Menu />
        <section class="w-full h-full bg-gray-100">
            <router-view />
        </section>
    </div>
</template>