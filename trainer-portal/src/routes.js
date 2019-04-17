
import Home           from '@/views/Home';
import Login          from '@/views/Login';
import Register       from '@/views/Register';

import Directory      from '@/views/TrainerDirectory';

import TrainerProfile from '@/views/trainer/Profile';      

import ClientProfile  from '@/views/client/Profile';   

import Redirect       from './components/Redirect.vue';
import Error          from './components/404.vue';

import WorkoutPlan   from '@/views/WorkoutPlan';
import WorkoutPlans   from '@/views/WorkoutPlans';

const routes = [
    /* authentication pages */
    { name: 'home'            , path: '/', alias: 'default'          , component: Home      },
    { name: 'login'           , path: '/login'                       , component: Login     },
    { name: 'register'        , path: '/register'                    , component: Register  },

    /* public pages */
    { name: 'dir-trainer'     , path: '/directory'                   , component: Directory  },

    /* trainer */
    { name: 'trainer-profile' , path: '/trainer/profile/:TrainerID'  , component: TrainerProfile },

    /* client */
    { name: 'client-profile'  , path: '/client/profile/:ClientID'    , component: ClientProfile },

    { name: 'redirect'        , path: '/redirect'                    , component: Redirect  },
    { name: '404'             , path: '/404'                         , component: Error     },

    /* workout plan */
    { name: 'workout-plan'   , path: '/workoutPlans/:WorkoutPlanID'        , component: WorkoutPlans },
    { name: 'workout-plans'   , path: '/workoutPlans/:UserID'              , component: WorkoutPlan },
];
export default routes;