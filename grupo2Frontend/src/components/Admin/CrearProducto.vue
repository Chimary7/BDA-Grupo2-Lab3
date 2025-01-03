<script setup>

import {useRouter} from "vue-router";
import {onMounted, ref, watch} from "vue";
import {createProduct, editProduct, getProductByID} from "../../services/productService.js";
import {uploadFile} from "../../services/cloudinaryService.js";
import { create } from "../../services/filesService.js";
import {getAllCategories} from "../../services/categoryService.js";

const router = useRouter();
const nombre = ref('')
const precio = ref('')
const stock = ref('')
const descripcion = ref('')
const estado = ref('')
const categorias = ref([])
const selectedCategoria = ref(null);
//variables de archivo
const selectedPortada = ref(null);
const selectedArchivos = ref([]);
const portadaPreview = ref(null);
const archivoPreviews = ref([]);

onMounted(async () => {
  const response1 = await getAllCategories();
  categorias.value = response1.data;
});


const editarProducto = async() => {
  if (nombre.value === '' || precio.value === '' || stock.value === '' || descripcion.value === '' || categoria.value === '' || estado.value === '') {
    alert('Todos los campos son obligatorios');
    return;
  }

  if (!selectedCategoria.value){
    alert('Seleccione una categoría');
    return;
  }

  const data = {
    nombre: nombre.value,
    descripcion: descripcion.value,
    precio: precio.value,
    stock: stock.value,
    estado: estado.value,
    idCategoria: selectedCategoria.value
  }

  console.log("Creando producto...");
  console.log(data);
  const response = await createProduct(data);
  console.log(response);
  if (response) {
    const responseURLPortada = await uploadFile(selectedPortada.value);
    const dataFile ={
      idProducto: response,
      nombre: responseURLPortada.public_id,
      tipo: 'PORTADA',
      url: responseURLPortada.url
    }

    const responseFile = await create(dataFile);
    console.log(responseFile);

    if (selectedArchivos.value.length > 0) {
      for (const file of selectedArchivos.value) {
        const responseURL = await uploadFile(file);
        const dataFile ={
          idProducto: response,
          nombre: responseURL.public_id,
          tipo: 'ARCHIVO',
          url: responseURL.url
        }
        const responseFile = await create(dataFile);
        console.log(responseFile);
      }
    }


    alert('Producto creado correctamente');
    await router.push({name: 'allProducts'});
  } else {
    alert('Error al crear el producto');
  }
}

const volver = () => {
  router.push({ name: 'allProducts'});
};

const onPortadaSelected = (event) => {
  const file = event.target.files[0];
  if (file && file.type.startsWith('image/')) {
    selectedPortada.value = file;
    portadaPreview.value = URL.createObjectURL(file);
    console.log('Portada seleccionada:', file);
  } else {
    alert('Seleccione un archivo de imagen válido para la portada.');
    event.target.value = null;
  }
};

const onArchivosSelected = (event) => {
  const files = Array.from(event.target.files);
  files.forEach(file => {
    if ((file.type.startsWith('image/') || file.type.startsWith('video/')) && 
        !selectedArchivos.value.some(existingFile => existingFile.name === file.name)) {
      selectedArchivos.value.push(file);
      archivoPreviews.value.push(URL.createObjectURL(file));
    }
  });
};

const removeArchivo = (index) => {
  selectedArchivos.value.splice(index, 1);
  archivoPreviews.value.splice(index, 1);
};

</script>

<template>
  <div class="flex justify-center items-center h-full w-full bg-white p-4">
    <div class="bg-white p-8 rounded-lg border border-[#71b770] shadow-lg w-full  flex flex-col h-5/6">
      <h1 class="text-center text-3xl font-bold text-[#71b770] mb-6">Crear Producto</h1>
      <form @submit.prevent="editarProducto" class="flex gap-4 h-full w-full">
        <div class="flex flex-col gap-4 h-full w-1/3">
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
                <option v-for="cat in categorias" :key="cat.id" :value="cat.id">{{ cat.nombre }}</option>
              </select>
            </div>
          </div>

          <div class="mb-4">
            <label for="descripcion" class="block text-[#71b770] font-bold mb-2">Descripción:</label>
            <textarea v-model="descripcion" id="descripcion" placeholder="Descripción del producto" class="bg-white text-black w-full p-2 border border-gray-300 rounded resize-none"></textarea>
          </div>

          <div class="flex justify-between gap-4">
            <button type="button" @click="volver" class="bg-[#71b770] text-white p-2 rounded w-full hover:bg-green-600">Volver</button>
            <button type="submit" class="bg-[#71b770] text-white p-2 rounded w-full hover:bg-green-600">crear producto</button>
          </div>
        </div>
        <div class="h-full w-2/3 border-l border-[#71b770]">
          <div class="flex flex-col gap-4 p-4 w-full h-full">
            <div class="mb-4 w-full h-1/4 flex space-between items-center">
              <label for="portada" class="block text-[#71b770] font-bold mb-2 p-2">Portada:</label>
              <input type="file" @change="onPortadaSelected" id="portada" accept="image/*" class="bg-white text-black w-1/2 p-2 border border-gray-300 rounded p-2" />
              <img v-if="portadaPreview" :src="portadaPreview" alt="Vista previa de portada" class="mt-4 w-32 object-contain rounded p-2" required/>
            </div>
            <div class="mb-4 h-1/4 w-full flex items-center">
              <label for="archivos" class="block text-[#71b770] font-bold mb-2 p-2">Archivos:</label>
              <input type="file" multiple @change="onArchivosSelected" id="archivos" class="bg-white text-black w-full p-2 border border-gray-300 rounded" />
            </div>
            <div v-if="selectedArchivos.length" class="mb-4 w-full h-full w-full scroll overflow-y-auto">
              <h2 class="text-lg font-bold">Archivos Seleccionados:</h2>
              <ul>
                <li v-for="(file, index) in selectedArchivos" :key="index" class="flex justify-between items-center">
                  <img :src="archivoPreviews[index]" alt="Vista previa archivo" class="w-16 h-16 object-cover rounded" />
                  {{ file.name }}
                  <button @click="removeArchivo(index)" class="text-red-500 bg-white border border-red-400 roundend-lg">Eliminar</button>
                </li>
              </ul>
            </div>
          </div>
        </div>
      </form>
    </div>
  </div>
</template>