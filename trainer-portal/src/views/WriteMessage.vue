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
        <span class="orangeText">First Name: sender id</span> <input type="text" v-model="message.senderId" placeholder="First Name">
        <span class="orangeText">Last Name: receipent id</span> <input type="text" v-model="message.receipientId" placeholder="Last Name">
        <span class="orangeText">Subject:</span> <input type="text" v-model="message.subject" placeholder="Subject">
         
      </div>
       <div class="message">
        <!-- <span id="composeMessage">Message:</span> -->
        <textarea name="body" cols="90" rows="5" v-model="message.message"></textarea>
      </div>    
      <button v-on:click="sendMessage">Send Message</button>  <!-- :disabled="!isValidForm" -->
    </div>
      </div>  
  </div>



</template>

<script>
import auth from '../auth';

export default {
     name: 'WriteMessage',
    
  data() {
    return {
      message: 
        {
        messageId: 0,
        senderId: 0,
        receipientId: 0,
        postDate: '',
        subject: '',
        message: '',
        unread: true,
        senderDelete: false,
        recipientDelete: false
      },
      sendSuccess: false

    }
  },
    methods: {
      sendMessage() {
        fetch(`${process.env.VUE_APP_REMOTE_API}/send`,{
        method: 'POST',
        headers: new Headers ({
          Authorization: 'Bearer ' + auth.getToken(),
           'Content-Type': 'application/json',
          }),
        
        body: JSON.stringify(this.message)
      })
      .then((response) => {
        if(response.ok) {
         this.$router.push({ path: '/inbox' });
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
