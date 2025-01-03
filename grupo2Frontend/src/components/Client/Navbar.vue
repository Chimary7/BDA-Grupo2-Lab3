<script setup>
import { ref, onMounted } from 'vue';
import { verifyToken } from '../../services/authService.js';import Logo from '../../assets/LOGO.png';
import Public from './viewNavbar/NavPublic.vue';
import Client from './viewNavbar/NavClient.vue';

const tokenValid = ref(false); // Variable reactiva para controlar si el token es válido

// Función para verificar el token
const verify = async () => {
  try {
    const response = await verifyToken();
    tokenValid.value = response;
  } catch (error) {
    tokenValid.value = false; 
  }
};

// Verificar token cuando el componente se monta
onMounted(() => {
  verify();
});

</script>

<template>
    <div class="h-16 w-full bg-color-quinary flex justify-center items-center text-white">
        <div class="flex justify-between items-center w-full h-full px-10">
            <div class="h-full w-auto flex items-center justify-center w-auto">
                <img :src="Logo" alt="logo" class="h-full w-full p-1" />
                <p class="w-auto h-full flex items-center font-bold">Ecommerce</p>
            </div>
            <div class="h-full p-2">
                <Public v-if="!tokenValid" />
                <Client v-else />
            </div>
        </div>
    </div>
</template>