<template>
<default-layout>
<div class="container">
   <div class="row trainerHeader text-dark mb-3 p-5 shadow">
      <div class="col">
         <h1>
            {{trainer.firstName}} {{trainer.lastName}}
         </h1>
         <h3>
            {{trainer.city}}, {{trainer.state}}
         </h3>
      </div>
      <div class="col">
         <h3 class="font-italic">
            "{{trainer.philosophy}}"
         </h3>
      </div>
   </div>
   <trainer-tools v-if="jwt_token.getUser().rol === 'Trainer'" :TrainerID="TrainerID"></trainer-tools>
   <div class="row pb-3">
      <div class="col" v-if="jwt_token.getUser().rol === 'Client'">
         <router-link v-bind:to="{ name: 'addTrainer', params: { TrainerID: TrainerID }}" exact>
             <button class="btn btn-lg btn-info pl-4 pr-4">Book</button>
          </router-link>
          <router-link to="/trainer" exact>
             <button class="btn btn-lg btn-info pl-4 pr-4 ml-2">Message</button>
          </router-link>
      </div>
   </div>
   <div class="row mb-3 subheading shadow border border-info">
      <div class="col">
         <h5 class="text-center pt-2"><img src="@/assets/FitnessVectors/waterbottle.png" class="pr-3"/><span class="orangeText">Rating:</span><img src="@/assets/star.png" v-bind:title="trainer.rating + ' Star Review'" class="ratingStar pl-2" v-for="n in trainer.rating" v-bind:key="n" /></h5>
      </div>
      <div class="col">
         <h5 class="text-center pt-2"><img src="@/assets/FitnessVectors/scale.png" class="pr-3"/><span class="orangeText">Price:</span> ${{trainer.hourlyRate}} per
            hour
         </h5>
      </div>
      </div>
      <div class="row mb-3">
         <div class="col subheading shadow border border-info">
            <h5 class="orangeText text-center pt-2"><img src="@/assets/FitnessVectors/shoe.png" class="pr-3"/>Background</h5>
            <hr class="my-3">
            <h6>{{trainer.biography}}</h6>
         </div>
         <div class="col subheading ml-3 shadow border border-info">
            <h5 class="orangeText text-center pt-2"><img src="@/assets/FitnessVectors/muscles.png" class="pr-3"/>Certifications</h5>
            <hr class="my-3">
            <div v-for="(certification, index) in trainer.certifications" :key="index">
               <h6>{{certification}}</h6>
            </div>
         </div>
      </div>
</div>
</default-layout>
</template>

<script>
import DefaultLayout from '@/layouts/DefaultLayout';
import TrainerTools from '@/components/TrainerTools';
import auth from '@/auth';

export default {
   name:"trainer-profile",
   components: {
      DefaultLayout,
      TrainerTools
   },
   data(){
      return{
         TrainerID: this.$route.params.TrainerID,
         title:"Trainer Profile",
         trainer: [],
         jwt_token: auth
      }
   },
   methods: {
   },
   created() {
      fetch(`${process.env.VUE_APP_REMOTE_API}/trainer/profile`, {
      method: 'GET',
         headers: new Headers ({
            Authorization: 'Bearer ' + auth.getToken(),
         }),
         credentials: 'same-origin',
      }) 
      .then(response => response.json())
      .then((json) => {
            this.trainer = json
            this.TrainerID = this.trainer.userId
        })
      .catch(err => console.error(err));
   }
}
</script>

<style>

.orangeText {
    color: 	#FF8C00;
}

.orangeBackground {
   background-color:	#ffaf4e;
}

.trainerHeader {
    background-image: url('/img/fitnessImage21.jpg');
    background-size: cover;
    background-position: center;
    border-radius: 4px;
    height: 250px;
    
}

.shadow {
  -moz-box-shadow:    3px 3px 5px 6px #ccc;
  -webkit-box-shadow: 3px 3px 5px 6px #ccc;
  box-shadow:         3px 3px 5px 6px #ccc;
}

.ratingStar {
   width: 8%;
}


</style>