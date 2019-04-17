<template>
<default-layout>
    
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
            ClientID: this.$route.params.ClientID,
            workoutPlan: {
                TrainerID: auth.getUser().getID(),
                ClientID: this.ClientID,
                daysOfWeek: '',
                title: '',
                message: '',
            },
        }
    },
    methods: {
        createWorkoutPlan() {
            fetch(`${process.env.VUE_APP_REMOTE_API}/workoutPlan`, {
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
            fetch(`${process.env.VUE_APP_REMOTE_API}/workoutPlan/${this.ClientID}`, {
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
                })
                .catch((err) => console.error(err));
        },
    },
}
</script>

<style>


</style>