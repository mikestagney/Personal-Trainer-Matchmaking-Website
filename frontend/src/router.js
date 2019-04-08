import Vue from 'vue';
import Router from 'vue-router';
import Login from '@/views/Login';
import Register from '@/views/Register';
import UpdateTrainerProfile from '@/views/UpdateTrainerProfile';
import auth from './auth';

Vue.use(Router);

const router = new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: [
    {
      path: '/',
      name: 'home',
      component: ListSnippets,
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
    },
    {
      path: '/updateprofile/:id?',
      name: 'updatetrainerporfile',
      component: UpdateTrainerProfile,
    },
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
