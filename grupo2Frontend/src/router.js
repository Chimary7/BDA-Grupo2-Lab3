import { createRouter, createWebHistory } from 'vue-router';
import Home from './components/client/HomeClient.vue';
import Register from './components/client/Register.vue';
import Login from './components/client/Login.vue';
import Products from './components/client/ProductContainer.vue';
import AllProducts from './components/client/AllProductsClient.vue';
import ProductsCategory from './components/client/ProductsCategory.vue';
import OrderList from './components/client/OrderList.vue';
import DetallesOrden from './components/client/OrdenDetaill.vue';
import CrearProducto from './components/Admin/CrearProducto.vue';
import OrdenPorCliente from './components/Admin/OrdenPorCliente.vue';
import EditarProducto from "./components/Admin/EditarProducto.vue";
import HomeAdmin from "./components/Admin/HomeAdmin.vue";
import AllProductsAdmin from "./components/Admin/AllProductsAdmin.vue";
import DetalleEntrega from './components/Admin/DetalleEntrega.vue';

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
                path: 'products',
                name: 'Products',
                component: Products,
                children: [
                    {
                        path: 'all',
                        name: 'All',
                        component: AllProducts
                    },
                    {
                        path: 'category/:categoryName',
                        name: 'Category',
                        component: ProductsCategory,
                        props: true
                    }
                ]
            },
            {
                path: 'historial',
                name: 'OrderList',
                component: OrderList
            },
            {
                path: 'detalles/:id',
                name: 'DetailsOrder',
                component: DetallesOrden,
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
    },
    {
        path: '/HomeAdmin',
        name: 'HomeAdmin',
        component: HomeAdmin,
        children: [
            {
                path: 'allProducts',
                name: 'allProducts',
                component: AllProductsAdmin
            },
            {
                path: 'CrearProducto',
                name: 'CrearProducto',
                component: CrearProducto
            },
            {
                path: 'EditarProducto/:id',
                name: 'EditarProducto',
                component: EditarProducto
            },
            {
                path: 'OrdenPorCliente',
                name: 'OrdenPorCliente',
                component: OrdenPorCliente
            },
            {
                path: '/entrega/:id',
                name: 'DetalleEntrega',
                component: DetalleEntrega
            }
        ]
    }
];

const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes,
});

export default router;