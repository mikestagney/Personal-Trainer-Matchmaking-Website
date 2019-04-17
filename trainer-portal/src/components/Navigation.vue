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
        <router-link :to="{name: 'dir-trainer'}" tag="li" class="nav-item" active-class="active" exact>
          <a class="nav-link">Trainer Directory</a>
        </router-link>
        <router-link to="/" tag="li" class="nav-item" active-class="active" v-on:click.native="userLoginLogout()">
           <a class="nav-link">{{ (isLoggedIn)? 'Logout': 'Login' }}</a>
        </router-link>
      </ul>
    </div>
  </nav>
</template>

<script>
import auth from '@/auth';


export default {
  name: 'Navigation',
  isLoggedIn: '',
  data () {
    return {
      isLoggedIn: auth.getUser() != null
    }
  },
  /*
  computed: {
    isLoggedIn: function () {
      console.log('isLoggedIn(auth.getUser() != null): ' + (auth.getUser() != null).toString());
      return auth.getUser() != null;
    }
  },
  created () {
    this.$bus.$on('logged', () => {
      this.isLogged = this.isLoggedIn()
    })
  },
  */  
  methods: {
    userLoginLogout() {
      console.log('userLoginLogout(this.isLoggedIn): ' + this.isLoggedIn);
      if( this.isLoggedIn ) {
        auth.logout();
        console.log('after logout: ' + this.isLoggedIn);
        console.log('isLoggedIn(auth.getUser() != null): ' + (auth.getUser() != null).toString());
        this.isLoggedIn = false;
        this.$router.push({ name: 'home'});
      } else {
        console.log('push login');
        this.$router.push({ name: 'login'});
      }
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