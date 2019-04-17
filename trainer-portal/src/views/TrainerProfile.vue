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
         <div class="row mb-3 subheading shadow border border-info">
            <div class="col">
                <h5 class="text-center pt-2"><img src="../assets/FitnessVectors/waterbottle.png" class="pr-3"/><span class="orangeText">Rating:</span><img src="../assets/star.png" v-bind:title="trainer.rating + ' Star Review'" class="ratingStar pl-2" v-for="n in trainer.rating" v-bind:key="n" /></h5>
            </div>
            <div class="col">
                  <h5 class="text-center pt-2"><img src="../assets/FitnessVectors/scale.png" class="pr-3"/><span class="orangeText">Price:</span> ${{trainer.hourlyRate}} per
                  hour</h5>
            </div>
            </div>
            <div class="row mb-3">
               <div class="col subheading shadow border border-info">
                 <h5 class="orangeText text-center pt-2"><img src="../assets/FitnessVectors/shoe.png" class="pr-3"/>Background</h5>
                <hr class="my-3">
                     <h6>{{trainer.biography}}</h6>
               </div>
               <div class="col subheading ml-3 shadow border border-info">
                 <h5 class="orangeText text-center pt-2"><img src="../assets/FitnessVectors/muscles.png" class="pr-3"/>Certifications</h5>
                <hr class="my-3">
                     <h6>{{trainer.certifications}}</h6>
               </div>
            </div>
      </div>
</default-layout>
</template>

<script>
import DefaultLayout from '@/layouts/DefaultLayout';
import auth from '../auth';

export default {
    name:"trainerProfile",
    components: {
        DefaultLayout
    },
    data(){
        return{
            TrainerID: this.$route.params.TrainerID,
            title:"trainerProfile",
            trainer: []
        }
    },
    methods: {

    },
  created() {
      fetch(`${process.env.VUE_APP_REMOTE_API}trainer/profile/${this.TrainerID}`, {
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
    background-image: url("../assets/FitnessImages/fitnessImage21.jpg");
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
