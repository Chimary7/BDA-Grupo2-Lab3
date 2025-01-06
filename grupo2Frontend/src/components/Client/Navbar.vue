<script setup>
import { ref, onMounted, watch, computed } from 'vue';
import { verifyToken } from '../../services/authService.js';import Logo from '../../assets/LOGO.png';
import { useStore  } from 'vuex';
import Public from './viewNavbar/NavPublic.vue';
import Client from './viewNavbar/NavClient.vue';

const tokenValid = ref(false);
const role = ref(''); 

const store = useStore();

// Computed para observar el estado `login` desde Vuex
const isLoggedIn = computed(() => store.getters.getLogin);

// Función para verificar el token
const verify = async () => {
  try {
    const response = await verifyToken();
    if (response?.success) {
      tokenValid.value = true;
      role.value = response.role;
    } else {
      tokenValid.value = false;
      role.value = '';
    }
  } catch (error) {
    console.error('Error al verificar el token:', error);
    tokenValid.value = false;
  }
};

watch(isLoggedIn, (value) => {
  if (value) {
    verify();
  }
});

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
                <Public v-if="!isLoggedIn || role == 'ADMIN'" />
                <Client v-else />
            </div>
        </div>
    </div>
</template>