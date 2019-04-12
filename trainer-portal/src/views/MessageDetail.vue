<template>
<body>
  <div class="container page-view pt-2">
    <div class="messageContainer pb-4 pl-4 pr-4 pt-2">
      <div class="row messageHeader text-light mb-3 p-5 shadow">
                <div class="col">
                <h2 id="test">Message</h2>
                </div>
       </div>
      <div class="subject text-center">
      <h3>"{{ this.message.subject }}"</h3>
      </div>
      <p><span class="orangeText">From:</span> {{ message.firstname  + ' ' + message.lastname }}, {{message.role}}</p>
      <p><span class="orangeText">Sent:</span> {{ message.date_sent  }}</p>
      <div class="message p-3">
      <p> {{ message.body }} </p>
      </div>
    </div>
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
    background-image: url("../assets/FitnessImages/fitnessImage8.jpg");
    background-size: cover;
    background-position: center;
    border-radius: 15px;

}

.message {
  background-color: white;
  border-radius: 15px;
}

</style>
