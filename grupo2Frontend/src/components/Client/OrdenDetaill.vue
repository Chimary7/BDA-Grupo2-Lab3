<script setup>
import { ref, onMounted } from 'vue';
import { useRouter } from 'vue-router';
import { getDetalleOrdenByIdOrden } from '../../services/DetalleOrdenService.js';
import { getOrderById } from '../../services/OrderService.js';
import { getProductByDetalleOrden } from '../../services/productService.js';

const router = useRouter();
const orden = ref({});
const orderDetails = ref([]);
const products = ref([]);
const idOrden = router.currentRoute.value.params.id;
const loading = ref(false);

onMounted(async () => {
  console.log('onMounted hook ejecutado');
  console.log('idOrden:', idOrden);
  loading.value = true;
  console.log('Cargando orden...');
  try {
    console.log('idOrden:', idOrden);
    const response = await getOrderById(idOrden);
    orden.value = response.data;
    console.log('orden:', orden.value);
    console.log('Cargando detalles de orden...');
    const responseDetails = await getDetalleOrdenByIdOrden(idOrden);
    orderDetails.value = responseDetails.data;
    console.log('orderDetails:', orderDetails.value);
    console.log('Cargando productos...');
    const responseProduct = await getProductByDetalleOrden(idOrden);
    products.value = responseProduct.data;
    console.log('products:', products.value);
    loading.value = false;
  } catch (error) {
    console.error('Error al cargar orden:', error);
  }
});

const fecha = (time) => {
  console.log('time:', time);
  const options = time.split("T");
  return options[0];
};
</script>

<template>
  <div v-if="loading" class="h-full w-full bg-transparent flex items-center justify-center rounded-1xl">
    <p>cargando...</p>
  </div>

  <!-- Solo renderiza cuando los datos estén disponibles -->
  <div v-else class="flex w-5/6">
    <!-- Asegúrate de que 'orden' y 'products' estén definidos antes de acceder a ellos -->
    <div class="border border-gray-400 px-2 py-1 w-full h-64 mr-4 mb-4 rounded-2xl flex flex-col items-center" v-if="orden && orden.fecha_orden && orden.total">
      <h2 class="text-center text-3xl mt-7 mb-6">Datos de la Orden</h2>
      <p class="mb-2"><strong>Fecha:</strong> {{ fecha(orden.fecha_orden) }}</p>
      <p class="mb-2"><strong>Total:</strong> ${{ orden.total }}</p>
      <p><strong>Estado:</strong> {{ orden.estado }}</p>
    </div>
    <div class="border border-gray-400 px-2 py-1 w-full min-h-64 mr-4 mb-4 rounded-2xl" v-if="products && products.length > 0">
      <h2 class="text-2xl mt-7 mb-6">Productos</h2>
      <ul class="mb-1">
        <li v-for="(product, index) in products" :key="product.id" class="flex items-center justify-between py-2 border-b border-gray-300">
          <span class="w-1/3 text-left ml-5">{{ product.nombre }}</span>
          <span class="w-1/3 text-center">${{ product.precio }}</span>
          <span class="w-1/3 text-right mr-4">{{ orderDetails[index]?.cantidad || 0 }} unidades</span> <!-- Accede a la cantidad usando el índice -->
        </li>
      </ul>
    </div>
  </div>
</template>

