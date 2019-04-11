<template>
<div>
  <h1>Subject: {{ message.subject }} </h1>
  <p> Name: {{ message.firstname  + ' ' + message.lastname }} {{ message.role }} Time sent: {{ message.date_sent  }}</p>
  <p> {{ message.body }} </p>
</div>
    
</template>

<script>
export default {
    name: 'MessageDetail',
    components: {
        DefaultLayout,
    },
  data() {
    return {
      message: 
        {
        firstname: 'Steve',
        lastname: 'Austin',
        role: 'trainer',
        subject: 'test',
        date_sent: 'today',
        unread: true,
        body: 'blah blah',
        message_id: 1
      }
    };
  },
}
</script>

<style>

</style>
