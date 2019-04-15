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
import EditTrainerProfile from '@/views/EditTrainerProfile';

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
        name: 'search',
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
        path: '/trainer/profile/:TrainerID/edit',
        name: 'editTrainerProfile',
        component: EditTrainerProfile
      },

      
    ],
  });
  
  
  
  export default router;
  