<template>
<default-layout>
      <div class="container shadow bg-light border border-warning">
         <h1 class="h3 mb-3 font-weight-normal text-center mt-3">Edit Profile</h1>
<!--          <h2>   
            the trainer id is {{this.$route.params.TrainerID}}
            Trainer name: {{ trainer.firstName + ' ' + trainer.lastName }}
            Profile is {{ trainer.public ? 'Public':'Private' }}
         </h2> -->
        <form>
        <div class="form-group row">
            <label for="city" class="col-sm-2 col-form-label">City</label>
            <div class="col-sm-5">
            <input type="text" class="form-control" placeholder="trainer.city" v-model="trainer.city">
            </div>
        </div>
        <div class="form-group row">
            <label for="state" class="col-sm-2 col-form-label">State</label>
            <div class="col-sm-5">
            <select name="state" v-model="trainer.state" class="form-control" placeholder="trainer.state">
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
        <div class="form-group row">
            <label for="rate" class="col-sm-2 col-form-label">Hourly Rate</label>
            <div class="col-sm-3">
            <input type="text" class="form-control" placeholder="trainer.hourlyRate" v-model="trainer.hourlyRate">
            </div>
        </div>
            <div class="form-group row">
            <label for="philosophy" class="col-sm-2 col-form-label">Philosophy (limit 50 characters)</label>
            <div class="col-sm-10">
            <input type="text" class="form-control" v-model="trainer.philosophy" placeholder="trainer.philosophy">
            </div>
        </div>
            <div class="form-group row">
            <label for="biography" class="col-sm-2 col-form-label">Background information (limit 250 characters)</label>
            <div class="col-sm-10">
            <textarea type="text" class="form-control" v-model="trainer.biography" rows="4" placeholder="trainer.biography"></textarea>
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label">Certifications</label>
            <div class="col-sm-10">
            <div v-for="certification in this.certificationsList" :key="certification">
                <input class="form-control d-flex justify-content-center" v-bind:value="certification">
            </div>    
                <button type="button" @click="addCertification" class="btn btn-dark mt-2 mb-2">
                Add a new certification
                </button>
        </div>
        </div>
        <fieldset class="form-group">
            <div class="row">
            <legend class="col-form-label col-sm-2 pt-0">Profile Status</legend>
            <div class="col-sm-2">
                <div class="form-check">
                <input id="true" class="form-check-input" type="radio" v-model="trainer.public" value="true">
                <label class="form-check-label" for="true">Profile is public</label>
                </div>
                <div class="form-check">
                <input id="false" class="form-check-input" type="radio" v-model="trainer.public" value="false">
                <label class="form-check-label" for="false">Profile is private</label>
                </div>
            </div>
            </div>
        </fieldset>
        <div class="form-group row">
            <div class="col-sm-12">
            <button type="submit" class="btn btn-info">Update Profile</button>
            </div>
        </div>
        </form>
        </div>
</default-layout>
</template>

<script>
import DefaultLayout from '@/layouts/DefaultLayout';
import auth from '@/auth';

export default {
    name:"editTrainerProfile",
    components: {
        DefaultLayout,
    },
    data(){
        return{
            title:"editTrainerProfile",
            TrainerID: this.$route.params.TrainerID,
            trainer: '',
            certificationsList: [],
        }
    },
    created() {  
      fetch(`${process.env.VUE_APP_REMOTE_API}/trainer/profile/${this.TrainerID}`, {
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
            this.trainer = json;
            this.certificationsList = this.trainer.certifications;
        })
        .catch((err) => console.error(err));
    },
    methods: {
        updateProfile() {
            fetch(`${process.env.VUE_APP_REMOTE_API}/trainer/profile/${this.TrainerID}`,{
            method: 'PUT',
            headers: new Headers ({
            Authorization: 'Bearer ' + auth.getToken(),
            }),
            credentials: 'same-origin',
            }) 
            .then((response) => {
            return response.json();
            })
            .then((trainer) => {
                this.trainer = trainer;
            })
            .catch((err) => console.error(err));
        },
        addCertification() {

            try {
                this.trainer.certifications.push(this.certification.value);
            }
            catch (Exception) {
                this.trainer.certifications.push('');
            }
            this.certificationsList = this.trainer.certifications;
        }
    }
}
</script>

<style>

.listHeader {
    background-image:  url('/img/fitnessImage03.jpg');
    background-size: cover;
    background-position: bottom;
    border-radius: 4px;
    height: 250px;

}


</style>
