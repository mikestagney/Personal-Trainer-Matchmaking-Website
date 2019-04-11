<template>
<default-layout>
    <div class="trainer-list">
      <form method="GET" class="form-inline" v-on:submit.prevent="getTrainers">
        <h1>Available Trainers</h1>
        <input name="search" type="text" placeholder="Search" v-model="searchText">
        <div class="form-group">
        <label for="sortBy">by:</label>
        <select name="sortBy" class="form-control" v-model="sortBy">
          <option value="lastName">Trainer last name</option>
          <option value="city">City</option>
          <option value="state">State</option>
          <option value="pricePerHour">Price</option>  
          <option value="rating">Rating</option>
        </select>
      </div>
         <input name="submit" value="Search" type="submit" class="btn btn-primary">
      </form>
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
                <tr v-for="trainer in trainers" :key="trainer.user_id">
                    <td><router-link v-bind:to="{ name: 'trainerProfile', params: { TrainerID: trainer.user_id }}">{{trainer.first_name}} {{trainer.last_name}}</router-link></td>
                    <td>{{trainer.city}}</td>
                    <td>{{trainer.state}}</td>
                    <td>{{trainer.hourly_rate}}</td>
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
            searchText: '',
            sortBy: '',
            trainers: []
        };
    },
    methods: {
        getTrainers(){
            fetch(`${process.env.VUE_APP_REMOTE_API}/trainers/search/${this.searchText}`)
            .then(response => response.json())
            .then(parsedData => this.trainers = parsedData)
            .catch(err => console.log(err)); 
        },


    },
    created() {
      fetch(`${process.env.VUE_APP_REMOTE_API}/TrainerProfile`) 
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