<template>
  <div id="app">
    <transition name="fade" mode="out-in">
    <router-view></router-view>
    <update-trainer-profile></update-trainer-profile>
    <login></login>
    </transition>
  </div>
</template>

<script>
import UpdateTrainerProfile from './views/UpdateTrainerProfile.vue'
import Login from './views/Login.vue'

export default {
  name: 'app', 
  components: {
    UpdateTrainerProfile,
    Login
    
  }
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
