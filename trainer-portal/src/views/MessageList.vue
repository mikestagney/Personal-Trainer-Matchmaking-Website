<template>
<default-layout>
  <h3> Private Messages </h3>
    <table class="table table-striped table-hover mt-3">
      <thead class="thead text-light orangeBackground">
      <tr>
        <th>Date</th>
        <th>From</th>
        <th>Subject</th>
        <th>Compose</th>
        <!-- <th>Unread?</th> -->
      </tr>
      </thead>
      <tr v-for="(mess, index) in messages" v-bind:key="index">
        <td> {{ mess.postDate }}</td>
        <td>{{ mess.senderName }}</td>
        <td><router-link v-bind:to="{ name: 'message-detail', params: { MessageID: mess.messageId }}">{{ mess.subject }}</router-link></td>
        <!-- <td>{{ mess.unread ? 'Yes':'No' }}</td> -->
        <td><router-link v-bind:to="{ name: 'writemessage', params: { replyToID: mess.senderId, SenderName: mess.senderName }}"><button>send message to</button></router-link></td>
      </tr>
    </table>

</default-layout>
</template>

<script>
import auth from '../auth';
import DefaultLayout from '@/layouts/DefaultLayout';

export default {
    name: 'messagelist',
    components: {
        DefaultLayout,
    },
  data() {
    return {
      messages: [],
      TrainerID: this.$route.params.TrainerID 
    };
  },
 
       //fetch(`${process.env.VUE_APP_REMOTE_API}/inbox/${this.TrainerID}`,
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
        .then((messages) => {
            this.messages = messages;
        })
        .catch((err) => console.error(err));
    },
    
}
</script>

<style>
table {
  width: 100%;
}


</style>
