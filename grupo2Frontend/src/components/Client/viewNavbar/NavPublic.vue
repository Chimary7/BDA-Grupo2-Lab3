<script setup>
import Icon from '../../../assets/iconuser.png';
import Carrito from '../../../assets/carrito.png';
import { useRouter } from 'vue-router';

const router = useRouter();

const carrito = () => {
    router.push({ name: 'Carrito' });
}

const Login = () => {
    router.push({ name: 'Login' });
}

</script>
<template>
    <div class="flex space-x-2">
        <button @click="carrito" class="h-full w-auto bg-transparent border-2 border-white rounded-xl flex items-center justify-center p-2 cursor-pointer hover:bg-color-quaternary hover:border-color-quaternary">
            <img :src="Carrito" alt="icon" class="h-5 w-auto filter invert" />
            <p class="text-white font-bold h-full w-auto flex items-center justify-center px-1">
                Pagar
            </p>
        </button>
        <button @click="Login" class="h-full w-auto bg-transparent border-2 border-white rounded-xl flex items-center justify-center p-2 cursor-pointer hover:bg-color-quaternary hover:border-color-quaternary">
            <img :src="Icon" alt="icon" class="h-5 w-auto filter invert" />
            <p class="text-white font-bold h-full w-auto flex items-center justify-center px-1">
                Login
            </p>
        </button>
    </div>
</template>