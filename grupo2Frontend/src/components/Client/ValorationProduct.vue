<template>
  <div class="bg-white min-h-screen mt-16">
    <div class="p-6 mb-4">        
      <div class="mb-4 card bg-white p-4 border border-[#71b770] shadow-lg rounded max-w-lg mx-auto"> 
        <h2 class="text-[#71B770] text-2xl font-bold mb-4">Valoraciones del Producto</h2>

        <div v-if="loading" class="text-center">Cargando valoraciones...</div>

        <div v-else>
          <div v-if="error" class="error text-red-600 font-semibold">{{ error }}</div>
          <div v-else>
            <div v-for="valoracion in valoraciones" :key="valoracion.id" class="valoracion border border-gray-300 rounded p-4 mb-4">
              <p><strong>ID Valoración:</strong> {{ valoracion.id }}</p>   
              <p><strong>ID Cliente:</strong> {{ valoracion.idCliente }}</p>
              <p><strong>ID Producto:</strong> {{ valoracion.idProducto }}</p>
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
import { getValorationsByIdProducto, getPromedioValoracionProducto } from '../../services/ValorationService';

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
    };
  },
  methods: {
    async fetchValoraciones() {
      this.loading = true;
      this.error = null;
      try {
        // Asegurarse de que las respuestas sean válidas
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
  },
  mounted() {
    this.fetchValoraciones(); // Llamada a la función de carga de valoraciones cuando el componente se monta
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