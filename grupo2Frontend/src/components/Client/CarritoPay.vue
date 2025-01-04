<script setup>
import { computed } from "vue";
import { useStore } from "vuex";

const store = useStore();

const itemsCarrito = computed(() => store.state.carrito);

const incrementarCantidad = (item) => {
  store.commit("ActualizarCarrito", { productId: item.id, cantidad: item.cantidad + 1 });
};

const disminuirCantidad = (item) => {
  if (item.cantidad > 1) {
    store.commit("ActualizarCarrito", { productId: item.id, cantidad: item.cantidad - 1 });
  } else {
    store.commit("RemoveProductoFromCarrito", item.id);
  }
};
</script>

<template>
    <div class="h-full w-full flex justify-center items-center">
      <div class="h-full w-1/2 bg-color-primary">
        <ul class="h-full w-full flex items-center flex-col p-6">
          <li
            v-for="(item, index) in itemsCarrito"
            :key="index"
            class="w-full p-4 border-2 border-color-quaternary my-2 rounded-lg flex justify-between items-center hover:border-color-quinary hover:ring-1 hover:ring-color-quinary bg-white"
          >
            <div class="w-4/6 flex justify-between items-center px-4">
              <span>{{ item.name }}</span>
              <button
                class="text-white px-3 py-1 rounded-lg bg-color-quinary hover:ring-1 hover:ring-color-quaternary hover:outline-none hover:border-color-quaternary"
                @click="disminuirCantidad(item)"
              >
                -
              </button>
              <span>Cantidad: {{ item.cantidad }}</span>
              <button
                class="bg-blue-500 text-white px-3 py-1 rounded-lg hover:ring-1 hover:ring-blue-400 hover:outline-none hover:border-blue-400"
                @click="incrementarCantidad(item)"
              >
                +
              </button>
              <span>Precio: ${{ item.precio_unitario }}</span>
            </div>
            <div class="w-1/6 flex justify-center items-center h-full gap-2">
              <button
                class="bg-red-500 text-white  py-1 rounded-lg p-6 hover:ring-1 hover:ring-red-400 hover:outline-none hover:border-red-400"
                @click="store.commit('RemoveProductoFromCarrito', item.id)"
              >
                Eliminar
              </button>
            </div>
          </li>
        </ul>
      </div>
      <div class="h-full w-1/2 b-white text-black">
        <!--
        Aquí va el formulario de pago (por implementar) 
        debe pedir la dirección de envío de la entrega y mostrar el total del carrito 
        al presionar el pagar entonces se crea la orden y los detalles orden además de la entrega 
        -->
    </div>
    </div>
  </template>
  