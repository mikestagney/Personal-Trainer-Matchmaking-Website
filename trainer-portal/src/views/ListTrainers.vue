<template>
<default-layout>
    <div class="trainer-list container">
            <div class="row listHeader text-light mb-3 p-5 shadow">
                <div class="col">
                <h2 id="test">Available Trainers</h2>
                </div>
            </div>
      <form method="GET" class="form-inline" v-on:submit.prevent="getTrainers">
          <div class="form-row">
              <div class="col">
                <input name="name" type="text" placeholder="Name" v-model="nameSearch" class="form-control">
            </div>
            <div class="col">
                <input name="city" type="text" placeholder="City"  v-model="citySearch" class="form-control">
            </div>
            <div class="col">
            <Select name="state" type="text" placeholder="State" v-model="stateSearch" class="form-control">
                <option disabled value="">State</option>
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
            <div class="form-row mt-2">
            <div class="col">
                <input name="minPrice" type="text" placeholder="Min Price per Hour" v-model="minPrice" class="form-control">
            </div>
            <div class="col">
                <input name="maxPrice" type="text" placeholder="Max Price per Hour" v-model="maxPrice" class="form-control">
            </div>
            <div class="col">
                <input name="rating" type="text" placeholder="Rating" v-model="rating" class="form-control">
            </div>
                <div class="col">
                <input name="submit" value="Search" type="submit" class="btn btn-info">
                </div>
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
                <tr v-for="trainer in trainers" :key="trainer.user_id">
                    <td><router-link v-bind:to="{ name: 'trainerProfile', params: { TrainerID: trainer.user_id }}" class="orangeText">{{trainer.first_name}} {{trainer.last_name}}</router-link></td>
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
            nameSearch: '',
            citySearch: '',
            stateSearch: '',
            minPrice: '',
            maxPrice: '',
            sortBy: '',
            trainers: []
        };
    },
    computed: {
        /* activeTrainers: function() {
            return this.trainers.filter(function(trainer) {
                return trainer.public
            })
        } */
    },
    methods: {
        getTrainers(){
            fetch(`${process.env.VUE_APP_REMOTE_API}/trainers/search/${this.searchText}`)
            .then(response => response.json())
            .then(json => this.trainers = json)
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

.listHeader {
    background-image: url("../assets/FitnessImages/fitnessImage7.jpg");
    background-size: cover;
    background-position: center;
    border-radius: 4px;

}

</style>