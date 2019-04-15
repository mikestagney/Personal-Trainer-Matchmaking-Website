<template>
<default-layout>
    <div class="trainer-list container">
            <div class="row listHeader text-light mb-3 p-5 shadow">
                <div class="col">
                <h2 id="test">Trainer List</h2>
                </div>
            </div>
      <form method="GET" class="form-inline" v-on:submit.prevent="filterTrainerList">
              <div class="col">
                <input name="name" type="text" placeholder="Name" v-model="name" class="form-control">
            </div>
                <div class="col">
                <input name="submit" value="Search" type="submit" class="btn btn-info">
                </div>
      </form>
        <table class="table table-striped mt-4">
            <thead class="thead thead-light">
            <tr>
            <th scope="col">Trainer Name</th>
            <th scope="col">City</th>
            <th scope="col">State</th>
            </tr>
            </thead>
            <tbody>
                <tr v-for="trainer in filteredTrainerList" :key="trainer.trainerID">
                    <td><router-link v-bind:to="{ name: 'trainerProfile', params: { TrainerID: trainer.trainerID }}" class="orangeText">{{trainer.firstName}} {{trainer.lastName}}</router-link></td>
                    <td>{{trainer.city}}</td>
                    <td>{{trainer.state}}</td>
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
        DefaultLayout,
    },
    data() {
        return {
            name: '',
            trainerList: [],
            filteredTrainerList: [],
        };
    },
    methods: {
        
        filterClientList() {

                this.filteredTrainerList = this.trainerList.filter((trainer) => {
                    return (trainer.firstName + ' ' + trainer.lastName).includes(this.name);
                })
        }
    },
    created() {
      fetch(`${process.env.VUE_APP_REMOTE_API}/trainerList`, {
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
            this.trainerList = json;
            this.filteredTrainerList = json;
        })
        .catch((err) => console.error(err));
    }
};
</script>

<style>

.listHeader {
    background-image: url("../assets/FitnessImages/fitnessImage7.jpg");
    background-size: cover;
    background-position: center;
    border-radius: 4px;

}

</style>