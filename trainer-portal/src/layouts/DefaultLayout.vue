<template>
  <div class="container-fluid">
    <navigation></navigation>
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
};
</script>

<style>
.container-fluid {
  margin: 0px;
  padding: 0px !important;
}
.page-view {
  padding: 0px 20px !important;
}
</style>
