<template>
<body>
  <div class="container page-view pt-2">
    <div class="messageContainer pb-4 pl-4 pr-4 pt-2 shadow subheading border border-info">
      <div class="row messageHeader text-dark mb-3 p-5 shadow">
                <div class="col">
                <h2 id="test">Private Message</h2>
                </div>
       </div>
      <div class="subject text-center">
      <h3>Subject: {{ message.subject }}</h3>
      </div>
      <p><span class="orangeText">From:</span> {{ message.senderId  + ' ' + message.receipientId }}, {{message.role}}
      <span class="orangeText">Sent on:</span> {{ message.postDate  }}</p>
      <div class="message p-3 mb-3">
      <p> {{ message.message }} </p>
      </div>
        <button v-on:click="replyToSender">Reply</button>
        <button v-on:click="deleteMessage" class="ml-2">Delete Message</button>
        <button v-on:click="backToInbox" class="ml-2">Return to inbox</button>
    </div>
  </div>
</body> 
    
</template>

<script>
import auth from '../auth';

export default {
    name: 'MessageDetail',
    
  data() {
    return {
      message: 
        {
        messageId: 1,
        senderId: 1,
        receipientId: 5,
        postDate: '2/12/19',
        subject: 'test',
        message: 'this is the body of the message',
        unread: false,
        senderDelete: false,
        recipientDelete: false
      }
    };
  },
  created() {
    /*
   const messageIdPuller = this.$route.params.MessageID;
   
    fetch(`${process.env.VUE_APP_REMOTE_API}/inbox/${messageIdPuller}`, {
      method: 'GET',
        headers: new Headers ({
          Authorization: 'Bearer ' + auth.getToken(),
        }),
        credentials: 'same-origin',
      })
      .then((response) => {
          return response.json();
      })
      .then((message) => {
          this.message = message;
      })
      .catch((err) => console.error(err));
      */
  },
  methods: {
    deleteMessage() {
      fetch(`${process.env.VUE_APP_REMOTE_API}/message/${this.messageIdPuller}`, {
        method: 'DELETE',
        headers: {
          Authorization: 'Bearer ' + auth.getToken(),
        },
        credentials: 'same-origin',
      })
        .then((response) => {
          if (response.ok) {
            this.$router.push({ path: '/inbox' })
          }
         
        })
        .catch((err) => console.error(err));
    },
    backToInbox() {
      this.$emit('inbox');//need to add trainerId
    },
    replyToSender() {
      this.$emit('trainer')//need to add senderId 
    }
  }
}
</script>

<style>

/* * {
  box-sizing: border-box;
}

.body {
  display: grid;
  grid-template-columns: 1fr 2fr 2fr 2fr 1fr;
  grid-template-rows: auto;
  grid-template-areas:
    ". subject subject .  ."
    ". name   role   date    ."
    ". message message message .";
}
.subject {
  grid-area: subject;
}
.name {
  grid-area: name;
}
.role {
  grid-area: role;
}
.date {
  grid-area: date;
}
.message {
  grid-area: message;
} */
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

</style>
