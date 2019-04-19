<template>
<default-layout>
    <div class="container page-view">
    <div class="messageContainer pb-4 pl-4 pr-4 pt-2">
      <div class="row messageHeader text-light mb-3 p-5 shadow">
                <div class="col">
      <h2>Compose Private Message</h2>
                </div>
        </div>
    <div class="form">
      <div class="form-input">
        <span class="orangeText" id="sendTo">Send to: {{ sendToName }}</span> 
        <span class="orangeText" maxlength="250">        Subject:</span> <input type="text" v-model="message.subject" maxlength="20" placeholder="Subject">
         
      </div> 
       <div class="message">
        <!-- <span id="composeMessage">Message:</span> -->
        <textarea name="body" cols="90" rows="5" v-model="message.message" maxlength="250"></textarea>
      </div>    
      <button v-on:click="sendMessage">Send Message</button>  <!-- :disabled="!isValidForm" -->
    </div>
      </div>  
  </div>


</default-layout>
</template>

<script>
import auth from '../auth';
import DefaultLayout from '@/layouts/DefaultLayout';


export default {
     name: 'WriteMessage',

     components: {
        DefaultLayout,
    },
    
  data() {
    return {
      message: 
        { //these 6 fields will post to the backend and database from Postman,
        messageId: 20,
        senderId: auth.getUser().jti,
        recipientId: 0, //this.$route.params.replyToID,
        //postDate: '', 
        subject: '',
        message: ''
        //unread: true,
      },
      sendSuccess: false,
      sendToName: this.$route.params.SenderName,
      replyToId: this.$route.params.replyToID,
      disabled: ''

    }
  },
    


    methods: {
     
      sendMessage() {
     
        this.message.recipientId = this.replyToId;
        fetch(`${process.env.VUE_APP_REMOTE_API}/send`, {
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


    }
    },
    computed: {
   // isValidForm() { //could not get this to work
    //  return this.message.subject != '' && this.message.message != '';
    //}
    },
    created() {
   // this.sendToName = this.$route.params.SenderName;
   // this.replyToId = this.$route.params.replyToID;
     // auth.User().id 
    },

  } 
  
</script>

<style>
.messageContainer {
  border-radius: 20px;
  background-color: #bdd0d6;
}

.messageHeader {
    background-image: url('/img/fitnessImage08.jpg');
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
