<template>
 <div class="trainerDetailPage">
  <div class="container">
   <h2>the trainer id is :{{this.$route.params.Tid}}</h2>
  </div>
 </div>
</template>
<script>
 export default{
  name:'trainerDetailPage',
  data(){
   return{
    trainId:this.$route.params.Tid,
    title:"trainerDetailPage"
   }
  }
 }
</script>
