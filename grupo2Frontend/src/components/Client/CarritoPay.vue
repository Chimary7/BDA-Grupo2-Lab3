<script setup>
import { computed } from "vue";
import { useStore } from "vuex";
import { useRouter } from "vue-router";

const store = useStore();
const router = useRouter();

const testProducts = [
  { id: 1, name: "Producto A", precio_unitario: 10000, cantidad: 2 },
  { id: 2, name: "Producto B", precio_unitario: 15000, cantidad: 1 },
  { id: 3, name: "Producto C", precio_unitario: 20000, cantidad: 3 },
];

// Inicializar el carrito con datos de prueba
store.commit("clearCarrrito");
testProducts.forEach((product) => {
  store.commit("addProductoToCarrito", product);
});

// Computed property para obtener los items del carrito
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

const totalCarrito = computed(() => {
  return itemsCarrito.value.reduce((total, item) => total + item.precio_unitario * item.cantidad, 0);
});
//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------
// IMPLEMENTAR CRER ORDEN, DETALLE ORDEN Y ENTREGA
const crearOrden = () => {
  // Lógica para crear la orden y los detalles de la orden
  console.log("Orden creada con dirección:", direccion.value);
  
  router.push({ name: 'MetodoPago' });
};
//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------
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
      <div class="h-full w-1/2 bg-white text-black p-6">
        <!--
        Aquí va el formulario de pago (por implementar) 
        debe pedir la dirección de envío de la entrega y mostrar el total del carrito 
        al presionar el pagar entonces se crea la orden y los detalles orden además de la entrega 
        -->
     <form @submit.prevent="crearOrden">
        <div class="mb-4">
          <label for="direccion" class="block text-sm font-medium text-gray-700">Dirección de Envío</label>
          <input
            type="text"
            id="direccion"
            v-model="direccion"
            class="bg-white mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm"
            required
          />
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
  