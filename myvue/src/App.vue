<template>
  <router-view></router-view>
</template>

<script>
// import router from './router';


export default {
  methods: {
    Togradelookup() {
      this.$router.push('/gradelookup');
    },
    logout() {
      this.$router.push('/');
    },
    CreateQuestion() {
      this.$router.push('/CreateQuestion');
    }
  }
}
</script>

<style>

html,
body,
#app {
  height: 100vh;
  width: 100vh;
  margin: 0;
  padding: 0;
  font-family: Arial, sans-serif;
}

#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin: auto;
}  
</style>