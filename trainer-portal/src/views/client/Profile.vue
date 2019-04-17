<template>
<default-layout>
      <div class="container">
         <div class="row clientHeader text-dark mb-3 p-5 shadow">
            <div class="col">
               <h1 id="test">
                  Hello, {{client.firstName}}!
               </h1>
               <h3>
                  {{client.city}}, {{client.state}}
               </h3>
            </div>
         </div>
         <client-tools></client-tools>
    </div>
</default-layout>
</template>

<script>
import DefaultLayout from '@/layouts/DefaultLayout';
import ClientTools from '@/components/ClientTools';
import auth from '@/auth';

 export default {
  name:"clientProfile",
  components: {
      DefaultLayout,
      ClientTools
  },
  data(){
   return{
    ClientID: this.$route.params.ClientID,
    title:"client-profile",
    client: []
   }
  },
  created() {
      fetch(`${process.env.VUE_APP_REMOTE_API}/client/profile/${this.ClientID}`, {
      method: 'GET',
        headers: new Headers ({
          Authorization: 'Bearer ' + auth.getToken(),
        }),
        credentials: 'same-origin',
      }) 
        .then((response) => response.json())
        .then((json) => this.client = json)
        .catch((err) => console.error(err));
    }
 
 }
</script>

<style>

.orangeText {
    color: 	#FF8C00;
}

.clientHeader {
    background-image: url('/img/fitnessImage23.jpg');
    background-position: center;
    background-size: cover;
    border-radius: 4px;
    height: 250px;
    
}

.shadow {
  -moz-box-shadow:    3px 3px 5px 6px #ccc;
  -webkit-box-shadow: 3px 3px 5px 6px #ccc;
  box-shadow:         3px 3px 5px 6px #ccc;
}

.blueBackground {
    background-color: #e9f5f8;

}


</style>
