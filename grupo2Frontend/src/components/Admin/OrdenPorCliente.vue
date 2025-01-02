<template>
    <div class="bg-white min-h-screen mt-16">
        <div class="p-6">        
            <!-- Formulario para buscar órdenes -->
            <div class="mb-4 card bg-white p-4 border border-[#71b770] shadow-lg rounded"> 
                <h1 class="text-[#71B770] text-2xl font-bold mb-4">Órdenes Por Cliente</h1>

                <label for="idCliente" class="block text-[#71b770] font-semibold">ID del Cliente:</label>
                <input
                    v-model="idCliente"
                    type="text"
                    id="idCliente"
                    class="border border-gray-300 rounded p-2 w-1/6 mt-1 bg-white"
                    placeholder="Ej: ID Cliente: 12345"
                    style="width: 200px;"
                />
                <div class="mt-2">
                    <button
                        @click="fetchOrdenes"
                        class="bg-[#71B770] text-white px-4 py-2 rounded hover:bg-[#5da861] transition"
                    >
                        Buscar Órdenes                        
                    </button>
                </div>

                <div v-if="mensajeError" class="mt-6 error-message">{{ mensajeError }}</div>

                <!-- Mostrar órdenes -->
                <div v-if="ordenes.length > 0" class="mt-6 card bg-gray-000 p-4 rounded">
                    <h2 class="text-lg font-semibold mb-2">Órdenes del Cliente</h2>
                    <ul class="border border-green-300 rounded p-2 w-4/4 mx-auto">
                        <li
                            v-for="orden in paginatedOrdenes"
                            :key="orden.id"
                            class="border-b last:border-none py-1"
                        >
                            <p><strong>ID Orden:</strong> {{ orden.idOrden }}</p>
                            <p><strong>Fecha de Orden:</strong> {{ formatDate(orden.fecha_orden) }}</p>
                            <p><strong>Estado:</strong> {{ orden.estado }}</p>
                            <p><strong>ID Entrega:</strong> {{ orden.id_entrega }}</p>
                            <p><strong>Total:</strong> ${{ orden.total }}</p>

                            <button
                                @click="verDetallesEntrega(String(orden.id_entrega))"
                                class="bg-blue-500 text-white px-2 py-1 mt-2 rounded hover:bg-blue-600 transition"
                            >
                                Ver detalles
                            </button>
                        </li>
                    </ul>
                    <div v-if="totalPages > 1" class="flex justify-between mt-4">
                        <div class="flex-1">
                            <button
                                v-if="currentPage > 1"
                                @click="prevPage"
                                class="bg-[#71b770] text-white px-8 py-2 rounded transition"
                            >
                                Anterior
                            </button>
                        </div>
                        <div class="flex-1 text-right">
                            <button
                                v-if="currentPage < totalPages"
                                @click="nextPage"
                                class="bg-[#71b770] text-white px-8 py-2 rounded transition"
                            >
                                Siguiente
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import NavbarAdmin from './NavbarAdmin.vue';
import { getOrdenesByIdCliente } from "../../services/OrdenService.js";
import { getEntregaById } from "../../services/EntregaService.js";

export default {
    data() {
        return {
            idCliente: '',
            ordenes: [], // Inicializar como un arreglo vacío
            searchCompleted: false,
            loading: false,
            currentPage: 1,
            itemsPerPage: 1,
            mensajeError: '' // Añadir propiedad para el mensaje de error
        };
    },
    computed: {
        paginatedOrdenes() {
            const start = (this.currentPage - 1) * this.itemsPerPage;
            const end = start + this.itemsPerPage;
            return this.ordenes.slice(start, end);
        },
        totalPages() {
            return Math.ceil(this.ordenes.length / this.itemsPerPage);
        }
    },
    methods: {
        async fetchOrdenes() {
            this.searchCompleted = false; 
            this.mensajeError = ''; // Resetear mensaje de error
            try {
                const idCliente = this.idCliente.trim();
                const response = await getOrdenesByIdCliente(idCliente);
                if (response.length === 0) {
                    this.mensajeError = 'No se encontraron órdenes para el ID ingresado.';
                }
                this.ordenes = response;
                this.searchCompleted = true;
            } catch (error) {
                console.error('Error al buscar órdenes:', error);
                this.ordenes = [];
                this.mensajeError = 'Error al buscar órdenes. Por favor, inténtelo de nuevo.';
                this.searchCompleted = true;
            }
        },
        formatDate(date) {
            const options = { year: 'numeric', month: 'long', day: 'numeric' };
            return new Date(date).toLocaleDateString('es-ES', options);
        },
        async verDetallesEntrega(idEntrega) {
            this.loading = true; // Activa el estado de carga
            
            try {
                const entrega = await getEntregaById(idEntrega);
                console.log('Detalles de la entrega:', entrega);
                this.$router.push({ name: 'DetalleEntrega', params: { id: idEntrega } });
            } catch (error) {
                console.error('Error al obtener detalles de la entrega:', error);
            } finally {
                this.loading = false;
            }
        
        },
        prevPage() {
            if (this.currentPage > 1) {
                this.currentPage--;
            }
        },
        nextPage() {
            if (this.currentPage < this.totalPages) {
                this.currentPage++;
            }
        }
    },
};
</script>

<style>
.error-message {
    color: red;
    font-weight: bold;
}
</style>