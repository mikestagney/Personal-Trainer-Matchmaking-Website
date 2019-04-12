<template>
    <div class="compose-message">
    <div class="header">
      <h2>Compose Private Message</h2>
    </div>
    <div class="form">
      <div class="form-input">
        <span class="label">First Name:</span> <input type="text" v-model="message.firstname" placeholder="First Name">
      </div>
      <div class="form-input">
        <span class="label">Last Name:</span> <input type="text" v-model="message.lastname" placeholder="Last Name">
      </div>
      <div class="form-input">
        <span class="label">Subject:</span> <input type="text" v-model="message.subject" placeholder="Subject">
      </div>
      <div class="form-input">
        <span class="label">Message:</span>
        <textarea name="body" id="review" cols="60" rows="10" v-model="message.body"></textarea>
      </div>
      <button v-on:click="sendMessage">Send Message</button>  <!-- :disabled="!isValidForm" -->
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
         this.$router.push({ path: '/trainer/privatemessage' });
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

</style>
