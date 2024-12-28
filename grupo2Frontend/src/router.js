import { createRouter, createWebHistory } from 'vue-router';
import Home from './components/Client/HomeClient.vue'
import Login from './components/Client/Login.vue';

const routes = [
    {
        path: '/',
        name: 'Home',
        component: Home
    },
    {
        path: '/login',
        name: 'Login',
        component: Login
    }
]

const router = createRouter({
    history: createWebHistory(),
    routes
})

export default router