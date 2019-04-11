<template>
<default-layout>
 <div class="details">
  <div class="container">
    <div v-for="trainer in trainers" :key="trainer.user_id">
     <div v-if="trainId == trainer.user_id">
    <div class="row">
        <div class="col">
            <p>{{trainer.first_name}} {{trainer.last_name}}</p>
            <p>{{trainer.city}}, {{trainer.state}}</p>
        </div>
        <div class="col">
            <p>{{trainer.philosophy}}</p>
            <p>{{trainer.hourly_price}}</p>
            <p>{{trainer.rating}}</p>
        </div>
    </div>
    <div class="row">
        <p>{{trainer.bio}}</p>
    </div>
    <div class="row">
        <p>{{trainer.certifications}}</p>
   </div>
  </div>
 </div>
 </default-layout>
</template>

<script>
 export default{
  name:'trainerDetailPage',
  data(){
   return {
    apiURL: 'http://5cab867dc85e05001452e9f5.mockapi.io/TrainerProfile',
    trainId: this.$route.params.Tid,
    trainers: []
    };
   },
   
    methods: {

   },
   
   created() {
      fetch(this.apiURL) 
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