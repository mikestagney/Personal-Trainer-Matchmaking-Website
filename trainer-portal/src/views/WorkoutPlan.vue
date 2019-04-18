<template>
<default-layout>
    <div class="trainer-list container">
        <table class="table table-striped mt-4">
            <thead class="thead thead-light">
            <tr>
            <th scope="col">Work Out: </th>
            <th scope="col">Focus: </th>
            <th scope="col">Days Of Week: </th>
            </tr>
            </thead>
            <tbody>
                <tr >
                    <td>{{workoutPlan.title}}</td>
                    <td>{{workoutPlan.message}}</td>
                    <td>{{ this.daysOfWeekString }}</td>
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
            WorkoutPlanID: this.$route.params.WorkoutPlanID,
            workoutPlan: '',
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