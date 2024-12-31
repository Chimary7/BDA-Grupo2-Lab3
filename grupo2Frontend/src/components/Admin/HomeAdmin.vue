<template>
  <div class="bg-white min-h-screen">
    <NavbarAdmin />
    <router-view />
    <h1 class="text-[#71B770]">Productos:</h1>
    <div class="h-full w-full bg-transparent p-4 flex items-center flex-wrap scroll overflow-y-auto">
      <div v-if="loading" class="h-full w-full bg-transparent flex items-center justify-center">
        <p>cargando...</p>
      </div>
      <div v-for="product in products" :key="product.id" @click="showProductDetails(product)" class="h-96 w-88 bg-white m-4 flex items-center justify-center flex-col rounded-lg border-2 border-color-secondary hover:shadow-lg hover:cursor-pointer">
        <img :src="product.imagen" alt="Producto" class="h-1/2 w-1/2 bg-color-primary" />
        <h2 class="text-xl font-bold text-black p-2">{{ product.nombre }}</h2>
        <p class="text-lg text-black p-2">${{ product.precio }}</p>
      </div>
    </div>
    <div v-if="showPopup" class="fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center">
      <div class="bg-white p-8 rounded-lg w-2/3 h-1/2 relative flex">
        <button @click="closePopup" class="absolute top-2 right-2 text-gray-500 hover:text-gray-700">✕</button>
        <img :src="selectedProduct.imagen" alt="Producto" class="h-1/2 w-1/2 bg-color-primary" />
        <div class="ml-8 flex flex-col justify-between">
          <div>
            <h2 class="text-2xl font-bold text-black">{{ selectedProduct.nombre }}</h2>
            <p class="text-lg text-left text-black mt-2">Categoría: {{selectedProduct.idCategoria}}</p>
            <p class="text-lg text-left text-black mt-4">Precio: CLP${{ selectedProduct.precio }}</p>
            <p class="text-lg text-left text-black mt-2">Descripción: {{ selectedProduct.descripcion }}</p>
            <p class="text-lg text-left text-black mt-2">Stock disponible: {{ selectedProduct.stock }}</p>
          </div>
          <div class="mt-8">
            <button @click="confirmDelete" class="bg-red-500 text-white p-2 rounded-lg m-2">Borrar</button>
            <button @click="editProduct" class="bg-blue-500 text-white p-2 rounded-lg m-2">Editar</button>
          </div>
        </div>
      </div>
    </div>
    <div v-if="showConfirmPopup" class="fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center">
      <div class="bg-white p-8 rounded-lg w-1/3 h-1/4 relative flex flex-col items-center">
        <p class="text-lg text-black mb-4">¿Estas seguro de que deseas eliminar el producto "{{ selectedProduct.nombre }}"?</p>
        <div class="flex">
          <button @click="deleteProduct" class="bg-red-500 text-white p-2 rounded-lg m-2">Sí</button>
          <button @click="closeConfirmPopup" class="bg-gray-500 text-white p-2 rounded-lg m-2">No</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import NavbarAdmin from './NavbarAdmin.vue';
import { onMounted, ref } from 'vue';
import { getAll } from '../../services/productService.js';

export default {
  components: {
    NavbarAdmin
  },
  setup() {
    const products = ref([]);
    const loading = ref(false);
    const error = ref(false);
    const showPopup = ref(false);
    const showConfirmPopup = ref(false);
    const selectedProduct = ref(null);

    const getProducts = async () => {
      loading.value = true;
      error.value = false;
      try {
        const response = await getAll();
        products.value = response.data;
      } catch (error) {
        console.log(error);
      } finally {
        loading.value = false;
      }
    };

    const showProductDetails = (product) => {
      selectedProduct.value = product;
      showPopup.value = true;
    };

    const closePopup = () => {
      showPopup.value = false;
      selectedProduct.value = null;
    };

    const confirmDelete = () => {
      showConfirmPopup.value = true;
    };

    const closeConfirmPopup = () => {
      showConfirmPopup.value = false;
    };

    const deleteProduct = () => {
      console.log('Producto borrado:', selectedProduct.value.id);
      closeConfirmPopup();
      closePopup();
    };

    const editProduct = () => {
      console.log('Producto editado:', selectedProduct.value.id);
    };

    onMounted(() => {
      getProducts();
    });

    return {
      products,
      loading,
      error,
      showPopup,
      showConfirmPopup,
      selectedProduct,
      showProductDetails,
      closePopup,
      confirmDelete,
      closeConfirmPopup,
      deleteProduct,
      editProduct
    };
  }
};
</script>