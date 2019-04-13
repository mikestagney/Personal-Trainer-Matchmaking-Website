<template>
  
    <table>
      <tr>
        <th>Date sent</th>
        <th>senderId recipientId</th>
        <th>Subject</th>
        <th>Unread?</th>
      </tr>
      <tr v-for="(message, index) in messages" v-bind:key="index">
        <td>{{ message.postDate }}</td>
        <td>{{ message.senderId + ' ' + message.receipientId }}</td>
        <td><router-link v-bind:to="{ name: 'trainerPM', params: { MessageID: message.messageId }}">{{ message.subject }}</router-link></td>
        <td>{{ message.unread ? 'Yes':'No' }}</td>
      </tr>
    </table>


</template>

<script>
import auth from '../auth';

export default {
    name: 'messagelist',
  data() {
    return {
      messages: [
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
      }
      ]
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
