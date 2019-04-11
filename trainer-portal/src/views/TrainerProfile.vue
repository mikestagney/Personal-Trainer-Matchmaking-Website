<template>
<default-layout>
 <div class="trainerProfile">
  <div class="container">
   <h2>the trainer id is :{{this.$route.params.TrainerID}}</h2>
   <div v-for="trainer in trainers" :key="trainer.user_id">
     <div v-if="TrainerID == trainer.user_id">
    <div class="row">
        <div class="col-md-6 bg-info">
            <p>{{trainer.first_name}} {{trainer.last_name}}</p>
            <p>{{trainer.city}}, {{trainer.state}}</p>
        </div>
        <div class="col">
            <p>{{trainer.philosophy}}</p>
            <p>{{trainer.hourly_price}}</p>
            <p>{{trainer.rating}}</p>
        </div>
    </div>
    <div class="row mx-auto">
        <p>{{trainer.bio_info}}</p>
    </div>
    <div class="row mx-auto">
        <p>{{trainer.certifications}}</p>
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
