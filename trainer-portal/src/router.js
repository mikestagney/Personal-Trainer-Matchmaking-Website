import Vue from 'vue';
import Router from 'vue-router';
import Home from '@/views/Home'
import Login from '@/views/Login';
import Register from '@/views/Register';
import auth from './auth';

Vue.use(Router);

const router = new Router({
    mode: 'history',
    base: process.env.BASE_URL,
    routes: [
      {
        path: '/',
        name: 'home',
        component: Home,
      },
      {
        path: '/login',
        name: 'login',
        component: Login,
      },
      {
        path: '/register',
        name: 'register',
        component: Register,
      }
    ],
  });
  
  router.beforeEach((to, from, next) => {
    // redirect to login page if not logged in and trying to access a restricted page
    const publicPages = ['/login', '/register'];
    const authRequired = !publicPages.includes(to.path);
    const loggedIn = auth.getUser();
  
    if (authRequired && !loggedIn) {
      return next('/login');
    }
  
    next();
  });
  
  export default router;
  