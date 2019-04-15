// App.vue

<template>
  <div id="app">
    <nav>
        <ul>
            <li>
                <router-link :to="{name: 'home'}">Home</router-link>
            </li>
            <li>
                <router-link :to="{name: 'register'}">Register</router-link>
            </li>
            <li>
                <router-link :to="{name: 'login'}">Login</router-link>
            </li>
            <li>
                <router-link :to="{name: 'student', params: {id: 2}}">Student</router-link>
            </li>
        </ul>
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
</style>
