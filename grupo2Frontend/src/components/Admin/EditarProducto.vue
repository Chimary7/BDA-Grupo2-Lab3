<script setup>

import {useRouter} from "vue-router";
import {onMounted, ref} from "vue";
import {editProduct, getProductByID} from "../../services/productService.js";
import {getAllCategories} from "../../services/categoryService.js";

const router = useRouter();
const idProducto = router.currentRoute.value.params.id;
const nombre = ref('')
const precio = ref('')
const stock = ref('')
const descripcion = ref('')
const estado = ref('')
const categorias = ref([])
const selectedCategoria = ref(null);

onMounted(async () => {
  const response1 = await getAllCategories();
  categorias.value = response1.data;

  const response = await getProductByID(idProducto);
  nombre.value = response.data.nombre;
  precio.value = response.data.precio;
  stock.value = response.data.stock;
  selectedCategoria.value = response.data.id_categoria;
  descripcion.value = response.data.descripcion;
  estado.value = response.data.estado;
});

const editarProducto = async() => {
  if (nombre.value === '' || precio.value === '' || stock.value === '' || descripcion.value === '' || categoria.value === '' || selectedCategoria.value === '' || estado.value === '') {
    alert('Todos los campos son obligatorios');
    return;
  }

  console.log('Enviando los cambios hechos al producto');
  const data = {
    nombre: nombre.value,
    descripcion: descripcion.value,
    precio: precio.value,
    stock: stock.value,
    estado: estado.value,
    id_categoria: selectedCategoria.value
  }

  console.log(data);
  const response = await editProduct(idProducto, data);
  console.log(response);
  if (response) {
    alert('Los cambios se han aplicado exitosamente');
    router.push({ name: 'mostrarProductos', params: { id: 1 }});
  } else {
    alert('Error al enviar los cambios');
  }
}

const volver = () => {
  router.push({ name: 'allProducts'});
};


</script>

<template>
  <div class="flex justify-center items-center min-h-screen bg-white p-4">
    <div class="bg-white p-8 rounded-lg border border-[#71b770] shadow-lg w-full max-w-2xl">
      <h1 class="text-center text-3xl font-bold text-[#71b770] mb-6">Editar Producto</h1>
      <form @submit.prevent="editarProducto">
        <div class="mb-4">
          <label for="nombre" class="block text-[#71b770] font-bold mb-2">Nombre:</label>
          <input type="text" v-model="nombre" id="nombre" placeholder="Nombre del producto" class="text-black w-full bg-white p-2 border border-gray-300 rounded" />
        </div>

        <div class="flex gap-4 mb-4">
          <div class="flex-grow">
            <label for="precio" class="block text-[#71b770] font-bold mb-2">Precio:</label>
            <input type="number" v-model="precio" id="precio" placeholder="Precio del producto" inputmode="decimal" min="0" class="bg-white text-black w-full p-2 border border-gray-300 rounded" />
          </div>
          <div class="flex-grow">
            <label for="stock" class="block text-[#71b770] font-bold mb-2">Stock:</label>
            <input type="number" v-model="stock" id="stock" placeholder="Cantidad en stock" step="1" class="bg-white text-black w-full p-2 border border-gray-300 rounded" />
          </div>
        </div>

        <div class="flex gap-4 mb-4">
          <div class="flex-grow">
            <label for="estado" class="block text-[#71b770] font-bold mb-2">Estado:</label>
            <select v-model="estado" id="estado" class="w-full p-2 border border-gray-300 rounded bg-white">
              <option disabled value="">Seleccionar estado</option>
              <option value="disponible">Disponible</option>
              <option value="no disponible">No Disponible</option>
            </select>
          </div>
          <div class="flex-grow">
            <label for="categoria" class="block text-[#71b770] font-bold mb-2">Categoría:</label>
            <select v-model="selectedCategoria" id="categoria" class="text-black w-full p-2 border border-gray-300 rounded bg-white">
              <option disabled value="">Seleccionar categoría</option>
              <option v-for="cat in categorias" :key="cat.id_categoria" :value="cat.id_categoria">{{ cat.nombre }}</option>
            </select>
          </div>
        </div>

        <div class="mb-4">
          <label for="descripcion" class="block text-[#71b770] font-bold mb-2">Descripción:</label>
          <textarea v-model="descripcion" id="descripcion" placeholder="Descripción del producto" class="bg-white text-black w-full p-2 border border-gray-300 rounded resize-none"></textarea>
        </div>

        <div class="flex justify-between gap-4">
          <button type="button" @click="volver" class="bg-[#71b770] text-white p-2 rounded w-full hover:bg-green-600">Volver</button>
          <button type="submit" class="bg-[#71b770] text-white p-2 rounded w-full hover:bg-green-600">Guardar Cambios</button>
        </div>
      </form>
    </div>
  </div>
</template>