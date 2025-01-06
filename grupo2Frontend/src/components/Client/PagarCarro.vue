<template>
    <div class="h-full w-full flex justify-center items-center">
      <div class="h-full w-1/2 bg-white text-black p-6">
        <form @submit.prevent="procesarPago">
          <div class="mb-4">
            <label for="metodoPago" class="block text-sm font-medium text-gray-700">Método de Pago</label>
            <select
              id="metodoPago"
              v-model="metodoPago"
              class="bg-white mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm"
              required
            >
              <option value="" disabled selected>Seleccione un método de pago</option>
              <option value="tarjeta">Tarjeta de Crédito/Débito</option>
              <option value="paypal">PayPal</option>
              <option value="transferencia">Transferencia Bancaria</option>
            </select>
          </div>
          <div class="mb-4">
            <span class="block text-sm font-medium text-gray-700">Total: ${{ totalCarrito }}</span>
          </div>
          <button
            type="submit"
            class="bg-green-500 text-white px-4 py-2 rounded-lg hover:ring-1 hover:ring-green-400 hover:outline-none hover:border-green-400"
          >
            Pagar
          </button>
        </form>
      </div>
    </div>
  </template>
  
  <script setup>
  import { ref, computed } from "vue";
  import { useRouter } from 'vue-router';
  import { useStore } from "vuex";
  
  const store = useStore();
  
  const direccion = ref("");
  const metodoPago = ref("");
  const router = useRouter();
  
  const totalCarrito = computed(() => store.state.carrito.reduce((total, item) => total + item.precio * item.cantidad, 0));
  //-----------------------------------------------------------------------------------------------------------------------------------------------------------------------
    // BOTON PENDIENTE DE IMPLEMENTAR
  const procesarPago = () => {
    store.dispatch("clearCarrrito");
    router.push({ name: "All" });
  };
  //-----------------------------------------------------------------------------------------------------------------------------------------------------------------------
  </script>