<script setup>
import Carrito from '../../../assets/carrito.png';
import Orden from '../../../assets/order.png';
import CerrarSecion from '../../../assets/Cerrar Secion.png';
import { logout } from '../../../services/authService';
import { useRouter } from 'vue-router';
import { useStore } from 'vuex';
const router = useRouter();
const store = useStore();

const VerOrdenes = () => {
    router.push({ name: 'OrderList' });
}

const CerrarSesion = async () => {
    const response = await logout();
    console.log('Response:', response);

    store.commit('logout');
    store.commit('clearUser');
    
    alert('Sesión cerrada exitosamente');
    router.push({ name: 'All' });
}

const Pagar = () => {
    router.push({ name: 'Carrito' });
}
</script>
<template>
    <div class="flex space-x-2">

        <button @click="Pagar" class="h-full w-auto bg-transparent border-2 border-white rounded-xl flex items-center justify-center p-2 cursor-pointer hover:bg-color-quaternary hover:border-color-quaternary">
            <img :src="Carrito" alt="icon" class="h-5 w-auto filter invert" />
            <p class="text-white font-bold h-full w-auto flex items-center justify-center px-1">
                Pagar
            </p>
        </button>

        <button @click="VerOrdenes" class="h-full w-auto bg-transparent border-2 border-white rounded-xl flex items-center justify-center p-2 cursor-pointer hover:bg-color-quaternary hover:border-color-quaternary">
            <img :src="Orden" alt="icon" class="h-5 w-auto filter invert" />
            <p class="text-white font-bold h-full w-auto flex items-center justify-center px-1">
                Ver Órdenes
            </p>
        </button>
        <button @click="CerrarSesion" class="h-full w-auto bg-transparent border-2 border-white rounded-xl flex items-center justify-center p-2 cursor-pointer hover:bg-color-quaternary hover:border-color-quaternary">
            <img :src="CerrarSecion" alt="icon" class="h-5 w-auto filter invert" />
            <p class="text-white font-bold h-full w-auto flex items-center justify-center px-1">
                Cerrar Sesión
            </p>
        </button>
    </div>
</template>