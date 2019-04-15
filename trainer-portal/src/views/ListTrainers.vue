<template>
<default-layout>
    <div class="trainer-list container">
            <div class="row listHeader text-light mb-3 p-5 shadow">
                <div class="col">
                <h2 id="test">Available Trainers</h2>
                </div>
            </div>
      <form method="GET" class="form-inline" v-on:submit.prevent="filterTrainers">
          <div class="form-row">
              <div class="col">
                <input name="name" type="text" placeholder="Name" v-model="name" class="form-control">
            </div>
            <div class="col">
                <input name="city" type="text" placeholder="City"  v-model="city" class="form-control">
            </div>
            <div class="col">
            <select name="state" type="text" placeholder="State" v-model="state" class="form-control">
                <option value="">State</option>
                <option value="AK">Alaska</option>
                <option value="AL">Alabama</option>
                <option value="AR">Arkansas</option>
                <option value="AZ">Arizona</option>
                <option value="CA">California</option>
                <option value="CO">Colorado</option>
                <option value="CT">Connecticut</option>
                <option value="DC">District of Columbia</option>
                <option value="DE">Delaware</option>
                <option value="FL">Florida</option>
                <option value="GA">Georgia</option>
                <option value="HI">Hawaii</option>
                <option value="IA">Iowa</option>
                <option value="ID">Idaho</option>
                <option value="IL">Illinois</option>
                <option value="IN">Indiana</option>
                <option value="KS">Kansas</option>
                <option value="KY">Kentucky</option>
                <option value="LA">Louisiana</option>
                <option value="MA">Massachusetts</option>
                <option value="MD">Maryland</option>
                <option value="ME">Maine</option>
                <option value="MI">Michigan</option>
                <option value="MN">Minnesota</option>
                <option value="MO">Missouri</option>
                <option value="MS">Mississippi</option>
                <option value="MT">Montana</option>
                <option value="NC">North Carolina</option>
                <option value="ND">North Dakota</option>
                <option value="NE">Nebraska</option>
                <option value="NH">New Hampshire</option>
                <option value="NJ">New Jersey</option>
                <option value="NM">New Mexico</option>
                <option value="NV">Nevada</option>
                <option value="NY">New York</option>
                <option value="OH">Ohio</option>
                <option value="OK">Oklahoma</option>
                <option value="OR">Oregon</option>
                <option value="PA">Pennsylvania</option>
                <option value="PR">Puerto Rico</option>
                <option value="RI">Rhode Island</option>
                <option value="SC">South Carolina</option>
                <option value="SD">South Dakota</option>
                <option value="TN">Tennessee</option>
                <option value="TX">Texas</option>
                <option value="UT">Utah</option>
                <option value="VA">Virginia</option>
                <option value="VT">Vermont</option>
                <option value="WA">Washington</option>
                <option value="WI">Wisconsin</option>
                <option value="WV">West Virginia</option>
                <option value="WY">Wyoming</option>
            </select>
            </div>
            </div>
            <div class="col">
                <select name="hourlyRate" type="number" placeholder="Any Hourly Rate" v-model="hourlyRate" class="form-control">
                    <option value=999>Any Hourly Rate</option>
                    <option value=20>$20 per Hour or Less</option>
                    <option value=40>$40 per Hour or Less</option>
                    <option value=100>$100 per Hour or Less</option>
                    <option value=50>$50 per Hour or More</option>
                </select>
            </div>
            <div class="col">
                <select name="rating" type="number" placeholder="Any Rating" v-model="rating" class="form-control">
                    <option value=0>Any Rating</option>
                    <option value=2>2 Stars or Higher</option>
                    <option value=3>3 Stars or Higher</option>
                    <option value=4>4 or 5 Stars</option>
                    <option value=5>5 Stars!!</option>
                </select>
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
            <th scope="col">Price</th>
            <th scope="col">Rating</th>
            </tr>
            </thead>
            <tbody>
                <tr v-for="trainer in filteredTrainers" :key="trainer.trainerID">
                    <td><router-link v-bind:to="{ name: 'trainerProfile', params: { TrainerID: trainer.trainerID }}" class="orangeText">{{trainer.firstName}} {{trainer.lastName}}</router-link></td>
                    <td>{{trainer.city}}</td>
                    <td>{{trainer.state}}</td>
                    <td>{{trainer.hourlyRate}}</td>
                    <td>{{trainer.rating}}</td>
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
    name:"ListTrainers",
    components: {
        DefaultLayout,
    },
    data() {
        return {
            name: '',
            city: '',
            state: '',
            hourlyRate: 999,
            rating: 0,
            sortBy: '',
            trainers: [],
            filteredTrainers: [],
        };
    },
    methods: {
        filterTrainers() {

                this.filteredTrainers = this.trainers.filter((trainer) => {
                    return (trainer.firstName + ' ' + trainer.lastName).includes(this.name)
                    && trainer.city.includes(this.city)
                    && trainer.state.includes(this.state)
                    && trainer.rating >= this.rating
                    && ((this.hourlyRate == 50 && trainer.hourlyRate >= this.hourlyRate)
                    || (this.hourlyRate != 50 && trainer.hourlyRate <= this.hourlyRate));
                })
        },

    },
    created() {
      fetch(`${process.env.VUE_APP_REMOTE_API}/trainers`, {
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
            this.trainers = json;
            this.filteredTrainers = json;
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