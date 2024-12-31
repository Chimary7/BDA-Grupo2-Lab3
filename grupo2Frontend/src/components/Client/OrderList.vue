<script setup>
import { ref } from 'vue';
import { onMounted } from 'vue';
import { useRouter } from 'vue-router';
import { getOrdersByIdUser } from '../../services/orderService';
import {useStore} from "vuex";

const store = useStore();
const router = useRouter();
const orders = ref([]);
const loading = ref(false);
const user = store.getters.getUser;

onMounted(async () => {
  loading.value = true;
  try {
    console.log('user:', user);
    const response = await getOrdersByIdUser(user.id_user);
    orders.value = response.data;
  } catch (error) {
    console.error('Error al cargar ordenes:', error);
  } finally {
    loading.value = false;
  }
});

const fecha = (time) => {
  const options = time.split("T");
  return options[0];
};

const DetailsOrder = (id) => {
  router.push({ name: 'DetailsOrder', params: { id } });
}
</script>

<template>
  <div class="h-full w-full bg-transparent p-4 flex items-center flex-wrap scroll overflow-y-auto">
    <div v-if="loading" class="h-full w-full bg-transparent flex items-center justify-center">
      <p>cargando...</p>
    </div>
    <table v-else class="w-4/5 table-auto border-collapse border border-black m-4 mx-auto">
      <thead>
      <tr>
        <th class="border border-gray-400 px-2 py-1 w-1/12">N°</th>
        <th class="border border-gray-400 px-2 py-1 w-3/12">Fecha</th>
        <th class="border border-gray-400 px-2 py-1 w-4/12">Total</th>
        <th class="border border-gray-400 px-2 py-1 w-4/12">Detalles</th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="(order, index) in orders" :key="order.id" class="hover:bg-gray-100 cursor-pointer">
        <td class="border border-gray-400 px-2 py-1 w-1/12">{{ index + 1 }}</td>
        <td class="border border-gray-400 px-2 py-1 w-3/12">{{ fecha(order.fecha_orden) }}</td>
        <td class="border border-gray-400 px-2 py-1 w-4/12">${{ order.total }}</td>
        <td class="border border-gray-400 px-2 py-1 w-4/12">
          <button class="bg-blue-500 text-white px-2 py-1 rounded" @click="DetailsOrder(order.idOrden)">Ver detalles</button>
        </td>
      </tr>
      </tbody>
    </table>
  </div>
</template>