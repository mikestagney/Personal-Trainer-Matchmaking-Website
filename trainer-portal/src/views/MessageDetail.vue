<template>
<div>
  <h3>Subject: {{ this.message.subject }} </h3>
  <p> Name: {{ message.firstname  + ' ' + message.lastname }} {{ message.role }} Time sent: {{ message.date_sent  }}</p>
  <p> {{ message.body }} </p>
</div>
    
</template>

<script>
export default {
    name: 'MessageDetail',
    
  data() {
    return {
      MessageID: this.$route.params.MessageID,
      apiURL: 'http://5cab867dc85e05001452e9f5.mockapi.io/message/',
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
      }
    };
  },
  created() {
      fetch((`${process.env.VUE_APP_REMOTE_API}/${this.$route.params.MessageID}`)) 
        .then((response) => {
            return response.json();
        })
        .then((message) => {
            this.message = message;
        })
        .catch((err) => console.error(err));
    }
}
</script>

<style>

</style>
