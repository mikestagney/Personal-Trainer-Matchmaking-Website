<template>
  
    <table>
      <tr>
        <th>Date sent</th>
        <th>Name</th>
        <th>Subject</th>
        <th>Unread?</th>
      </tr>
      <tr v-for="(message, index) in messages" v-bind:key="index">
        <td>{{ message.date_sent }}</td>
        <td>{{ message.firstname + ' ' + message.lastname }}</td>
        <td><router-link v-bind:to="{ name: 'trainerPM', params: { MessageID: message.message_id }}">{{ message.subject }}</router-link></td>
        <td>{{ message.unread ? 'Yes':'No' }}</td>
      </tr>
    </table>


</template>

<script>
export default {
    name: 'messagelist',
  data() {
    return {
      messages: [
        {
        firstname: '',
        lastname: '',
        role: '',
        subject: '',
        date_sent: '',
        unread: true,
        body: '',
        message_id: 0
      }
      ]
    };
  },
 
       
  created() {
      fetch(`${process.env.VUE_APP_REMOTE_API}/message`) 
        .then((response) => {
            return response.json();
        })
        .then((messages) => {
            this.messages = messages;
        })
        .catch((err) => console.error(err));
    }
}
</script>

<style>
table {
  width: 100%;
}


</style>
