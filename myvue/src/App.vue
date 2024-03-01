<template>
  <login></login>
</template>

<script>
import login from './components/login.vue'

export default {
  name: 'App',
  components: {
    login
  }
}
</script>

<style>
body{
  width: 100%;
  height: 100%;
}
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin:auto;
  width:100%;
  
}
</style>
