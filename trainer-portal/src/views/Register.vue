<template>
<default-layout>
  <div id="register" class="text-center shadow light-bg border border-warning">
    <form class="form-register" @submit.prevent="register">
      <h1 class="h3 mb-3 font-weight-normal">Create Account</h1>
      <div class="alert alert-danger" role="alert" v-if="registrationErrors">
        There were problems registering this user.
      </div>
      <label for="firstName" class="sr-only">First Name</label>
      <input
        type="text"
        id="firstname"
        class="form-control"
        placeholder="First Name"
        v-model="user.firstName"
        required
        autofocus
      />
      <label for="lastName" class="sr-only">Last Name</label>
      <input
        type="text"
        id="lastname"
        class="form-control"
        placeholder="Last Name"
        v-model="user.lastName"
        required
        autofocus
      />
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
      <input
        type="password"
        id="confirmPassword"
        class="form-control"
        placeholder="Confirm Password"
        v-model="user.confirmPassword"
        required
      />
      <label for="role">Role</label>
        <select v-model="user.role" class="ml-2">
        <option value="Client">Client</option>
        <option value="Trainer">Trainer</option>
      </select>
      <div class="row">
        <div class="col">
      <router-link :to="{ name: 'login' }" class="orangeText">
        Have an account?
      </router-link>
        </div>
      </div>
      <button class="btn btn-lg btn-info btn-block mt-2" type="submit">
        Create Account
      </button>
    </form>
  </div>
</default-layout>
</template>

<script>
import DefaultLayout from '@/layouts/DefaultLayout';

export default {
  name: 'register',
  components: {
    DefaultLayout,
  },
  data() {
    return {
      user: {
        firstName: '',
        lastName: '',
        username: '',
        password: '',
        confirmPassword: '',
        role: '',
      },
      registrationErrors: false,
    };
  },
  methods: {
    register() {
      fetch(`${process.env.VUE_APP_REMOTE_API}/register`, {
        method: 'POST',
        headers: {
          Accept: 'application/json',
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(this.user),
      })
      .then((response) => {
        if (response.ok) {
          this.$router.push({ path: '/login', query: { registration: 'success' } });
        } else {
          this.registrationErrors = true;
        }
      })
      .catch((err) => console.error(err));
    },
  },
};
</script>

<style>
html,
body {
  height: 100%;
}
#app {
  height: 100%;
}
#register {
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

.form-register {
  width: 100%;
  max-width: 330px;
  padding: 15px;
  margin: auto;
}

.form-register .form-control {
  position: relative;
  box-sizing: border-box;
  height: auto;
  padding: 10px;
  font-size: 16px;
}
.form-register .form-control:focus {
  z-index: 2;
}
.form-register input[type='text'] {
  margin-bottom: 10px;
  border-bottom-right-radius: 0;
  border-bottom-left-radius: 0;
}
.form-register input[type='password'] {
  margin-bottom: 10px;
  border-top-left-radius: 0;
  border-top-right-radius: 0;
}
</style>