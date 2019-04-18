<template>
<default-layout>
    <div class="container">
            <div class="row listHeader2 text-light mb-3 p-5 shadow">
                <div class="col">
                <h2 id="test">Workout Plan</h2>
                </div>
            </div>
      <form method="POST" class="form-inline" v-on:submit.prevent="createWorkoutPlan">
            <div class="col">
                <input name="title" type="text" placeholder="Title" v-model="workoutPlan.title" class="form-control">
            </div>
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
            <div class="col">
                <input name="message" type="text" placeholder="What is the workout?" v-model="workoutPlan.message" class="form-control">
            </div>
                <div class="col">
                <input name="submit" value="Update" type="submit" class="btn btn-info">
                </div>
      </form>
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


</style>