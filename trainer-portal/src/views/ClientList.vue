<template>
<default-layout>
    <div class="trainer-list container">
            <div class="row clientListHeader text-dark mb-4 shadow">
                <div class="ol">
                <h2 id="test" class="pl-5 p-4">Current Clients</h2>
                </div>
            </div>
      <form method="GET" class="form-inline" v-on:submit.prevent="filterClientList">
                <input name="name" type="text" placeholder="Name" v-model="name" class="form-control mr-2">
                <input name="submit" value="Search" type="submit" class="btn btn-info">
      </form>
        <table class="table table-striped table-hover mt-3">
            <thead class="thead text-light orangeBackground">
            <tr>
            <th scope="col">Client Name</th>
            <th scope="col">City</th>
            <th scope="col">State</th>
            </tr>
            </thead>
            <tbody>
                <tr v-for="client in filteredClientList" :key="client.userID">
                    <td><router-link v-bind:to="{ name: 'trainerProfile', params: { CliendID: client.userID }}" class="orangeText">{{client.firstName}} {{client.lastName}}</router-link></td>
                    <td>{{client.city}}</td>
                    <td>{{client.state}}</td>
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
            clientList: [],
            filteredClientList: [],
        };
    },
    methods: {
        filterClientList() {
                this.filteredClientList = this.clientList.filter((client) => {
                    return (client.firstName + ' ' + client.lastName).includes(this.name);
                })
        }
    },
    created() {
      fetch(`${process.env.VUE_APP_REMOTE_API}/clientList`, {
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
            this.clientList = json;
            this.filteredClientList = json;
        })
        .catch((err) => console.error(err));
    }
};
</script>

<style>

.clientListHeader {
    background-image: url('/img/fitnessImage10.jpg');
    background-size: cover;
    background-position: center;
    border-radius: 4px;
    height: 250px;

}

</style>