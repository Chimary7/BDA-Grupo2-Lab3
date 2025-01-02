<script setup>
import LoginImage from '../../assets/login image.png'
import { ref } from 'vue';
import { useRouter } from 'vue-router';
import { login, getUser } from '../../services/UserService.js';
import { useStore } from 'vuex';
import Alert from '../client/alert/MessageSucces.vue';

const store = useStore();
const router = useRouter();
const email = ref('');
const error = ref(false);
const password = ref('');
const success = ref(false);
const alertMessage = ref('');
const Login = async () => {
    error.value = false
    success.value = false
  try{
    if(email.value === '' || password.value === '' || email.value === null || password.value === null || email.length === 0 || password.length === 0){
      error.value = true
      return
    }
    const data = {
      email: email.value,
      password: password.value
    }
    const response = await login(data);
    if(response.status === 200){
      store.commit('setUser', response.data);
      store.commit('login');

      const idCliente = response.data.id_user;



      const userData = await getUser(idCliente);

      success.value = true
      alertMessage.value = 'se inicio sesión correctamente';
      setTimeout(() => {
        if(userData.data.role === 'ADMIN'){
          router.push({name: 'HomeAdmin'});
        } else {
          router.push({name: 'Home'});

        }
      }, 2000);
      console.log("inicia sesion correctamente")
    }
    else{
      error.value = true
      console.log(response)
    }
  }
  catch (error){
    console.log(error)
  }
}
</script>

<template>
    <div class="h-full w-full bg-white flex items-center justify-between">
      <Alert :message="alertMessage" v-if="success" />
        <div class="text-black bg-transparent w-1/2 h-full flex items-center justify-center">
            <div class="bg-color-secondary w-4/5 h-5/6 rounded-lg flex flex-col items-center justify-center border-2 border-color-quaternary">
                <form @submit.prevent="Login" class="w-full h-full flex flex-col items-center justify-center">
                    <h1 class="text-7xl text-color-quinary font-bold p-8 w-full h-1/6 text-center flex items-center justify-center">Login</h1>
                    <div v-if="error" class="w-5/6 bg-red-200 text-red-800 p-4 rounded-lg mb-6 border-2 border-red-400">Email o contraseña incorrectos</div>
                    <div class="w-5/6 mb-6">
                        <label for="email" class="block text-xl font-medium text-color-quinary mb-1 pl-1 flex">Email</label>
                        <input type="email" v-model="email" id="email" class="mt-2 p-4 w-full border-2 border-color-quinary rounded-lg shadow-sm text-lg bg-white text-black 
                        focus:border-color-quinary focus:ring-1 focus:ring-color-quinary focus:outline-none" placeholder="email@example.cl" autocomplete="email">
                    </div>
                    <div class="w-5/6 mb-6">
                        <label for="password" class="block text-xl font-medium text-color-quinary mb-1 pl-1 flex px-4 w-full">Contraseña</label>
                        <input type="password" v-model="password" id="password" class="mt-2 p-4 w-full border-2 border-color-quinary rounded-lg shadow-sm text-lg bg-white text-black 
                        focus:border-color-quinary focus:ring-1 focus:ring-color-quinary focus:outline-none" placeholder="********" autocomplete="current-password">
                    </div>
                    <div class="w-5/6 flex">
                        <span class="text-color-quinary font-medium">no tienes una cuenta?</span>
                        <router-link :to="{name:'register'}" class="text-color-quinary font-semibold px-2 hover:font-bold hover:text-color-quinary">Regístrate</router-link>
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