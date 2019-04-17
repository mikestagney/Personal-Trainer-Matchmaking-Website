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
                <div v-for="(certification, index) in trainer.certifications" :key="index">
                    <input v-model="certification.value">
                </div>
                
                <button @click="addCertification">
                    Add a new certification
                </button>
                <pre>{{ certifications | json }}</pre>
                

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
import auth from '../auth';

export default {
    name:"editTrainerProfile",
    components: {
        DefaultLayout
    },
    data(){
        return{
            title:"editTrainerProfile",
            TrainerID: this.$route.params.TrainerID,
            trainer: ''
        }
    },
    created() {  
      fetch(`${process.env.VUE_APP_REMOTE_API}/trainer/profile/edit/${this.TrainerID}`, {
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
        })
        .catch((err) => console.error(err));
    },
    methods: {
        updateProfile() {
            fetch(`${process.env.VUE_APP_REMOTE_API}/trainer/profile/edit/${this.TrainerID}`,{
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
            this.trainer.certifications.push({ value: '' });
        }
    }
}
</script>

<style>

.orangeText {
    color: 	#FF8C00;
}

.imageHeader {
    background-image: url('');
    background-size: cover;
    background-position: bottom;
    border-radius: 4px;
    
}

.shadow {
  -moz-box-shadow:    3px 3px 5px 6px #ccc;
  -webkit-box-shadow: 3px 3px 5px 6px #ccc;
  box-shadow:         3px 3px 5px 6px #ccc;
}

.blueBackground {
    background-color: #e9f5f8;

}

@font-face {
font-family:"bebas-neue-by-fontfabric";
src:url("https://use.typekit.net/af/630fe9/00000000000000003b9b1558/27/l?primer=7cdcb44be4a7db8877ffa5c0007b8dd865b3bbc383831fe2ea177f62257a9191&fvd=n4&v=3") format("woff2"),url("https://use.typekit.net/af/630fe9/00000000000000003b9b1558/27/d?primer=7cdcb44be4a7db8877ffa5c0007b8dd865b3bbc383831fe2ea177f62257a9191&fvd=n4&v=3") format("woff"),url("https://use.typekit.net/af/630fe9/00000000000000003b9b1558/27/a?primer=7cdcb44be4a7db8877ffa5c0007b8dd865b3bbc383831fe2ea177f62257a9191&fvd=n4&v=3") format("opentype");
font-style:normal;font-weight:400;
}


</style>
