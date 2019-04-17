import ListTrainers from '@/views/ListTrainers';
import TrainerProfile from '@/views/trainer/Profile';
import MessageDetail from '@/views/MessageDetail';
import WriteMessage from '@/views/WriteMessage';
import ClientProfile from '@/views/ClientProfile';
import EditTrainerProfile from '@/views/EditTrainerProfile';
import ClientList from '@/views/ClientList';
import AddTrainer from '@/views/AddTrainer';

import EditTrainerProfile from '@/views/EditTrainerProfile';
import WorkoutPlan from '@/views/WorkoutPlan';
import WorkoutPlans from '@/views/WorkoutPlans';

Vue.use(Router);

const router = new Router({
    mode: 'history',
    base: process.env.BASE_URL,
    routes: [
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
        path: '/trainer/profile/:TrainerID/edit',
        name: 'editTrainerProfile',
        component: EditTrainerProfile
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
  
  export default router;
  