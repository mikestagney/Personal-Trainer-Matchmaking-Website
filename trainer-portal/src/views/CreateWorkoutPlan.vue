<template>
<default-layout>
     <div class="jumbotron creteWorkoutPlanHeader shadow">
            <div class="crete-workout-plan container">
        <div class="site-heading text-dark pinkTransparent p-2 shadow">
            <h1 class="display-5 p-2 text-center">Create a Customized Workout Plan for your Client</h1>
      <form method="POST" class="form-block" v-on:submit.prevent="createWorkoutPlan">
            <h3>Workout Focus: </h3>
            <div class="col">
                <input name="title" type="text" placeholder="ex: Cardio Day, Chest Day, Arms and Abs, ..." v-model="workoutPlan.title" class="form-control mb-3">
            </div>
            <h3>Days to Implement: </h3>
            <input class="mr-1" type="checkbox" id="sunday" v-model="daysOfWeekArr.sunday">
            <label class="mr-2" for="checkbox">Sunday</label>
            <input class="mr-1" type="checkbox" id="monday" v-model="daysOfWeekArr.monday">
            <label class="mr-2" for="checkbox">Monday</label>
            <input class="mr-1" type="checkbox" id="tuesday" v-model="daysOfWeekArr.tuesday">
            <label class="mr-2" for="checkbox">Tuesday</label>
            <input class="mr-1" type="checkbox" id="wednesday" v-model="daysOfWeekArr.wednesday">
            <label class="mr-2" for="checkbox">Wednesday</label>
            <input class="mr-1" type="checkbox" id="thursday" v-model="daysOfWeekArr.thursday">
            <label class="mr-2" for="checkbox">Thursday</label>
            <input class="mr-1" type="checkbox" id="friday" v-model="daysOfWeekArr.friday">
            <label class="mr-2" for="checkbox">Friday</label>
            <input class="mr-1" type="checkbox" id="saturday" v-model="daysOfWeekArr.saturday">
            <label class="mr-2" for="checkbox">Saturday</label>
            <h3 class="mt-3">Description: </h3>
            <div class="col">
                <input name="message" type="text" placeholder="ex: Run 5 miles, Do 3 sets of bench, All Leg Workouts, ..." v-model="workoutPlan.message" class="form-control">
            </div>
                <div class="col mt-3">
                <input name="submit" value="Submit Workout Plan" type="submit" class="btn btn-info">
                </div>
      </form>
    </div>
    </div>
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
            ids: {
                TrainerID: '',
                ClientID: '',
            },
            workoutPlan: {
                trainerId: '',
                clientId: '',
                daysOfWeek: '',
                title:      '',
                message:    '',
            },
            daysOfWeekArr: {
                sunday:    false,
                monday:    false,
                tuesday:   false,
                wednesday: false,
                thursday:  false,
                friday:    false,
                saturday:  false,
            },
        }
    },
    methods: {
        createWorkoutPlan() {
            this.daysOfWeekString       = '';
            this.workoutPlan.daysOfWeek = '';
            this.workoutPlan.trainerId = this.TrainerID;
            this.workoutPlan.clientId = this.ClientID;
            this.daysOfWeekArr.forEach(dayOfWeek => {
                this.workoutPlan.daysOfWeek  += (dayOfWeek ? 'T' : 'F');
            });
            fetch(`${process.env.VUE_APP_REMOTE_API}/createWorkoutPlan`, {
                method: 'POST',
                    headers: new Headers ({
                    Authorization: 'Bearer ' + auth.getToken(),
                    }),
                    credentials: 'same-origin',
                    body: JSON.stringify(this.workoutPlan),
                }) 
                    .then((response) => {
                        return response.json();
                    })
                    .catch((err) => console.error(err));
        },
        created() {
            fetch(`${process.env.VUE_APP_REMOTE_API}/createWorkoutPlan/${this.ClientID}`, {
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
                    this.ids = json;
                })
                .catch((err) => console.error(err));
        },
    },
}
</script>

<style>

.creteWorkoutPlanHeader {
    background-image: url('/img/fitnessImage25.jpg');
    background-size: cover;
     background-position: center;
    border-radius: 4px;
    height: 626px;
}

.pinkTransparent {
    background-color: rgba(255,182,193, 0.5);
}

</style>