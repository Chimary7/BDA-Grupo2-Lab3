<template>
    <Navbar /> 
    <div class="flex justify-center items-center min-h-screen bg-gray-100">        
      <div class="bg-white rounded-lg p-6 max-w-sm mt-4 border border-blue-800">
        <h1 class="text-4xl font-bold text-blue-800">Pagar Compra</h1>
        
        <p class="text-gray-700">Este es un mensaje dentro de una carta.</p>
        
        <div class="flex space-x-4 mt-4">
          <button @click="seguirComprando" class="bg-blue-500 text-white px-4 py-2 rounded">
            Seguir Comprando
          </button>
          <button @click="pagar" class="bg-red-500 text-white px-4 py-2 rounded">
            Pagar
          </button>
        </div>
      </div>
    </div>
</template>

<script setup>
import Navbar from './Navbar.vue'
import { useRouter } from 'vue-router';

const router = useRouter();

const seguirComprando = () => {
    router.push('/home/products/all');
}

const pagar = () => {
    // VER QUE SE DESEA HACER
}
</script>

