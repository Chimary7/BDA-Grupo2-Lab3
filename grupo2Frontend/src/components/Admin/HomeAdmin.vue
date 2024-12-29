<template>
    <div class="bg-white min-h-screen">
        <NavbarAdmin />
        <router-view />
        <h1 class="text-[#71B770]">Productos:</h1>
    </div>
</template>

<script>
import NavbarAdmin from './NavbarAdmin.vue';

export default {
    components: {
        NavbarAdmin
    }
}
</script>