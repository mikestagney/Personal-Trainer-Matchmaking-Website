
import Home           from '@/views/Home';
import Login          from '@/views/Login';
import Register       from '@/views/Register';

import Directory      from '@/views/TrainerDirectory';

import TrainerProfile from '@/views/trainer/Profile';
import EditTrainerProfile from '@/views/EditTrainerProfile';
import AddTrainer from '@/views/AddTrainer';

import ClientList from '@/views/ClientList';

import ClientProfile  from '@/views/client/Profile';   

import Redirect       from './components/Redirect.vue';
import Error          from './components/404.vue';

import MessageDetail from '@/views/MessageDetail';

import WorkoutPlan         from '@/views/WorkoutPlan';
import WorkoutPlans        from '@/views/WorkoutPlans';
import CreateWorkoutPlan   from '@/views/CreateWorkoutPlan';

const routes = [
    /* authentication pages */
    { name: 'home'            , path: '/', alias: 'default'          , component: Home      },
    { name: 'login'           , path: '/login'                       , component: Login     },
    { name: 'register'        , path: '/register'                    , component: Register  },

    /* public pages */
    { name: 'dir-trainer'     , path: '/directory'                   , component: Directory  },

    /* trainer */
    { name: 'trainer-profile' ,    path: '/trainer/profile/:TrainerID'      , component: TrainerProfile },
    { name: 'client-list',         path: '/clientList'                      , component: ClientList },
    { name: 'editTrainerProfile' , path: '/trainer/profile/:TrainerID/edit' , component: EditTrainerProfile },

    /* client */
    { name: 'client-profile'  , path: '/client/profile/:ClientID'    , component: ClientProfile },
    { name: 'addTrainer' ,         path: '/addTrainer/:TrainerID'           , component: AddTrainer },

    { name: 'redirect'        , path: '/redirect'                    , component: Redirect  },
    { name: '404'             , path: '/404'                         , component: Error     },

    /* message */
    {name: 'message-detail'   , path: '/message/:MesssageID'         , component: MessageDetail },

    /* workout plan */
    { name: 'workout-plan'   , path: '/workoutPlan/:WorkoutPlanID'         , component: WorkoutPlan },
    { name: 'workout-plans'   , path: '/workoutPlans/:UserID'              , component: WorkoutPlans },
    { name: 'create-workout-plan'   , path: '/createWorkoutPlan/:ClientID' , component: CreateWorkoutPlan },
];
export default routes;