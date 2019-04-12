<template>
<body>
  <div class="subject">
  <h3>Subject: {{ this.message.subject }} </h3>
  </div>
  <div class="name">
  Name: {{ message.firstname  + ' ' + message.lastname }}
  </div>
  <div class="role">
  {{ message.role }} 
  </div>
  <div class="date">
   sent: {{ message.date_sent  }}
  </div>
  <div class="message">
  <p> {{ message.body }} </p>
  </div>
</body> 
    
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

* {
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
}

</style>
