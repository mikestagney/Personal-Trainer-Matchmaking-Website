import Vue  from 'vue';
import App  from './App.vue';
import auth from './auth';
import VueRouter      from 'vue-router';
import BootstrapVue   from 'bootstrap-vue';
import VueHighlightJS from 'vue-highlightjs';

import routes from './routes';

Vue.config.productionTip = true;

Vue.use(VueRouter)
Vue.use(BootstrapVue);
Vue.use(VueHighlightJS);
 
import 'bootstrap/dist/css/bootstrap.css';
import 'bootstrap-vue/dist/bootstrap-vue.css';

const router = new VueRouter({mode: 'history', routes});
router.beforeEach((to, from, next) => {
  // redirect to login page if not logged in and trying to access a restricted page
  const publicPages = ['/', '/login', '/register'];

  const authRequired = !publicPages.includes(to.path);
  const loggedIn = auth.getUser();

  return ( authRequired && !loggedIn )? next('/login'): next();
});

new Vue({
  router,
  render: h => h(App),
}).$mount('#app')
