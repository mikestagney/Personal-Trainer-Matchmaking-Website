<template>
<default-layout>
      <div class="container">
         <h1>Edit Profile</h1>
         <h2>   
            the trainer id is {{this.$route.params.TrainerID}}
            Trainer name: {{ trainer.firstName + ' ' + trainer.lastName }}
            Profile is {{ trainer.public ? 'Public':'Private' }}
         </h2>
              
            <div class="form-input">
               <span id="label">City: </span> <input type="text" v-model="trainer.city" 
                  placeholder="trainer.city">
            </div>
            
        <label for="state">State: </label>
        <select name="state" v-model="trainer.state">
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
            <div class="form-input">
               <span id="label">Philosphy (limit 50 characters)</span><br> <textarea rows="1" cols="70" v-model="trainer.philosophy"> 
                  placeholder="trainer.philosophy"</textarea>
            </div>  
            <div class="form-input">
               <span id="label">Hourly rate: </span> <input type="text" v-model="trainer.hourlyRate" 
                  placeholder="trainer.hourlyRate">
            </div>  
            <div class="form-input">
               <span id="label">Background information (limit 250 characters)</span> <textarea rows="4 " cols="100" v-model="trainer.biography"> 
                  placeholder="trainer.biography"</textarea>
            </div>  
            <div class="form-input">

               <span id="label">Certifications</span>
                <div v-for="certification in this.certificationsList" :key="certification">
                    <input v-bind:value="certification">
                </div>
                
                <button @click="addCertification">
                    Add a new certification
                </button>
            </div>  
            <div class="form-input">
               <input id="true" type="radio" value="true" v-model="trainer.public"><label for="true">Profile is public</label>
               <input id="false" type="radio" value="false" v-model="trainer.public"><label for="false">Profile is private</label>
             </div>  
            <div>
            <button class="btn btn-lg btn-primary btn-block" type="submit">
                Update profile
            </button>        
            </div> 
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
