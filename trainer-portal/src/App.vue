// App.vue

<template>
  <div id="app">
    <nav>
        <router-link to='/'>Home</router-link>
        <router-link to='/register'>Register</router-link>
        <router-link to='/login'>Login</router-link>
    </nav>
    <router-view />
  </div>
</template>

<script>
export default {
}
</script>

<style>
#app {
   font-family: 'Roboto Condensed', sans-serif;
  margin: 0px;
  padding: 0px;
}
.fade-enter-active,
.fade-leave-active {
  transition-duration: 0.3s;
  transition-property: opacity;
  transition-timing-function: ease;
}
.fade-enter,
.fade-leave-active {
  opacity: 0;
}
</style>
