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
    const messageID = this.$route.params.MessageID;
    fetch(`${process.env.VUE_APP_REMOTE_API}/message/${messageID}`)
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
