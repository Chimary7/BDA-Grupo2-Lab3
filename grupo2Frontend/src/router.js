import { createRouter, createWebHistory } from 'vue-router';
import Register from './components/client/Register.vue'
import HelloWorld from "./components/HelloWorld.vue";

const routes= [
    {
        path: '/',
        name: 'home',
        component: HelloWorld
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