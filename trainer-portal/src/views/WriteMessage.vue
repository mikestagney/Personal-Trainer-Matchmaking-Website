<template>
    <div class="container page-view">
    <div class="messageContainer pb-4 pl-4 pr-4 pt-2">
      <div class="row messageHeader text-light mb-3 p-5 shadow">
                <div class="col">
      <h2>Compose Private Message</h2>
                </div>
        </div>
    <div class="form">
      <div class="form-input">
        <span class="orangeText">First Name:</span> <input type="text" v-model="message.firstname" placeholder="First Name">
        <span class="orangeText">Last Name:</span> <input type="text" v-model="message.lastname" placeholder="Last Name">
        <span class="orangeText">Subject:</span> <input type="text" v-model="message.subject" placeholder="Subject">
         
      </div>
       <div class="message">
        <!-- <span id="composeMessage">Message:</span> -->
        <textarea name="body" cols="90" rows="5" v-model="message.body"></textarea>
      </div>    
      <button v-on:click="sendMessage">Send Message</button>  <!-- :disabled="!isValidForm" -->
    </div>
      </div>  
  </div>



</template>

<script>
export default {
     name: 'WriteMessage',
    
  data() {
    return {
      message: 
        {
        firstname: '',
        lastname: '',
        role: '',
        subject: '',
        date_sent: '',
        unread: true,
        body: '',
        message_id: 0
      },
      sendSuccess: false

    }
  },
    methods: {
      sendMessage() {
        fetch(`${process.env.VUE_APP_REMOTE_API}/message`,{
        method: 'POST',
        headers: {
          //need to add validation when posting to back end API
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(this.message)
      })
      .then((response) => {
        if(response.ok) {
         this.$router.push({ path: '/home' });
          } 
      })
      .catch((err) => console.error(err));


    },


    computed: {
    isValidForm() {
      return this.message.firstname != '' && this.message.lastname != '' && this.message.subject != '' && this.message.body != '';
    }
    }

  }
}  
</script>

<style>
.messageContainer {
  border-radius: 20px;
  background-color: #bdd0d6;
}

.messageHeader {
    background-image: url("../assets/FitnessImages/fitnessImage8.jpg");
    background-size: cover;
    background-position: center;
    border-radius: 15px;

}

.message {
  background-color: white;
  border-radius: 15px;
}
#composeMessage {

}


</style>
