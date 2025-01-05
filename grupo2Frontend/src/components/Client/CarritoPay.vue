<script setup>
import { computed } from "vue";
import { useStore } from "vuex";
import { useRouter } from "vue-router";
import { ref } from "vue";
import { saveOrden } from "../../services/OrdenService";
import { saveDetalleOrden } from "../../services/DetalleOrdenService";
import { saveEntrega } from "../../services/EntregaService";

const store = useStore();
const router = useRouter();
const direccion = ref('');


// Computed property para obtener los items del carrito
const itemsCarrito = computed(() => store.state.carrito);

const totalCarrito = computed(() => store.state.carrito.reduce((total, item) => total + item.precio * item.cantidad, 0));

// Función para incrementar la cantidad de un producto
const incrementarCantidad = (item) => {
  store.dispatch("ActualizarCarrito", { productId: item.id, cantidad: item.cantidad + 1 });
};

// Función para disminuir la cantidad de un producto
const disminuirCantidad = (item) => {
  if (item.cantidad > 1) {
    store.dispatch("ActualizarCarrito", { productId: item.id, cantidad: item.cantidad - 1 });
  } else {
    store.dispatch("RemoveProductoFromCarrito", item.id);
  }
};

// Obtener el usuario autenticado
const User = store.getters.getUser;

// Implementar la creación de orden
const crearOrden = async () => {
  const orden = {
    id: Date.now().toString(),
    fechaOrden: new Date(),
    estado: "Pendiente",
    idCliente: User.id_user,
    idEntrega: Date.now().toString(),
    total: totalCarrito.value
  };

  const detallesOrden = itemsCarrito.value.map(item => ({
    id: Date.now().toString() + item.id,
    idOrden: orden.id,
    idProducto: item.id,
    cantidad: item.cantidad,
    precio_unitario: item.precio_unitario
  }));

  const entrega = {
    id: orden.idEntrega,
    idZona: "Zona 1",
    idCliente: orden.idCliente,
    coordenadaDireccion: { type: "Point", coordinates: [40.7128, -74.0060] },
    direccion: direccion.value,
    estado: "En camino"
  };

  try {
    await saveOrden(orden);
    for (const detalle of detallesOrden) {
      await saveDetalleOrden(detalle);
    }
    await saveEntrega(entrega);

    console.log("Orden:", orden);
    console.log("Detalles de la Orden:", detallesOrden);
    console.log("Entrega:", entrega);

    router.push({ name: 'MetodoPago' });
  } catch (error) {
    console.error("Error al crear la orden:", error);
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
            <span>{{ item.nombre }}</span>
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
            <span>Precio: ${{ item.precio }}</span>
          </div>
          <div class="w-1/6 flex justify-center items-center h-full gap-2">
            <button
              class="bg-red-500 text-white py-1 rounded-lg p-6 hover:ring-1 hover:ring-red-400 hover:outline-none hover:border-red-400"
              @click="store.dispatch('RemoveProductoFromCarrito', item.id)"
            >
              Eliminar
            </button>
          </div>
        </li>
      </ul>
    </div>
    <div class="h-full w-1/2 bg-white text-black p-6">
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
