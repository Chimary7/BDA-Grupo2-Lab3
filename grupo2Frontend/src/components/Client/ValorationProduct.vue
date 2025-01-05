<template>
  <div class="bg-white min-h-screen mt-16 flex justify-center">
    <div class="p-6 mb-4 w-1/3">
      <div class="card bg-white p-4 border border-[#71b770] shadow-lg rounded min-h-card">
        <h2 class="text-[#71B770] text-2xl font-bold mb-4">Evaluar Producto</h2>
        <form @submit.prevent="submitEvaluation">
          <div class="mb-4">
            <label for="valoracion" class="block text-gray-700 font-bold mb-2">Valoración:</label>
            <select id="valoracion" v-model="newValoracion.valoracion" class="shadow appearance-none bg-white border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline" required>
              <option value="" disabled selected>Selecciona una valoración</option>
              <option value="1">1</option>
              <option value="2">2</option>
              <option value="3">3</option>
              <option value="4">4</option>
              <option value="5">5</option>
            </select>
          </div>
          <div class="mb-4">
            <label for="comentario" class="block text-gray-700 font-bold mb-2">Comentario:</label>
            <textarea id="comentario" v-model="newValoracion.comentario" class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline bg-white " required></textarea>
          </div>
          <button type="submit" class="bg-[#71b770] text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline">Enviar Evaluación</button>
        </form>
      </div>
    </div>
    <div class="p-6 mb-4 w-1/3">
      <div class="card bg-white p-4 border border-[#71b770] shadow-lg rounded min-h-card">
        <h2 class="text-[#71B770] text-2xl font-bold mb-4">Valoraciones del Producto</h2>
        <div v-if="loading" class="text-center">Cargando valoraciones...</div>
        <div v-else>  
          <div v-if="error" class="error text-red-600 font-semibold">{{ error }}</div>
          <div v-else>
            <div v-for="valoracion in valoraciones" :key="valoracion.id" class="valoracion border border-gray-300 rounded p-4 mb-4">
              <!-- <p><strong>ID Valoración:</strong> {{ valoracion.id }}</p>
              <p><strong>ID Cliente:</strong> {{ valoracion.idCliente }}</p>
              <p><strong>ID Producto:</strong> {{ valoracion.idProducto }}</p> -->
              <p><strong>ID Cliente:</strong> {{ valoracion.idCliente }}</p>
              <p><strong>Valoración:</strong> {{ valoracion.valoracion }}</p>
              <p><strong>Comentario:</strong> {{ valoracion.comentario }}</p>
            </div>
            <div v-if="promedio" class="promedio text-lg font-bold mt-4">
              <strong>Valoración Promedio:</strong> {{ promedio.toFixed(1) }}
            </div>
            <div v-else class="mt-4 text-sm text-gray-500">No hay valoraciones para mostrar el promedio.</div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { getValorationsByIdProducto, getPromedioValoracionProducto, createValoracion } from '../../services/ValorationService';
import { useStore } from 'vuex';

export default {
  props: {
    idProducto: {
      type: String,
      required: true,
    },
  },
  data() {
    return {
      valoraciones: [],
      promedio: null,
      loading: true,
      error: null,
      newValoracion: {
        valoracion: '',
        comentario: ''
      }
    };
  },
  methods: {
    async fetchValoraciones() {
      this.loading = true;
      this.error = null;
      try {
        const valoracionesData = await getValorationsByIdProducto(this.idProducto);
        if (valoracionesData && valoracionesData.length > 0) {
          this.valoraciones = valoracionesData;
        } else {
          this.valoraciones = [];
          this.error = "No se encontraron valoraciones para este producto.";
        }

        const promedioData = await getPromedioValoracionProducto(this.idProducto);
        if (promedioData) {
          this.promedio = promedioData;
        } else {
          this.promedio = 0;
        }
      } catch (err) {
        this.error = 'Error al cargar las valoraciones: ' + err.message;
      } finally {
        this.loading = false;
      }
    },
    //-----------------------------------------------------------------------------------------------------------------------------------------------------------------------
    // APLICAR EVALUACIÓN DEL PRODUCTO
    async submitEvaluation() {
      try {
        const User = this.$store.getters.getUser; // Obtener el usuario autenticado
        const valoracionData = {
          idCliente: User.id_user, // Usar el ID del cliente autenticado
          idProducto: this.idProducto,
          valoracion: this.newValoracion.valoracion,
          comentario: this.newValoracion.comentario
        };
        await createValoracion(valoracionData);
        this.fetchValoraciones(); // Refresca las valoraciones después de enviar la nueva
      } catch (error) {
        console.log("Error al enviar la evaluación: ", error.message);
      }
    }
    //-----------------------------------------------------------------------------------------------------------------------------------------------------------------------
  },
  mounted() {
    this.fetchValoraciones();
  },
};
</script>

<style scoped>
.valoracion {
  border: 1px solid #ddd;
  padding: 10px;
  margin: 10px 0;
}
.promedio {
  font-weight: bold;
  margin-top: 20px;
}
.error {
  color: red;
}
</style>