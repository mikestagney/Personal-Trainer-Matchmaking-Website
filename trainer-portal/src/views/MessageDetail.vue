<template>
<default-layout>
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
      <p><span class="orangeText">From:</span> {{ message.senderName }}
      <span class="orangeText">Sent on:</span> {{ message.postDate }} </p>
      <div class="message p-3 mb-3">
      <p> {{ message.message }} </p>
      </div>  
        <button v-on:click="replyToSender">Reply</button>
        <button v-on:click="deleteMessage" class="ml-2">Delete Message</button>
        <button v-on:click="backToInbox" class="ml-2">Return to inbox</button>
    </div>
  </div>
</body> 
</default-layout>
</template>

<script>
import auth from '../auth';
import DefaultLayout from '@/layouts/DefaultLayout';

export default {
    name: 'MessageDetail',

    components: {
      DefaultLayout,
    },
    
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
        unread: false,
        senderDelete: false,
        recipientDelete: false,
        senderName: '',
        recipientName: ''
      },
      messageIdPuller: 0
    };
  },
  created() {
    
   const messageIdPuller = this.$route.params.MessageID;
   //fetch(`${process.env.VUE_APP_REMOTE_API}/inbox/message/${messageIdPuller}
    fetch(`${process.env.VUE_APP_REMOTE_API}/inbox/message/${messageIdPuller}`, {
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
      
  },//fetch(`${process.env.VUE_APP_REMOTE_API}/inbox/message/${this.messageIdPuller}  
  methods: {
    deleteMessage() {
      const deleteMessID = this.$route.params.MessageID;
      fetch(`${process.env.VUE_APP_REMOTE_API}/inbox/message/${deleteMessID}`, {
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
         this.$router.push({ path: '/inbox' })
    },
    replyToSender() {
     this.$router.push({ name: 'writemessage', params: { replyToID: this.message.senderId, SenderName: this.message.senderName} })

      
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
