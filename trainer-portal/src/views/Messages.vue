<template>
<body>
  <div class="container page-view pt-2">
    <div class="messageContainer pb-4 pl-4 pr-4 pt-2">
      <div class="row messagesHeader text-light mb-3 p-5 shadow">
                <div class="col">
                <h2 id="test">Inbox</h2>
                </div>
       </div>
      <div v-for="message in messages" v-bind:key="message">
      <div class="subject text-center">
      <h3>Subject: {{ message.subject }}</h3>
      </div>
      <p><span class="orangeText">From:</span> {{ message.senderId  + ' ' + message.receipientId }}, {{message.role}}
      <span class="orangeText">Sent on:</span> {{ message.postDate  }}</p>
      <div class="message p-3">
      <p> {{ message.message }} </p>
      </div>
        <button v-on:click="replyToSender">Reply</button>
        <button v-on:click="deleteMessage">Delete Message</button>
    </div>
    </div>
  </div>
</body> 
    
</template>

<script>
import auth from '../auth';

export default {
    name: 'Messages',
    data() {
        return {
        messages: [],
        };
    },
    created() {
        fetch(`${process.env.VUE_APP_REMOTE_API}/inbox`, {
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
            this.messages = json;
        })
        .catch((err) => console.error(err));
    },
}
</script>

<style>


.messagesHeader {
    background-image: url('/img/fitnessImage8.jpg');
    background-size: cover;
    background-position: center;
    border-radius: 15px;

}


</style>