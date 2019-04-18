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
            <th scope="col">Done? </th>
            <th scope="col">Focus: </th>
            <th scope="col">Plan: </th>
            <th scope="col">Days Of Week: </th>
            </tr>
            </thead>
            <tbody>
                <tr v-for="workout in workoutPlans" :key="workout.workoutId" v-bind:class="{'workout-completed': workout.completed}">
                    <input type="checkbox" v-on:click="changeStatus(workout.workoutId)"/>
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
            workoutPlans: [],
            daysOfWeekArr: {
                sunday:    'Sun, ',
                monday:    'Mon, ',
                tuesday:   'Tues, ',
                wednesday: 'Wed, ',
                thursday:  'Thur, ',
                friday:    'Fri, ',
                saturday:  'Sat, ',
            },
            daysOfWeekStrings: [],
            daysOfWeekString: '',
        }
    },
    methods: {
         changeStatus(id) {
            const arrIndex = this.workoutPlans.findIndex((workout) => workout.workoutId == id);
            this.workoutPlans[arrIndex].completed = !this.workoutPlans[arrIndex].completed;
        }
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
            this.daysOfWeekStrings.push(this.daysOfWeekString);
        });
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

tr.workout-completed {
    text-decoration: line-through;
    color: darkgray;
}


</style>