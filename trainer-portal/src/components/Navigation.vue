<template>
  <nav class="navbar navbar-expand navbar-dark bg-info">
    <router-link to="/" class="navbar-brand">
      Personal Trainer Portal
    </router-link>
    <button
      class="navbar-toggler"
      type="button"
      data-toggle="collapse"
      data-target="#trainer-portal-navbar"
      aria-controls="trainer-portal-navbar"
      aria-expanded="false"
      aria-label="Toggle navigation"
    >
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="trainer-navbar">
      <ul class="navbar-nav mr-auto">
        <router-link to="/search" tag="li" class="nav-item" active-class="active" exact>
          <a class="nav-link">Search Trainers</a>
        </router-link>
        <router-link to="/save"  tag="li" class="nav-item" active-class="active">
        </router-link>
      </ul>
    </div>
    <div class="navbar-collapse collapse">
      <ul class="navbar-nav mr-auto">
        <router-link to="/login" v-if="!isLoggedIn()" tag="li" class="nav-item" active-class="active" exact>
          <li><a class="nav-link">Login</a></li>
        </router-link>
        <router-link to="home" v-if="isLoggedIn()" tag="li" class="nav-item" active-class="active" @click.prevent="doLogout()" exact>
          <li><a class="nav-link">Logout</a></li>
        </router-link>
      </ul>
    </div>
  </nav>
</template>

<script>
import auth from '../auth';

export default {
  name: 'Navigation',
  methods: {
    doLogout() {
      console.log('doLogout()');
      auth.logout();
      this.$router.go('home');
    },
    isLoggedIn() {
      console.log('isLoggedIn()');
      return auth.getToken() != null;
    }
  }, 
};
</script>

<style>
nav {
  margin-bottom: 20px;
}
a.navbar-brand,
a.nav-link {
  font-family: 'Acme', sans-serif;
}
</style>