<script setup>
import { onMounted, ref, computed } from 'vue';
import {deleteProductByID, getAll} from '../../services/productService.js';
import {getFilesProductByType} from '../../services/filesService.js';
import router from "../../router.js";

const products = ref([]);
const loading = ref(false);
const error = ref(false);
const showPopup = ref(false);
const showConfirmPopup = ref(false);
const selectedProduct = ref(null);
const imageUrls = ref([]);

const getProducts = async () => {
  loading.value = true;
  error.value = false;
  try {
    const response = await getAll();
    products.value = response.data;
    for (const product of products.value) {
            imageUrls.value[product.id] = await ImageProduct(product.id);
        }
  } catch (error) {
    console.log(error);
  } finally {
    loading.value = false;
  }
};

const ImageProduct = async (id) => {
    try {
        const response = await getFilesProductByType(id, 'PORTADA');  
        if (response.data.length > 0) {
            return response.data[0].url;  
        }
        return '';  
    } catch (error) {
        console.log(error);
        return '';  
    }
}

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

const deleteProduct = async () => {
  await deleteProductByID(selectedProduct);
  console.log('Producto borrado:', selectedProduct);
  closeConfirmPopup();
  closePopup();
};

const editProduct = () => {
  router.push({ name: 'EditarProducto', params: { id: selectedProduct.value.id } });
  closePopup();
};

onMounted(() => {
  getProducts();
});

</script>

<template>
  <div class="h-full w-full bg-transparent p-4 flex flex-wrap overflow-y-auto">
    <div v-if="loading" class="h-full w-full flex items-center justify-center">
      <p>Cargando...</p>
    </div>
    <div v-for="product in products" :key="product.id" @click="showProductDetails(product)" class="h-96 w-88 bg-white m-4 flex flex-col items-center justify-center rounded-lg border-2 border-color-secondary hover:shadow-lg hover:cursor-pointer">
      <img :src="imageUrls[product.id]" alt="Producto" class="h-1/2 w-1/2 object-contain" />
      <h2 class="text-xl font-bold text-black p-2">{{ product.nombre }}</h2>
      <p class="text-lg text-black p-2">${{ product.precio }}</p>
    </div>
  </div>
  <div v-if="showPopup" class="fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center">
    <div class="bg-white p-8 rounded-lg w-2/3 h-1/2 relative flex">
      <button @click="closePopup" class="absolute top-2 right-2 text-gray-500 hover:text-gray-700 bg-transparent border-none text-2xl">✕</button>
      <img :src="imageUrls[selectedProduct.id]" alt="Producto" class="h-full w-1/2 object-contain" />
      <div class="ml-8 flex flex-col justify-between">
        <div>
          <h2 class="text-2xl font-bold text-black">{{ selectedProduct.nombre }}</h2>
          <p class="text-lg text-left text-black mt-2">Categoría: {{selectedProduct.idCategoria}}</p>
          <p class="text-lg text-left text-black mt-4">Precio: CLP${{ selectedProduct.precio }}</p>
          <p class="text-lg text-left text-black mt-2">Descripción: {{ selectedProduct.descripcion }}</p>
          <p class="text-lg text-left text-black mt-2">Stock disponible: {{ selectedProduct.stock }}</p>
        </div>
        <div class="mt-8 flex">
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
</template>>