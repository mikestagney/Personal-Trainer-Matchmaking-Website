<template>
<default-layout>
    <div class="trainer-list container">
        <table class="table table-striped mt-4">
            <thead class="thead thead-light">
            <tr>
            <th scope="col">Days Of Week (Sun,Mon,Tues,Wed,Thurs,Fri,Sat)</th>
            <th scope="col">Work Out: </th>
            <th scope="col">Plan: </th>
            </tr>
            </thead>
            <tbody>
                <tr >
                    <td>{{ workoutPlan.daysOfWeek }}</td>
                    <td>{{workoutPlan.title}}</td>
                    <td>{{workoutPlan.body}}</td>
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
            workoutPlan: ''
        }
    },
    methods: {

    },
  created() {
      fetch(`${process.env.VUE_APP_REMOTE_API}workout/${this.UserID}`, {
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
    }
 
}
</script>

<style>


</style>