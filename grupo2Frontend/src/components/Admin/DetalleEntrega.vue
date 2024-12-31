<template>
    <div class="bg-white min-h-screen p-6">
        <h1 class="text-[#71B770] text-2xl font-bold mb-4">Detalle de Entrega</h1>
        <div v-if="entrega">
            <p><strong>ID Entrega:</strong> {{ entrega.id }}</p>
            <p><strong>ID Zona:</strong> {{ entrega.idZona }}</p>
            <p><strong>ID Cliente:</strong> {{ entrega.idCliente }}</p>
            <p><strong>Dirección:</strong> {{ entrega.direccion }}</p>
            <p><strong>Estado:</strong> {{ entrega.estado }}</p>
        </div>
        <div v-else>
            <p>Cargando detalles...</p>
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
    }
};
</script>
