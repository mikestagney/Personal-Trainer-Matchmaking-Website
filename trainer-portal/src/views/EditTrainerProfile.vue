<template>
<default-layout>
      <div class="container">
         <h2>
            the trainer id is {{this.$route.params.TrainerID}}
            Trainer name: {{ trainer.firstName + ' ' + trainer.lastName }}
            Profile is {{ trainer.isPublic ? 'Public':'Private' }
         </h2>
              
            <div class="form-input">
               <span id="label">City></span> <input type="text" v-model="trainer.city" 
                  placeholder="{{ trainer.city }}">
            </div>           
            <div class="form-input">
               <span id="label">First Name></span> <input type="text" v-model="trainer.state" 
                  placeholder="{{ trainer.state }}">
            </div>  
            <div class="form-input">
               <span id="label">Philosphy (limit 50 characters)></span> <input type="text" v-model="trainer.philosophy" 
                  placeholder="{{ trainer.philosophy }}">
            </div>  
            <div class="form-input">
               <span id="label">Hourly rate ></span> <input type="text" v-model="trainer.hourlyRate" 
                  placeholder="{{ trainer.hourlyRate }}">
            </div>  
            <div class="form-input">
               <span id="label">Background information (limit 250 characters)></span> <input type="text" v-model="trainer.bioInfo" 
                  placeholder="{{ trainer.bioInfo }}">
            </div>  
            <div class="form-input">
               <span id="label">Certifications></span> <input type="text" v-model="trainer.certifications" 
                  placeholder="{{ trainer.certifications }}">
            </div>  
            <div class="form-input">
               <input id="true" type="radio" value="true" v-model="trainer.isPublic"><label for="true">Profile is public</label>
               <input id="false" type="radio" value="false" v-model="trainer.isPublic"><label for="false">Profile is private</label>
             </div>  
            <div>
            <button class="btn btn-lg btn-primary btn-block" type="submit">
                Update profile
            </button>        
            </div> 
         </div>
      </div>
</default-layout>
</template>

<script>
import DefaultLayout from '@/layouts/DefaultLayout';
import auth from '../auth';

export default {
    name:"editTrainerProfile",
    props: {
        chosenTrainer: Object
    },
    components: {
        DefaultLayout
    },
    data(){
        return{
            trainer: this.chosenTrainer      
        }
    },
  updateProfile() {
      fetch(`${process.env.VUE_APP_REMOTE_API}/trainer/updateprofile/${this.TrainerID}`, {
      method: 'PUT',
        headers: new Headers ({
          Authorization: 'Bearer ' + auth.getToken(),
        }),
        credentials: 'same-origin',
      }) 
        .then((response) => {
            return response.json();
        })
        .then((trainers) => {
            this.trainers = trainers;
        })
        .catch((err) => console.error(err));
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
