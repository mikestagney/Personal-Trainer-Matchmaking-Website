<template>
<default-layout>
    <div class="container">
           <div class="row workoutHeader text-light mb-4 shadow">
<!--             <div class="col">
                <h2 class="p-4">
                    Current Workouts
                </h2>
            </div> -->
    </div>
        <table class="table table-striped table-hover mt-3">
            <thead class="thead text-light orangeBackground">
            <tr>
            <th scope="col">Days Of Week (Sun,Mon,Tues,Wed,Thurs,Fri,Sat)</th>
            <th scope="col">Focus: </th>
            <th scope="col">Plan: </th>
            </tr>
            </thead>
            <tbody>
                <tr v-for="workout in workoutPlans" :key="workout.workoutId">
                    <td>{{ workout.daysOfWeek }}</td>
                    <td><router-link v-bind:to="{ name: 'workout-plan', params: { WorkoutPlanID: workout.workoutId }}" class="orangeText">{{workout.title}}</router-link></td>
                    <td>{{workout.message}}</td>
                </tr>
            </tbody>
        </table>
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
            UserID: this.$route.params.UserID,
            workoutPlans: []
        }
    },
    methods: {

    },
  created() {
      fetch(`${process.env.VUE_APP_REMOTE_API}/workoutPlans/${this.UserID}`, {
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
            this.workoutPlans = json;
        })
        .catch((err) => console.error(err));
    }
 
}
</script>

<style>


.workoutHeader {
    background-image:  url('/img/FitnessCollage.png');
    background-size: cover;
    background-position: bottom;
    border-radius: 4px;
    height: 250px;

}


</style>