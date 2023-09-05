import LoginView from 'pages/LoginView.vue'
import RegisterView from 'pages/RegisterView.vue'
import DashboardView from 'pages/DashboardView.vue'
import { createRouter, createWebHistory } from 'vue-router';


const routes = [
  {
    path: '/',
    name: 'LoginView',
    component: LoginView
  },
  {
    path: '/register',
    name: 'RegisterView',
    component: RegisterView
  },
  {
    path: '/dashboard',
    name: 'DashboardView',
    component: DashboardView
  }

]
const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default routes
