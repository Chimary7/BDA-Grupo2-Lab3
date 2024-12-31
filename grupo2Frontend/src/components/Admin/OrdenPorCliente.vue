<template>
    <div class="bg-white min-h-screen">
        <div class="p-6">
            <h1 class="text-[#71B770] text-2xl font-bold mb-4">Órdenes Por Cliente</h1>

            <!-- Formulario para buscar órdenes -->
            <div class="mb-4">
                <label for="idCliente" class="block text-gray-500 font-semibold">ID del Cliente:</label>
                <input
                    v-model="idCliente"
                    type="text"
                    id="idCliente"
                    class="border border-gray-100 rounded p-2 w-1/6 mt-1"
                    placeholder="Ej: ID Cliente: 12345"
                />
                <button
                    @click="fetchOrdenes"
                    class="bg-[#71B770] text-white px-4 py-2 mt-2 rounded hover:bg-[#5da861] transition"
                >
                    Buscar Órdenes
                </button>
            </div>

            <!-- Mostrar órdenes -->
            <div v-if="ordenes.length > 0" class="mt-6">
                <h2 class="text-lg font-semibold mb-2">Órdenes del Cliente</h2>
                <ul class="border border-green-200 rounded p-2 w-1/2 mx-auto">
                    <li
                        v-for="orden in ordenes"
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
            </div>

            <div v-else-if="searchCompleted" class="mt-6">
                <p class="text-red-500">No se encontraron órdenes para este cliente.</p>
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
        };
    },
    methods: {
        async fetchOrdenes() {
            this.searchCompleted = false; 
            try {
                const idCliente = this.idCliente.trim();
                const response = await getOrdenesByIdCliente(idCliente);
                this.ordenes = response;
                this.searchCompleted = true;
            } catch (error) {
                console.error('Error al buscar órdenes:', error);
                this.ordenes = [];
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
    },
};
</script>