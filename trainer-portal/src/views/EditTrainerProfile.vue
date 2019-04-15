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
               <span id="label">City</span> <input type="text" v-model="trainer.city" 
                  placeholder="trainer.city">
            </div>           
            <div class="form-input">
               <span id="label">State</span> <input type="text" v-model="trainer.state" 
                  placeholder="trainer.state">
            </div>  
            <div class="form-input">
               <span id="label">Philosphy (limit 50 characters)</span><br> <textarea rows="1" cols="70" v-model="trainer.philosophy"> 
                  placeholder="trainer.philosophy"</textarea>
            </div>  
            <div class="form-input">
               <span id="label">Hourly rate</span> <input type="text" v-model="trainer.hourlyRate" 
                  placeholder="trainer.hourlyRate">
            </div>  
            <div class="form-input">
               <span id="label">Background information (limit 250 characters)</span> <textarea rows="4 " cols="100" v-model="trainer.biography"> 
                  placeholder="trainer.biography"</textarea>
            </div>  
            <div class="form-input">
               <span id="label">Certifications</span>
                <div v-for="(certification, index) in trainer.certifications" :key="index">
                    {{ certification}}
                    <input v-model="certification.value">
                </div>
                <div>   
                  <button @click="addCertification">

                Add a new certification
                </button>
                <pre>{{ trainer.certifications }}</pre>
                </div>

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
            trainer: '',      
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
        .then((trainer) => {
            this.trainer = trainer;
        })
        .catch((err) => console.error(err));
    },
    methods: {
        updateProfile() {
            fetch(`${process.env.VUE_APP_REMOTE_API}/trainer/updateprofile/${this.TrainerID}`,{
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
        addCertification: function () {
            this.trainer.certifications.push( { value: ''});
        }
    }
}
</script>

<style>

.orangeText {
    color: 	#FF8C00;
}

.imageHeader {
    background-image: url("../assets/FitnessImages/fitnessImage5.jpg");
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
