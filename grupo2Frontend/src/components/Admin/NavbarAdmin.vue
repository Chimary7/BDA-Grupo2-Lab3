<script setup>
import Logo from '../../assets/LOGO.png';
</script>

<template>

    <div class="h-24 w-full bg-[#71B770] flex justify-center items-center text-white">
        <div class="flex justify-between items-center w-full h-full px-10">
            
            <div class="h-full w-auto flex items-center justify-center">
                <img :src="Logo" alt="logo" class="h-full w-full p-1" />
                <p class="w-auto h-full flex items-center font-bold text-xl">Ecommerce</p>
            </div>

          <!--
            <div class="h-full p-2">
                <Public />
            </div>
            -->

            <div class="hidden w-full md:block md:w-auto" id="navbar-default">
                <router-link to="/HomeAdmin" class="text-white bg-[#71B770] hover:bg-green-800 focus:ring-4 focus:ring-green-300 font-medium rounded-lg text-sm px-5 py-2.5 me-2 mb-2 dark:bg-green-600 dark:hover:bg-green-700 focus:outline-none dark:focus:ring-green-800 border border-white" active-class="bg-green-800">Home Administrador</router-link>
                <router-link to="/CrearProducto" class="text-white bg-[#71B770] hover:bg-green-800 focus:ring-4 focus:ring-green-300 font-medium rounded-lg text-sm px-5 py-2.5 me-2 mb-2 dark:bg-green-600 dark:hover:bg-green-700 focus:outline-none dark:focus:ring-green-800 border border-white" active-class="bg-green-800">Crear Producto</router-link>
                <router-link to="/OrdenPorCliente" class="text-white bg-[#71B770] hover:bg-green-800 focus:ring-4 focus:ring-green-300 font-medium rounded-lg text-sm px-5 py-2.5 me-2 mb-2 dark:bg-green-600 dark:hover:bg-green-700 focus:outline-none dark:focus:ring-green-800 border border-white" active-class="bg-green-800">Ordenes por Cliente</router-link>
            </div>
          
        </div>
    </div>

</template>