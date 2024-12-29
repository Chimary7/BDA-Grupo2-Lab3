<script setup>
import Navbar from './Navbar.vue'
</script>
<template>
    <Navbar />
    <div class="w-full h-screen bg-white text-black flex items-center justify-center">
        <router-view />
    </div>
</template>