import { createRouter, createWebHistory } from 'vue-router';
import Home from './components/client/HomeClient.vue';
import Register from './components/client/Register.vue';
import Login from './components/client/Login.vue';
import Navbar from './components/client/Navbar.vue';
import AllProducts from './components/client/AllProductsClient.vue';

const routes= [
    {
        path: '/', redirect: { name: 'Home'}
    },
    {
        path: '/home',
        name: 'Home',
        component: Home,
        children: [
            {
                path: 'allproducts',
                name: 'AllProducts',
                component: AllProducts
            }
        ]
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
    }
];

const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes,
});

export default router;