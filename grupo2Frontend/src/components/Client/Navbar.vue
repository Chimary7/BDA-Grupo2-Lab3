<script setup>
import Logo from '../../assets/LOGO.png';
import Public from './viewNavbar/NavPublic.vue';
</script>

<template>
    <div class="h-16 w-full bg-color-quinary flex justify-center items-center text-white">
        <div class="flex justify-between items-center w-full h-full px-10">
            <div class="h-full w-auto flex items-center justify-center w-auto">
                <img :src="Logo" alt="logo" class="h-full w-full p-1" />
                <p class="w-auto h-full flex items-center font-bold">Ecommerce</p>
            </div>
            <div class="h-full p-2">
                <Public />
            </div>
        </div>
    </div>
</template>