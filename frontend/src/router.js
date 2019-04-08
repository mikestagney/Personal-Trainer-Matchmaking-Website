import Vue from 'vue';
import Router from 'vue-router';
import ListSnippets from '@/views/ListSnippets';
import ViewSnippet from '@/views/ViewSnippet';
import SaveSnippet from '@/views/SaveSnippet';
import Login from '@/views/Login';
import ClientRegister from '@/views/ClientRegister';
import TrainerRegister from '@/views/TrainerRegister';
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
      path: '/snippet/:id',
      name: 'snippet',
      component: ViewSnippet,
      props: true,
    },
    {
      path: '/save/:id?',
      name: 'save',
      component: SaveSnippet,
    },
    {
      path: '/login',
      name: 'login',
      component: Login,
    },
    {
      path: '/clientregister',
      name: 'clientregister',
      component: ClientRegister,
    },
    {
      path: '/trainerregister',
      name: 'trainerregister',
      component: TrainerRegister,
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
