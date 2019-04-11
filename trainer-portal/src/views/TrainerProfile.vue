<template>
<default-layout>
 <div class="trainerProfile">
  <div class="container">
   <!-- <h2>the trainer id is :{{this.$route.params.TrainerID}}</h2> -->
   <div v-for="trainer in trainers" :key="trainer.user_id">
     <div v-if="TrainerID == trainer.user_id">
    <div class="row bg-info text-light mb-3" id="trainerHeading">
        <div class="col">
        <h3 class="text-center">{{trainer.first_name}} {{trainer.last_name}}</h3>
        <h4 class="text-center font-italic">{{trainer.philosophy}}</h4>
        </div>
    </div>
    <div class="row text-center mb-3 purpleText">
        <div class="col bg-light">
            <h6 class="font-weight-bold">{{trainer.city}}, {{trainer.state}}</h6>
        </div>
        <div class="col bg-light ml-1">
            <h6 class="font-weight-bold">{{trainer.rating}}</h6>
        </div>
        <div class="col bg-light ml-1">
            <h6 class="font-weight-bold">${{trainer.hourly_rate}} per hour</h6>
        </div>
    </div>
    <div class="row mx-auto text-center mb-3">
        <div class="col">
        <p>{{trainer.bio_info}}</p>
        <p>{{trainer.certifications}}</p>
        </div>
    </div>
  </div>
 </div>
  </div>
 </div>
</default-layout>
</template>

<script>
import DefaultLayout from '@/layouts/DefaultLayout';

 export default {
  name:"trainerProfile",
  components: {
      DefaultLayout
  },
  data(){
   return{
    TrainerID: this.$route.params.TrainerID,
    title:"trainerProfile",
    trainers: []
   }
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
 
 }
</script>

<style>
#trainerHeading {
    border-radius: 4px;
}

.purpleText {
    color: #A194DB;
}

</style>
