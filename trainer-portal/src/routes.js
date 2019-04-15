import Home     from '@/views/Home';

import Register from './components/Register.vue';
import Login    from './components/Login.vue';
import Student  from './components/Student.vue';
import Redirect from './components/Redirect.vue';
import Error    from './components/404.vue';

const routes = [
    { name: 'home'     , path: '/'           , component: Home      },
    { name: 'register' , path: '/register'   , component: Register  },
    { name: 'login'    , path: '/login'      , component: Login     },
    { name: 'student'  , path: '/student/:id', component: Student   },
    { name: 'redirect' , path: '/redirect'   , component: Redirect  },
    { name: '404'      , path: '/404'        , component: Error     },
];

const routes = [
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
      path: '/trainer/profile/:TrainerID/edit',
      name: 'editTrainerProfile',
      component: EditTrainerProfile
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
];



export default routes;