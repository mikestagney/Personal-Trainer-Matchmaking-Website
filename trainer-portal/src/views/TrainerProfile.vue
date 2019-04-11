<template>
 <div class="trainerProfile">
  <div class="container">
   <h2>the trainer id is :{{this.$route.params.TrainerID}}</h2>
  </div>
 </div>
</template>
<script>
 export default{
  name:'trainerProfile',
  data(){
   return{
    trainId:this.$route.params.TrainerID,
    title:"trainerProfile"
   }
  }
 }
</script>
