<template>
  <default-layout>
  <div id="login" class="text-center shadow bg-light border border-warning">
    <form class="form-signin" @submit.prevent="login">
      <h1 class="h3 mb-3 font-weight-normal">Please Sign In</h1>
      <div class="alert alert-danger" role="alert" v-if="invalidCredentials">
        Invalid username and password!
      </div>
      <div class="alert alert-success" role="alert" v-if="this.$route.query.registration">
        Thank you for registering, please sign in.
      </div>
      <label for="username" class="sr-only">Username</label>
      <input
        type="text"
        id="username"
        class="form-control"
        placeholder="Username"
        v-model="user.username"
        required
        autofocus
      />
      <label for="password" class="sr-only">Password</label>
      <input
        type="password"
        id="password"
        class="form-control"
        placeholder="Password"
        v-model="user.password"
        required
      />
      <router-link :to="{ name: 'register' }" class="orangeText">
        Need an account
      </router-link>
      <button class="btn btn-lg btn-info btn-block mt-2" type="submit">
        Sign in
      </button>
    </form>
  </div>
 
  </default-layout>
</template>

<script>
import auth from '@/auth';
import DefaultLayout from '@/layouts/DefaultLayout';

export default {
  name: 'login',
  components: {
    DefaultLayout 
  },
  data() {
    return {
      user: {
        username: '',
        password: '',
      },
      invalidCredentials: false,
    };
  },
  methods: {
    login() {
      fetch(`${process.env.VUE_APP_REMOTE_API}/login`, {
        method: 'POST',
        headers: {
          'Accept': 'application/json',
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(this.user),
      })
      .then((response) => {
        if (response.ok) {
          return response.json();
        } else {
            this.invalidCredentials = true;
        }
      })
      .then((json) => {
        if (json.token != undefined) {           
          if (json.token.includes('"')) {
            json.token = json.token.replace(/"/g, '');
          }
          auth.saveToken(json.token);
          if (auth.getUser().rol == 'Trainer') {
            this.$router.push('/trainer/profile/' + json.userID);
          } else if (auth.getUser().rol == 'Client') {
              this.$router.push('/client/profile/' + json.userID);
          }
        }
      })
      .catch((err) => console.error(err));
    },
  }
}
</script>

<style>
html,
body {
  height: 100%;
}
#app {
  height: 100%;
}
#login {
  height: 100%;
  display: -ms-flexbox;
  display: flex;
  -ms-flex-align: center;
  align-items: center;
  padding-top: 40px;
  padding-bottom: 40px;
  font-family: 'Roboto Condensed', sans-serif;
  border-radius: 10px;
  
}

form {
  text-align: center;
}

.form-signin {
  width: 100%;
  max-width: 330px;
  padding: 15px;
  margin: auto;
}

.form-signin .form-control {
  position: relative;
  box-sizing: border-box;
  height: auto;
  padding: 10px;
  font-size: 16px;
}
.form-signin .form-control:focus {
  z-index: 2;
}
.form-signin input[type='text'] {
  margin-bottom: 10px;
  border-bottom-right-radius: 0;
  border-bottom-left-radius: 0;
}
.form-signin input[type='password'] {
  margin-bottom: 10px;
  border-top-left-radius: 0;
  border-top-right-radius: 0;
}
</style>