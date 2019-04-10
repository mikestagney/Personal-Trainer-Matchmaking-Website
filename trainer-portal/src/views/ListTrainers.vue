<template>
<default-layout>
    <div class="trainer-list">
        <h1>Available Trainers</h1>
        <table class="table table-striped">
            <thead class="thead thead-light">
            <tr>
            <th scope="col">Trainer Name</th>
            <th scope="col">City</th>
            <th scope="col">State</th>
            <th scope="col">Price</th>
            <th scope="col">Rating</th>
            </tr>
            </thead>
            <tbody>
                <tr v-for="trainer in trainers" :key="trainer.id">
                    <td>{{trainer.firstname}} {{trainer.lastname}}</td>
                    <td>{{trainer.city}}</td>
                    <td>{{trainer.state}}</td>
                    <td>{{trainer.price_per_hour}}</td>
                    <td>{{trainer.rating}}</td>
                </tr>
            </tbody>
        </table>
    </div>
</default-layout>
</template>

<script>
import DefaultLayout from '@/layouts/DefaultLayout';

export default {
    name:"ListTrainers",
    components: {
        DefaultLayout,
    },
    data() {
        return {
            trainers: []
        };
    },
    methods: {

    },
    created() {
      fetch(`${process.env.VUE_APP_REMOTE_API}`) 
        .then((response) => {
            return response.json();
        })
        .then((trainers) => {
            this.trainers = trainers;
        })
        .catch((err) => console.error(err));
    }

};
</script>

<style>

</style>