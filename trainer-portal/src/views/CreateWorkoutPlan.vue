<template>
<default-layout>
     <div class="jumbotron creteWorkoutPlanHeader shadow">
            <div class="crete-workout-plan container">
        <div class="site-heading text-light pinkTransparent p-2 shadow">
            <h1 class="display-5 p-2">Create a customized Workout Plan for your Client</h1>
      <form method="POST" class="form-block" v-on:submit.prevent="createWorkoutPlan">
            <h4>Workout Plan Name: </h4>
            <div class="col">
                <input name="title" type="text" placeholder="ex: Cardio Day, Chest Day, Leges and more Legs, ..." v-model="workoutPlan.title" class="form-control">
            </div>
            <h4>Days of Week to do Workout On: </h4>
            <input type="checkbox" id="sunday" v-model="daysOfWeekArr.sunday.bool">
            <label for="checkbox">Sunday</label>
            <input type="checkbox" id="monday" v-model="daysOfWeekArr.monday.bool">
            <label for="checkbox">Monday</label>
            <input type="checkbox" id="tuesday" v-model="daysOfWeekArr.tuesday.bool">
            <label for="checkbox">Tuesday</label>
            <input type="checkbox" id="wednesday" v-model="daysOfWeekArr.wednesday.bool">
            <label for="checkbox">Wednesday</label>
            <input type="checkbox" id="thursday" v-model="daysOfWeekArr.thursday.bool">
            <label for="checkbox">Thursday</label>
            <input type="checkbox" id="friday" v-model="daysOfWeekArr.friday.bool">
            <label for="checkbox">Friday</label>
            <input type="checkbox" id="saturday" v-model="daysOfWeekArr.saturday.bool">
            <label for="checkbox">Saturday</label>
            <h4>Description of workout: </h4>
            <div class="col">
                <input name="message" type="text" placeholder="ex: Run 5 miles, Do 3 sets of bench, All Leg Workouts, ..." v-model="workoutPlan.message" class="form-control">
            </div>
                <div class="col">
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
                sunday:    {bool: false, day: 'Sunday, '   },
                monday:    {bool: false, day: 'Monday, '   },
                tuesday:   {bool: false, day: 'Tuesday, '  },
                wednesday: {bool: false, day: 'Wednesday, '},
                thursday:  {bool: false, day: 'Thursday, ' },
                friday:    {bool: false, day: 'Friday, '   },
                saturday:  {bool: false, day: 'Saturday, ' },
            },
            daysOfWeekString: '',
        }
    },
    methods: {
        createWorkoutPlan() {
            this.daysOfWeekString       = '';
            this.workoutPlan.daysOfWeek = '';
            this.workoutPlan.trainerId = this.TrainerID;
            this.workoutPlan.clientId = this.ClientID;
            let lastTrueDay = '';
            this.daysOfWeekArr.forEach(dayOfWeek => {
                this.workoutPlan.daysOfWeek  += (dayOfWeek.bool ? 'T' : 'F');
                this.daysOfWeekString += (dayOfWeek ? dayOfWeek.day : '');
                lastTrueDay = (dayOfWeek ? dayOfWeek.day : '');
            });
            if (this.daysOfWeekString.length > 11) {
                this.daysOfWeekString = this.daysOfWeekString.substring(0, this.daysOfWeekString.length - lastTrueDay.length);
                this.daysOfWeekString += 'and ' + lastTrueDay.substring(0, lastTrueDay.length - 2);
            }
            else {
                this.daysOfWeekString = this.daysOfWeekString.substring(0, this.daysOfWeekString.length - 2);
            }
            fetch(`${process.env.VUE_APP_REMOTE_API}/createWorkoutPlan`, {
                method: 'POST',
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
    background-color: rgba(255,182,193, 0.6);
}

</style>