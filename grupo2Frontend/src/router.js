import { createRouter, createWebHistory } from 'vue-router';
import Home from './components/client/HomeClient.vue';
import Register from './components/client/Register.vue';
import Login from './components/client/Login.vue';
import Admin from './components/Admin/HomeAdmin.vue';
import CrearProducto from './components/Admin/CrearProducto.vue';
import OrdenPorCliente from './components/Admin/OrdenPorCliente.vue';

const routes= [
    {
        path: '/',
        name: 'Home',
        component: Home
    },
    {
        path: '/login',
        name: 'Login',
        component: Login
    },
    {
        path: '/register',
        name: 'register',
        component: Register
    },
    {
        path: '/HomeAdmin',
        name: 'HomeAdmin',
        component: Admin,
        children: [
            {
                path: '/CrearProducto',
                name: 'CrearProducto',
                component: CrearProducto
            },
            {
                path: '/OrdenPorCliente',
                name: 'OrdenPorCliente',
                component: OrdenPorCliente
            }
        ]
    }
];

const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes,
});

export default router;