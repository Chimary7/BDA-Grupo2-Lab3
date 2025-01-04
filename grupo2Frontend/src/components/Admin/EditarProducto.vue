<script setup>

import {useRouter} from "vue-router";
import {onMounted, ref, watch} from "vue";
import {editProduct, getProductByID} from "../../services/productService.js";
import {getAllCategories} from "../../services/categoryService.js";
import {getFilesProductByType, create, update, deleteFileDB} from "../../services/filesService.js";
import {uploadFile} from "../../services/cloudinaryService.js";

const router = useRouter();
const idProducto = router.currentRoute.value.params.id;
const nombre = ref('')
const precio = ref('')
const stock = ref('')
const descripcion = ref('')
const estado = ref('')
const categorias = ref([])
const selectedCategoria = ref(null);

//variables de archivo
const portadaURL = ref(null);
const galeriaURLs = ref([]);


onMounted(async () => {
  const response1 = await getAllCategories();
  categorias.value = response1.data;

  const response = await getProductByID(idProducto);
  console.log(response.data);
  nombre.value = response.data.nombre;
  precio.value = response.data.precio;
  stock.value = response.data.stock;
  selectedCategoria.value = response.data.idCategoria;
  descripcion.value = response.data.descripcion;
  estado.value = response.data.estado;
  
  const portadaResponse = await getFilesProductByType(idProducto, "PORTADA");
    portadaURL.value = {url: portadaResponse.data[0].url, id: portadaResponse.data[0].id};

  const galeriaResponse = await getFilesProductByType(idProducto, "ARCHIVO");
    galeriaURLs.value = galeriaResponse.data.map(file => ({
      id: file.id,
      idProducto: file.idProducto,
      nombre: file.nombre,
      tipo: file.tipo,
      url: file.url,
    }));
});


