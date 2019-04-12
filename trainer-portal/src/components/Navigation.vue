<template>
  <nav class="navbar navbar-expand navbar-dark bg-info">
    <router-link to="/" class="navbar-brand">
      Personal Trainer Portal
    </router-link>
    <button
      class="navbar-toggler"
      type="button"
      data-toggle="collapse"
      data-target="#te-snippets-navbar"
      aria-controls="te-snippets-navbar"
      aria-expanded="false"
      aria-label="Toggle navigation"
    >
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="trainer-navbar">
      <ul class="navbar-nav mr-auto">
        <router-link tag="li" class="nav-item" to="/search" active-class="active" exact>
          <a class="nav-link">Search Trainers</a>
        </router-link>
        <router-link tag="li" class="nav-item" to="/save" active-class="active">
        </router-link>
      </ul>
    </div>
    <div class="navbar-collapse collapse">
      <ul class="navbar-nav ml-auto">
        <router-link tag="li" class="nav-item" @click="logout" active-class="active" exact>
          <a class="nav-link" href="#"><!-- {{auth.getToken === null ? 'login' : 'logout'}} --></a>
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
    logout() {
      auth.logout();
      this.$router.go('/');
    },
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