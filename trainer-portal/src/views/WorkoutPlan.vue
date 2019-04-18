<template>
<default-layout>
    <div class="d-flex justify-content-center container ">
        <div class="card shadow bg-light border border-warning" style="width: 23rem;">
            <img class="card-img-top" src="">
        <div class="card-body text-center">
            <h5 class="card-title">{{workoutPlan.title}}</h5>
            <h6 class="card-text">{{ this.daysOfWeekString }}</h6>
            <p class="card-text">{{workoutPlan.message}}</p>
         </div>
         </div>
         <router-link v-bind:to="{ name: 'workout-plans', params: { UserID: UserID }}" exact>
                    <button class="text-light btn-lg btn-info ml-2">Back to Workouts</button>
          </router-link>
    </div>
    
</default-layout>
</template>

<script>
import DefaultLayout from '@/layouts/DefaultLayout';
import auth from '../auth';

export default {
    components: {
        DefaultLayout
    },
    data(){
        return{
            WorkoutPlanID: this.$route.params.WorkoutPlanID,
            workoutPlan: '',
            UserID: '',
            daysOfWeekArr: {
                sunday:    'Sun, ',
                monday:    'Mon, ',
                tuesday:   'Tues, ',
                wednesday: 'Wed, ',
                thursday:  'Thur, ',
                friday:    'Fri, ',
                saturday:  'Sat, ',
            },
            daysOfWeekString: '',
        }
    },
    methods: {

    },
  created() {
      fetch(`${process.env.VUE_APP_REMOTE_API}/workoutPlan/${this.WorkoutPlanID}`, {
      method: 'GET',
        headers: new Headers ({
          Authorization: 'Bearer ' + auth.getToken(),
        }),
        credentials: 'same-origin',
      }) 
        .then((response) => {
            return response.json();
        })
        .then((json) => {
            this.workoutPlan = json;
        })
        .catch((err) => console.error(err));
        this.daysOfWeekString = '';
        let lastTrueDay = '';
        let counter = 0;
        this.workoutPlans.forEach(workoutPlan => {
            this.daysOfWeekArr.forEach(dayOfWeek => {
                this.daysOfWeekString += (workoutPlan.dayOfWeek.indexOf(counter) == 'T' ? dayOfWeek : '');
                lastTrueDay = (workoutPlan.dayOfWeek.indexOf(counter) == 'T' ? dayOfWeek : lastTrueDay);
                counter++;
            });
            if (this.daysOfWeekString.length > 11) {
                this.daysOfWeekString = this.daysOfWeekString.substring(0, this.daysOfWeekString.length - lastTrueDay.length);
                this.daysOfWeekString += 'and ' + lastTrueDay.substring(0, lastTrueDay.length - 2);
            }
            else {
                this.daysOfWeekString = this.daysOfWeekString.substring(0, this.daysOfWeekString.length - 2);
            }
        });
    }
 
}
</script>

<style>


</style>