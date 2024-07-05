import Vue from 'vue'
import VueRouter from 'vue-router'


Vue.use(VueRouter)

const routes = [
  {
    path: '/login',
    name: 'Login',
    component: () => import('../views/LoginView.vue')
  },
  {
    path: '/',
    redirect: '/login',
  },
  {
    path: '/home',
    name: 'Home',
    component: () => import('../views/HomeView.vue'),
    redirect:'/welcome',
    children:[
      {
        path: '/welcome',
        name: 'Welcome',
        component: () => import('../views/IndexView.vue'),
      },
      {
        path: '/food',
        name: 'Food',
        component: () => import('../views/FoodView.vue'),
      },
      {
        path: '/cart',
        name: 'Cart',
        component: () => import('../views/CartView.vue'),
      },
      {
        path: '/order',
        name: 'Order',
        component: () => import('../views/OrderView.vue'),
      },
      {
        path: '/sales',
        name: 'Sales',
        component: () => import('../views/SalesView.vue'),
      },
      {
        path: '/user',
        name: 'Admin',
        component: () => import('../views/AdminView.vue'),
      },
    ]
  },
]

const router = new VueRouter({
  routes
})

export default router
