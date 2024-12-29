<script setup>
import Navbar from './Navbar.vue'
</script>
<template>
    <div class="h-full w-full bg-white flex flex-col">
        <Navbar />
        <div class="w-full h-full bg-transparent text-black flex items-center justify-center overflow-hidden box-border">
            <router-view />
        </div>
    </div>
</template>