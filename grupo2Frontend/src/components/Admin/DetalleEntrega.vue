<template>
    <div class="bg-white min-h-screen mt-16">
        <div class="p-24 mb-4"> 
        <div class="border border-[#71b770] p-4 rounded-md" style="width: 500px;">
                <h1 class="text-[#71B770] text-2xl font-bold mb-4">Detalle de Entrega</h1>
                <div v-if="entrega" class="card p-4 rounded-md border border-[#71b770] mx-auto" style="width: 300px;">
                    <p><strong>ID Entrega:</strong> {{ entrega.id }}</p>
                    <p><strong>ID Zona:</strong> {{ entrega.idZona }}</p>
                    <p><strong>ID Cliente:</strong> {{ entrega.idCliente }}</p>
                    <p><strong>Dirección:</strong> {{ entrega.direccion }}</p>
                    <p><strong>Estado:</strong> {{ entrega.estado }}</p>
                </div>
                <div v-else>
                    <p>No existe Entrega relacionada al ID del Cliente...</p>
                </div>
                <button @click="goBack" class="mt-4 bg-[#71b770] text-white p-2 rounded">Regresar</button>
            </div>
        </div>
    </div>
</template>

<script>
import { getEntregaById } from "../../services/EntregaService.js";

export default {
    data(){
        return {
            entrega: null,
            loading: true, 
        }
    },
    async created() {
        const idEntrega = this.$route.params.id;
        try {
            this.entrega = await getEntregaById(idEntrega);
        } catch (error) {
            console.error('Error al cargar detalles de entrega:', error);
        } finally {
            this.loading = false;
        }
    },
    methods: {
        goBack() {
            this.$router.push('/HomeAdmin/OrdenPorCliente');
        }
    }
};
</script>
