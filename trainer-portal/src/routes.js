
import Home           from '@/views/Home';
import Login          from '@/views/Login';
      
import TrainerProfile from '@/views/trainer/Profile';      
      
import Register       from './components/Register.vue';
      
import Student        from './components/Student.vue';
import Redirect       from './components/Redirect.vue';
import Error          from './components/404.vue';



const routes = [
    { name: 'home'            , path: '/', alias: 'default'          , component: Home      },
    { name: 'login'           , path: '/login'                       , component: Login     },
    { name: 'trainer profile' , path: '/trainer/profile/:TrainerID'  , component: TrainerProfile },

    { name: 'register'        , path: '/register'                    , component: Register  },
                        
    { name: 'student'         , path: '/student/:id'                 , component: Student   },
    { name: 'redirect'        , path: '/redirect'                    , component: Redirect  },
    { name: '404'             , path: '/404'                         , component: Error     },
];
/*
    {
      path: ,
      name: 'trainerProfile',
      component: TrainerProfile
    },

const aaa = [
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
*/


export default routes;