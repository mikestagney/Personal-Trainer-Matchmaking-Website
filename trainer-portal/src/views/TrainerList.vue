<template>
<default-layout>
    <div class="trainer-list container">
            <div class="row listHeader2 text-light mb-3 p-5 shadow">
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
                    <td><router-link v-bind:to="{ name: 'trainer-profile', params: { TrainerID: trainer.trainerID }}" class="orangeText">{{trainer.firstName}} {{trainer.lastName}}</router-link></td>
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
import auth from '@/auth';

export default {
  name: 'dir-trainer',
  components: {
      DefaultLayout,
  },
  data() {
      return {
          name: '',
          completeTrainerList: [],
          filteredTrainerList: [],
      };
  },
  methods: {
      filterClientList() {
          this.filteredTrainerList = this.completeTrainerList.filter((trainer) => {
              return (trainer.firstName + ' ' + trainer.lastName).includes(this.name);
          })
      }
  },
  created() {
    fetch(`${process.env.VUE_APP_REMOTE_API}/dir/trainers`, {
      method: 'GET',
      headers: new Headers ({
        Authorization: 'Bearer ' + auth.getToken(),
      }),
      credentials: 'same-origin',
    }) 
    .then((response) => {
      console.log(response.json());
      return response.json();
    })
    .then((json) => {
      console.table(json);
      this.completeTrainerList = this.filteredTrainerList = json;
    })
    .catch((err) => console.error(err));
  }
};
</script>

<style>
.listHeader2 {
    background-image: url('/img/fitnessImage7.jpg');
    background-size: cover;
    background-position: center;
    border-radius: 4px;
}
</style>