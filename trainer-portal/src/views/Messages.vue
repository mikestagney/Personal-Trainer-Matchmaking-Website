<template>
<default-layout>
  <div class="container">
      <div class="row messagesHeader text-light mb-3 p-5 shadow">
                <div class="col">
                <h2 id="test">Inbox</h2>
                </div>
       </div>
       <!-- <message-list></message-list> -->
    <table>
      <!-- <tr v-for="message in messages" v-bind:key="message"> -->
        <th>Date</th>
        <th>senderId</th>
        <th>recipientId</th>
        <th>Subject</th>
        <th>Unread?</th>
      <!-- </tr> -->
      <tr v-for="(message, index) in messages" v-bind:key="index">
        <td>{{ message.postDate }}</td>
        <td>{{ message.senderId }}</td>
        <td>{{ message.receipientId }}</td>
        <td><router-link v-bind:to="{ name: 'message-detail', params: { MessageID: message.messageId }}">{{ message.subject }}</router-link></td>
        <td>{{ message.unread ? 'Yes':'No' }}</td>
      </tr>
    </table>
  </div>
</default-layout>
</template>

<script>
import DefaultLayout from '@/layouts/DefaultLayout';
import auth from '../auth';
//import MessageList from '@/components/MessageList';

export default {
    name: 'Messages',
    components: {
         DefaultLayout,
    },

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
    background-image: url('/img/fitnessImage05.jpg');
    background-size: cover;
    background-position: bottom;
    border-radius: 15px;

}

table {
  width: 100%;
}


</style>