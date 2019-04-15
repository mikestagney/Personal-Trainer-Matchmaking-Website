<template>
<default-layout>
      <div class="container">
         <div class="row imageHeader text-light mb-3 p-5 shadow">
            <div class="col">
               <h3 id="test">
                  {{client.firstName}} {{client.lastName}}
               </h3>
               <h5>
                  {{client.city}}, {{client.state}}
               </h5>
            </div>
         </div>
      </div>
</default-layout>
</template>

<script>
import DefaultLayout from '@/layouts/DefaultLayout';
import auth from '../auth';

 export default {
  name:"clientProfile",
  components: {
      DefaultLayout
  },
  data(){
   return{
    ClientID: this.$route.params.ClientID,
    title:"clientProfile",
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
        .then((response) => {
            return response.json();
        })
        .then((json) => {
            this.client = json;
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
