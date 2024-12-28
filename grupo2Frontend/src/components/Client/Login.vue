<script setup>
import LoginImage from '../../assets/login image.png'
</script>

<template>
    <div class="h-full w-full bg-white flex items-center justify-between">
        <div class="text-black bg-transparent w-1/2 h-full flex items-center justify-center">
            <div class="bg-color-secondary w-4/5 h-5/6 rounded-lg flex flex-col items-center justify-center border-2 border-color-quaternary">
                <form action="" class="w-full h-full flex flex-col items-center justify-center">
                    <h1 class="text-7xl text-color-quinary font-bold p-8 w-full h-1/6 text-center flex items-center justify-center">Login</h1>
                    <div class="w-5/6 mb-6">
                        <label for="email" class="block text-xl font-medium text-color-quinary mb-1 pl-1 flex">Email</label>
                        <input type="email" id="email" class="mt-2 p-4 w-full border border-color-quinary rounded-lg shadow-sm text-lg bg-white text-color-secondary" placeholder="email@example.cl">
                    </div>
                    <div class="w-5/6 mb-6">
                        <label for="password" class="block text-xl font-medium text-color-quinary mb-1 pl-1 flex px-4 w-full">Password</label>
                        <input type="password" id="password" class="mt-2 p-4 w-full border border-color-quinary rounded-lg shadow-sm text-lg bg-white text-color-secondary" placeholder="********">
                    </div>
                    <div class="w-5/6 flex text-sm">
                        <span class="text-color-quinary font-medium">no tienes una cuenta?</span>
                        <a href="#" class="text-color-quinary font-semibold px-2 hover:font-bold hover:text-color-quinary">Regístrate</a>
                    </div>
                    <button type="submit" class="mt-8 p-4 w-5/6 bg-color-quinary text-white rounded-lg shadow-lg text-lg font-semibold border-2 border-color-quinary hover:bg-color-primary hover:text-color-quinary hover:border-color-quinary">Iniciar sesión</button>
                </form>
            </div>
        </div>
        <div class="text-black w-1/2 h-full flex items-center justify-center">
            <div class="absolute w-48r h-48r bg-color-primary rounded-full"></div>
            <img :src="LoginImage" alt="Login Image" class="w-4/5 h-5/6 relative object-cover">
        </div>
    </div>
</template>