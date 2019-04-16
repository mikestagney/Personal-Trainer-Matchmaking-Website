<template>
  <div class="container-fluid">
    <navigation v-on:login-updated='handleLogin'></navigation>
    <div class="page-view">
      <slot />
    </div>
  </div>
</template>

<script>
import Navigation from '@/components/Navigation';

export default {
  name: 'DefaultLayout',
  components: {
    Navigation,
  },
  methods: {
    handleLogin() {
      console.log("DefaultLayout handleLogin()");
      alert("DefaultLayout handleLogin()");
    },
  }
};
</script>

<style>
.container-fluid {
  margin: 0px;
  padding: 0px !important;
}
.page-view {
  padding: 0px 40px !important;
}
</style>
