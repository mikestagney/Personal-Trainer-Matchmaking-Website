<template>
<default-layout>
 <div class="trainerProfile">
  <div class="container">
   <!-- <h2>the trainer id is :{{this.$route.params.TrainerID}}</h2> -->
   <div v-for="trainer in trainers" :key="trainer.user_id">
     <div v-if="ClientID == trainer.user_id">
    <div class="row imageHeader text-light mb-3 p-5 shadow">
        <div class="col">
        <h3 id="test">{{client.first_name}} {{client.last_name}}</h3>
        <h5>{{client.city}}, {{client.state}}</h5>
        </div>
        <div class="col">
        <h4 class="font-italic">{{trainer.philosophy}}</h4>
        </div>
    </div>
    <div class="row mb-2">
        <div class="col blueBackground ml-1">
            <h6 class="font-weight-bold"><img src="../assets/FitnessVectors/waterbottle.png" class="p-3"><span class="orangeText">  Rating:</span> {{trainer.rating}}</h6>
            
        </div>
        <div class="col blueBackground ml-1">
            <h6 class="font-weight-bold"><img src="../assets/FitnessVectors/scale.png" class="p-3"><span class="orangeText">  Price:</span> ${{trainer.hourly_rate}} per hour</h6>
        </div>
        </div>
        <div class="row mb-3">
            <div class="col blueBackground">
            <h6 class="font-weight-bold"><img src="../assets/FitnessVectors/shoe.png" class="p-3"><span class="orangeText">  Background:</span> {{trainer.bio_info}}</h6>
            </div>
            <div class="col blueBackground ml-1">
            <h6 class="font-weight-bold"><img src="../assets/FitnessVectors/muscles.png" class="p-3"><span class="orangeText"> Certifications:</span> {{trainer.certifications}}</h6>
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
  name:"clientProfile",
  components: {
      DefaultLayout
  },
  data(){
   return{
    ClientID: this.$route.params.ClientID,
    title:"client Profile",
    client: []
   }
  },
  created() {
      fetch(`${process.env.VUE_APP_REMOTE_API}/TrainerProfile`) 
        .then((response) => {
            return response.json();
        })
        .then((clients) => {
            this.clients = clients;
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
