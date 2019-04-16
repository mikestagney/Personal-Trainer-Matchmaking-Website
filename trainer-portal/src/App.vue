<template>
  <div id="app">
    <router-view 
      v-on:login-updated='handleLogin'
    />
  </div>
</template>

<script>
export default {
  components: {
  },
  methods: {
    handleLogin() {
      console.log("app handleLogin()");
      alert("app handleLogin()");
    },
  }

}
</script>

<style>
#app {
  font-family: 'Roboto Condensed', sans-serif;
  margin: 0px;
  padding: 0px;
}
</style>
