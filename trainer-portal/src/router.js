import Vue from 'vue';
import Router from 'vue-router';
import auth from './auth';
import Login from '@/views/Login';
import Register from '@/views/Register';
import Home from '@/views/Home';
import ListTrainers from '@/views/ListTrainers';
import TrainerProfile from '@/views/TrainerProfile';
import MessageDetail from '@/views/MessageDetail';
import WriteMessage from '@/views/WriteMessage';
import ClientProfile from '@/views/ClientProfile';
import ClientList from '@/views/ClientList';
import AddTrainer from '@/views/AddTrainer';
import TrainerList from '@/views/TrainerList';
import EditTrainerProfile from '@/views/EditTrainerProfile';
import WorkoutPlan from '@/views/WorkoutPlan';
import WorkoutPlans from '@/views/WorkoutPlans';

Vue.use(Router);

const router = new Router({
    mode: 'history',
    base: process.env.BASE_URL,
    routes: [
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
        path: '/',
        name: 'home',
        component: Home
      },
      {
        path: '/search',
        name: 'listTrainers',
        component: ListTrainers,
      },
      {
        path: '/client/profile/:ClientID',
        name: 'clientProfile',
        component: ClientProfile
      }, 
      {
        path: '/trainer/profile/:TrainerID',
        name: 'trainerProfile',
        component: TrainerProfile
      },
      {
        path: '/trainer/privatemessage/:MesssageID',
        name: 'trainerPM',
        component: MessageDetail
      },
      {
        path: '/trainer/writemessage',
        name: 'trainer',
        component: WriteMessage
      },
      {
        path: '/clientList',
        name: 'listClients',
        component: ClientList
      },
      {
        path: '/addTrainer/:TrainerID',
        name: 'addTrainer',
        component: AddTrainer
      },
      {
        path: '/trainerList',
        name: 'listTrainers',
        component: TrainerList
      },
      {
        path: '/trainer/profile/:TrainerID/edit',
        name: 'editTrainerProfile',
        component: EditTrainerProfile
      },
      {
        path: '/workout/:UserID',
        name: 'listWorkoutPlans',
        component: WorkoutPlan
      },
      {
        path: '/workoutPlans/:UserID',
        name: 'listWorkoutPlans',
        component: WorkoutPlans
      },
      {
        path: '/workoutPlan/:WorkoutPlanID',
        name: 'workoutPlan',
        component: WorkoutPlan
      },
    ],
  });
  
  router.beforeEach((to, from, next) => {
    // redirect to login page if not logged in and trying to access a restricted page
    const publicPages = ['/login', '/register'];
    const authRequired = !publicPages.includes(to.path);

    const loggedIn = auth.getUser();
  
    if (authRequired && !loggedIn) {
      console.log('if (authRequired && !loggedIn)')
      console.table(to);
      return next('/login');
    }
  
    next();
  });
  
  export default router;
  