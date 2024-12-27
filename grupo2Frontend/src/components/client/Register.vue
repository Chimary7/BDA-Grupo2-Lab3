<script setup>
import { ref } from 'vue';
import { useRouter } from 'vue-router';

const router = useRouter();
const name = ref('')
const email = ref('')
const password = ref('')
const telefono = ref('')
const direccion = ref('')
const password2 = ref('')

const register = async () => {
  try{
    console.log("Hola")
  }
  catch (error){
    console.log(error)
  }
}
</script>


<template>
  <div class="absolute top-0 left-0 w-full h-full bg-cover flex items-center justify-end pr-10" style="background-image: url(grupo2Frontend/src/assets/register.png);">
    <div class="w-[650px] p-10 bg-white box-border flex flex-col items-center justify-center rounded-xl">
      <h2 class="text-3xl font-bold text-center  mb-10 text-red-600">Crear una cuenta</h2>
      <div class="grid gap-4 mb-6 md:grid-cols-2">
        <input type="text" id="nombre" placeholder="  Nombre" class="bg-white border border-red-600 rounded-md h-12"/>
        <input type="email" id="apellido" placeholder="  Apellido" class="bg-white border border-red-600 rounded-md h-12"/>
      </div>
      <input type="text" id="email" placeholder="  Correo electrónico" class="bg-white border border-red-600 rounded-md h-12 w-[460px] mb-6"/>
      <input type="text" id="direccion" placeholder="  Dirección" class="bg-white border border-red-600 rounded-md h-12 w-[460px] mb-6"/>
      <div class="grid gap-4 mb-6 md:grid-cols-2">
        <input type="text" id="contrasena" placeholder="  Contraseña" class="bg-white border border-red-600 rounded-md h-12"/>
        <input type="email" id="repetir_contrasena" placeholder="  Repetir contraseña" class="bg-white border border-red-600 rounded-md h-12"/>
      </div>
      <p class="text-red-600 text-sm text-center mb-6">¿Ya tienes una cuenta? <a href="#" class="underline">Inicia sesión aquí</a></p>
      <button type="submit" class="bg-red-600 text-white w-full py-2 rounded-lg hover:bg-red-700">Registrar</button>
    </div>

  </div>

</template>