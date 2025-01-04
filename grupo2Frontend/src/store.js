import { createStore } from "vuex";
import VuexPersist from "vuex-persist";

const vuexPersist = new VuexPersist({
  key: "my-app",
  storage: window.localStorage,
});

export default createStore({
  state: {
    user: null,
    login: false,
    carrito: [],
  },
  mutations: {
    setUser(state, user) {
      state.user = user;
    },
    clearUser(state) {
      state.user = null;
    },
    login(state) {
      state.login = true;
    },
    logout(state) {
      state.login = false;
    },
    addProductoToCarrito(state, producto) {
      const existingProduct = state.carrito.find((item) => item.id === producto.id);
      if (existingProduct) {
        existingProduct.cantidad += producto.cantidad;
      } else {
        state.carrito.push(producto);
      }
    },
    RemoveProductoFromCarrito(state, productId) {
      state.carrito = state.carrito.filter((item) => item.id !== productId);
    },
    ActualizarCarrito(state, { productId, cantidad }) {
      const product = state.carrito.find((item) => item.id === productId);
      if (product) {
        product.cantidad = cantidad;
        if (product.cantidad <= 0) {
          state.carrito = state.carrito.filter((item) => item.id !== productId);
        }
      }
    },
    clearCarrrito(state) {
      state.carrito = [];
    },
  },
  actions: {
    addProductoToCarrito({ commit }, producto) {
      commit("addProductoToCarrito", producto);
    },
    RemoveProductoFromCarrito({ commit }, productId) {
      commit("RemoveProductoFromCarrito", productId);
    },
    ActualizarCarrito({ commit }, payload) {
      commit("ActualizarCarrito", payload);
    },
    clearCarrrito({ commit }) {
      commit("clearCarrrito");
    },
    setUser({ commit }, user) {
      commit("setUser", user);
    },
    clearUser({ commit }) {
      commit("clearUser");
    },
    login({ commit }) {
      commit("login");
    },
    logout({ commit }) {
      commit("logout");
    },
  },
  getters: {
    getUser: (state) => state.user,
    getLogin: (state) => state.login,
    getCarrito: (state) => state.carrito,
    carritoTotalPrice: (state) =>
      state.carrito.reduce((total, item) => total + item.precio_unitario * item.cantidad, 0),
  },
  plugins: [vuexPersist.plugin],
});
