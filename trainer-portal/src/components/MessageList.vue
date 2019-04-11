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
        <td>{{ message.subject }}</td>
        <td>{{ message.unread ? 'Yes':'No' }}</td>
      </tr>
    </table>


</template>

<script>
export default {
    name: 'messagelist',
  data() {
    return {
      apiURL: '',
      messages: [
        {
        firstname: '',
        lastname: '',
        role: '',
        subject: '',
        date_sent: '',
        unread: true,
        body: '',
        message_id: 1
      }
      ]
    };
  },
 
       
  created() {
      fetch(this.apiURL) 
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
