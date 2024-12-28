<script setup>
import { ref } from 'vue';
import { useRouter } from 'vue-router';
import {createClient} from "../../services/UserService.js";

const router = useRouter();
const firstName = ref('')
const lastName = ref('')
const email = ref('')
const password = ref('')
const telefono = ref('')
const direccion = ref('')
const password2 = ref('')

const register = async () => {
  try{
    if(firstName.value === '' || lastName.value === '' || email.value === '' || password.value === '' || telefono.value === '' || direccion.value === '' || password2.value === ''){
      alert('Todos los campos son obligatorios')
      return
    }
    if(password.value !== password2.value){
      alert('Las contraseñas no coinciden')
      return
    }
    if(password.value.length < 4){
      alert('La contraseña debe tener al menos 4 caracteres')
      return
    }
    const data = {
      nombre: firstName.value + ' ' + lastName.value,
      email: email.value,
      password: password.value,
      telefono: telefono.value,
      direccion: direccion.value,
      role: 'CLIENT'
    }
    console.log(data)
    const response = await createClient(data);
    if(response.status === 201){
      alert('Usuario registrado correctamente')
      router.push({name: 'login'});
    }
    else{
      alert('Error al registrar el usuario')
      console.log(response)
    }
  }
  catch (error){
    console.log(error)
  }
}
</script>

<template>
  <div class="flex h-screen bg-color-secondary">
    <div class="flex-1 flex items-center justify-center">
      <div class="absolute w-48r h-48r bg-white rounded-full"></div>
      <img src="/images/imagen_register.png" alt="Imagen estática" class="max-w-full h-auto relative object-cover" />
    </div>
    <div class="flex-1 flex items-center justify-center ml-10">
      <form @submit.prevent="register" class="w-4/5 h-5/6 p-10 bg-white box-border flex flex-col items-center justify-center rounded-xl border-2 border-color-quaternary">
        <h2 class="text-6xl font-bold text-center mb-10 text-color-quinary">Crear una cuenta</h2>
        <div class="grid gap-4 mb-6 md:grid-cols-2 w-full">
          <input type="text" v-model="firstName" placeholder="Nombre" class="bg-white border-2 border-color-quaternary rounded-md h-12 text-black p-4"/>
          <input type="text" v-model="lastName" placeholder="Apellido" class="bg-white border-2 border-color-quaternary rounded-md h-12 text-black p-4"/>
        </div>
        <input type="text" v-model="email" placeholder="Correo electrónico" class="bg-white border-2 border-color-quaternary rounded-md h-12 w-full mb-6 text-black p-4"/>
        <input type="text" v-model="direccion" placeholder="Dirección" class="bg-white border-2 border-color-quaternary rounded-md h-12 w-full mb-6 text-black p-4"/>
        <input type="text" v-model="telefono" placeholder="Telefono" class="bg-white border-2 border-color-quaternary rounded-md h-12 w-full mb-6 text-black p-4"/>
        <div class="grid gap-4 mb-6 md:grid-cols-2 w-full">
          <input type="password" v-model="password" placeholder="Contraseña" class="bg-white border-2 border-color-quaternary rounded-md h-12 text-black p-4"/>
          <input type="password" v-model="password2" placeholder="Repetir contraseña" class="bg-white border-2 border-color-quaternary rounded-md h-12 text-black p-4"/>
        </div>
        <p class="text-color-quinary font-medium text-center mb-6">¿Ya tienes una cuenta? <router-link :to="{name: 'Login'}" class="text-color-quinary font-semibold px-2 hover:font-bold hover:text-color-quinary">Inicia sesión</router-link></p>
        <button type="submit" class="mt-1 p-4 w-full bg-color-quinary text-white rounded-lg shadow-lg text-lg font-semibold border-2 border-color-quinary hover:bg-color-primary hover:text-color-quinary hover:border-color-quinary">Registrar</button>
      </form>
    </div>
  </div>
</template>