watch(selectedCategoria, (newValue) => {
  console.log('selectedCategoria:', newValue);
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

    await editProduct(data);
}

const onPortadaSelected = async (event) => {
  const file = event.target.files[0];
  if (file && file.type.startsWith("image/")) {

    // Subir nueva portada
    const uploadResponse = await uploadFile(file);

    // Actualizar base de datos
    const data = {
      id: portadaURL.value.id,
      idProducto: idProducto,
      nombre: uploadResponse.public_id,
      tipo: "PORTADA",
      url: uploadResponse.url,
    };
    await update(data);
  }
};

const onArchivosSelected = async (event) => {
  const files = Array.from(event.target.files);
  for (const file of files) {
    if (file.type.startsWith("image/") || file.type.startsWith("video/")) {
      const uploadResponse = await uploadFile(file);

      galeriaURLs.value.push({
        idProducto,
        nombreMostrar: file.name,
        nombre: uploadResponse.public_id,
        tipo: "ARCHIVO",
        url: uploadResponse.url,
      });

      const data = {
        idProducto,
        nombre: uploadResponse.public_id,
        tipo: "ARCHIVO",
        url: uploadResponse.url,
      };
      const response = await create(data);
      console.log(response);
    }
    console.log(galeriaURLs.value);
  }
};

const removeArchivoURL = async (index) => {
  const archivoToRemove = galeriaURLs.value[index];
  try {
    // Eliminar de la base de datos
    console.log("Eliminando archivo:", archivoToRemove.id);
    const response = await deleteFileDB(archivoToRemove.id);
    console.log("respuesta: ", response.data);

    // Actualizar vista
    galeriaURLs.value.splice(index, 1);
  } catch (error) {
    console.error("Error eliminando archivo:", error);
  }
};

const volver = () => {
  router.push({ name: 'allProducts'});
};


</script>

<template>
  <div class="flex justify-center items-center h-full w-full bg-white p-4">
    <div class="bg-white p-8 rounded-lg border border-[#71b770] shadow-lg w-full flex flex-col h-5/6">
      <h1 class="text-center text-3xl font-bold text-[#71b770] mb-6">Editar Producto</h1>
      <form @submit.prevent="editarProducto" class="flex gap-4 h-full w-full">
        <div class="flex flex-col gap-4 h-full w-1/3">
          <div class="mb-4">
            <label for="nombre" class="block text-[#71b770] font-bold mb-2">Nombre:</label>
            <input
              type="text"
              v-model="nombre"
              id="nombre"
              placeholder="Nombre del producto"
              class="text-black w-full bg-white p-2 border border-gray-300 rounded"
            />
          </div>

          <div class="flex gap-4 mb-4">
            <div class="flex-grow">
              <label for="precio" class="block text-[#71b770] font-bold mb-2">Precio:</label>
              <input
                type="number"
                v-model="precio"
                id="precio"
                placeholder="Precio del producto"
                inputmode="decimal"
                min="0"
                class="bg-white text-black w-full p-2 border border-gray-300 rounded"
              />
            </div>
            <div class="flex-grow">
              <label for="stock" class="block text-[#71b770] font-bold mb-2">Stock:</label>
              <input
                type="number"
                v-model="stock"
                id="stock"
                placeholder="Cantidad en stock"
                step="1"
                class="bg-white text-black w-full p-2 border border-gray-300 rounded"
              />
            </div>
          </div>

          <div class="flex gap-4 mb-4">
            <div class="flex-grow">
              <label for="estado" class="block text-[#71b770] font-bold mb-2">Estado:</label>
              <select
                v-model="estado"
                id="estado"
                class="w-full p-2 border border-gray-300 rounded bg-white"
              >
                <option disabled value="">Seleccionar estado</option>
                <option value="disponible">Disponible</option>
                <option value="no disponible">No Disponible</option>
              </select>
            </div>
            <div class="flex-grow">
              <label for="categoria" class="block text-[#71b770] font-bold mb-2">Categoría:</label>
              <select
                v-model="selectedCategoria"
                id="categoria"
                class="text-black w-full p-2 border border-gray-300 rounded bg-white"
              >
                <option disabled value="">Seleccionar categoría</option>
                <option v-for="cat in categorias" :key="cat.id" :value="cat.id">{{ cat.nombre }}</option>
              </select>
            </div>
          </div>

          <div class="mb-4">
            <label for="descripcion" class="block text-[#71b770] font-bold mb-2">Descripción:</label>
            <textarea
              v-model="descripcion"
              id="descripcion"
              placeholder="Descripción del producto"
              class="bg-white text-black w-full p-2 border border-gray-300 rounded resize-none"
            ></textarea>
          </div>

          <div class="flex justify-between gap-4">
            <button
              type="button"
              @click="volver"
              class="bg-[#71b770] text-white p-2 rounded w-full hover:bg-green-600"
            >
              Volver
            </button>
            <button
              type="submit"
              class="bg-[#71b770] text-white p-2 rounded w-full hover:bg-green-600"
            >
              Guardar Cambios
            </button>
          </div>
        </div>

        <div class="h-full w-2/3 border-l border-[#71b770] p-4 flex flex-col gap-4">
          <!-- Portada -->
          <div class="mb-4 w-full flex items-center gap-4">
            <label for="portada" class="block text-[#71b770] font-bold mb-2">Portada:</label>
            <input
              type="file"
              @change="onPortadaSelected"
              id="portada"
              accept="image/*"
              class="bg-white text-black w-full p-2 border border-gray-300 rounded"
            />
          </div>
          <div v-if="portadaURL" class="flex items-center justify-center mb-4">
            <img
              :src="portadaURL.url"
              alt="Portada actual"
              class="max-h-40 object-cover border border-gray-300 rounded"
            />
          </div>

          <!-- Archivos -->
          <div class="mb-4">
            <label for="galeria" class="block text-[#71b770] font-bold mb-2">Archivos:</label>
            <input
              type="file"
              id="galeria"
              accept="image/*,video/*"
              multiple
              @change="onArchivosSelected"
              class="bg-white text-black w-full p-2 border border-gray-300 rounded"
            />
          </div>
          <div v-if="galeriaURLs.length" class="flex flex-wrap gap-4 overflow-x-auto ">
            <div
              v-for="(archivo, index) in galeriaURLs"
              :key="archivo.id"
              class="flex flex-col items-center gap-2"
            >
              <img
                v-if="archivo.url.endsWith('.jpg') || archivo.url.endsWith('.png')"
                :src="archivo.url"
                :alt="archivo.nombre"
                class="max-w-24 max-h-24 object-cover border border-gray-300 rounded"
              />
              <video
                v-else
                :src="archivo.url"
                class="max-w-48 max-h-24 border border-gray-300 rounded"
              ></video>
              <button
                @click="removeArchivoURL(index)"
                class="bg-red-500 text-white p-2 rounded hover:bg-red-700"
              >
                Eliminar
              </button>
            </div>
          </div>
        </div>
      </form>
    </div>
  </div>
</template>