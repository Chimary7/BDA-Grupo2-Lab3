<script setup>
import { ref, computed } from 'vue';
import { onMounted } from 'vue';
import { useRouter } from 'vue-router';
import { getOrdersByIdUser } from '../../services/orderService';
import { useStore } from "vuex";

const store = useStore();
const router = useRouter();
const orders = ref([]);
const loading = ref(false);
const user = store.getters.getUser;

// Paginación
const currentPage = ref(1);
const pageSize = ref(5);

// Obtener órdenes visibles para la página actual
const paginatedOrders = computed(() => {
  const start = (currentPage.value - 1) * pageSize.value;
  const end = start + pageSize.value;
  return orders.value.slice(start, end);
});

// Calcular el total de páginas
const totalPages = computed(() => Math.ceil(orders.value.length / pageSize.value));

onMounted(async () => {
  loading.value = true;
  try {
    console.log('user:', user);
    const response = await getOrdersByIdUser(user.id_user);
    orders.value = response.data;
    console.log('orders:', orders.value);
  } catch (error) {
    console.error('Error al cargar órdenes:', error);
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
};

// Cambiar página
const changePage = (page) => {
  if (page > 0 && page <= totalPages.value) {
    currentPage.value = page;
  }
};
</script>

<template>
  <div class="h-full w-full bg-transparent p-4 flex items-center flex-wrap scroll overflow-y-auto">
    <div v-if="loading" class="h-full w-full bg-transparent flex items-center justify-center">
      <p>Cargando...</p>
    </div>
    <div v-else class="w-4/5 table-auto border-collapse  m-4 mx-auto">
      <table class="w-4/5 table-auto border-collapse border border-black m-4 mx-auto">
        <thead>
        <tr>
          <th class="border border-gray-400 px-2 py-1 w-1/12">N°</th>
          <th class="border border-gray-400 px-2 py-1 w-3/12">Fecha</th>
          <th class="border border-gray-400 px-2 py-1 w-4/12">Total</th>
          <th class="border border-gray-400 px-2 py-1 w-4/12">Detalles</th>
        </tr>
        </thead>
        <tbody>
        <tr
            v-for="(order, index) in paginatedOrders"
            :key="order.id"
            class="hover:bg-gray-100 cursor-pointer"
        >
          <td class="border border-gray-400 px-2 py-1 w-1/12">
            {{ (currentPage - 1) * pageSize + index + 1 }}
          </td>
          <td class="border border-gray-400 px-2 py-1 w-3/12">
            {{ fecha(order.fechaOrden) }}
          </td>
          <td class="border border-gray-400 px-2 py-1 w-4/12">
            ${{ order.total }}
          </td>
          <td class="border border-gray-400 px-2 py-1 w-4/12">
            <button
                class="bg-blue-500 text-white px-2 py-1 rounded"
                @click="DetailsOrder(order.id)"
            >
              Ver detalles
            </button>
          </td>
        </tr>
        </tbody>
      </table>
      <!-- Paginación -->
      <div class="flex justify-center items-center mt-4">
        <button
            class="mx-1 px-3 py-1 bg-gray-300 rounded hover:bg-gray-400"
            :disabled="currentPage === 1"
            @click="changePage(currentPage - 1)"
        >
          Anterior
        </button>
        <span class="mx-2">Página {{ currentPage }} de {{ totalPages }}</span>
        <button
            class="mx-1 px-3 py-1 bg-gray-300 rounded hover:bg-gray-400"
            :disabled="currentPage === totalPages"
            @click="changePage(currentPage + 1)"
        >
          Siguiente
        </button>
      </div>
    </div>
  </div>
</template>
