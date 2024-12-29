<script setup>
import Icon from '../../../assets/iconuser.png';
import { useRouter } from 'vue-router';

const router = useRouter();
const Login = () => {
    router.push({ name: 'Login' });
}
</script>
<template>
    <button @click="Login" class="h-full w-auto bg-transparent border-2 border-white rounded-xl flex items-center justify-center p-4 cursor-pointer hover:bg-color-quaternary hover:border-color-quaternary">
        <img :src="Icon" alt="icon" class="h-5 w-auto filter invert" />
        <p class="text-white font-bold h-full w-auto flex items-center justify-center px-2">
            Login
        </p>
    </button>
</template>