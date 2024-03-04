<template>
  <login></login>
</template>

<script>

// import router from './router';
import login from './components/GradeLookup.vue'

export default {
  name: 'App',
  components: {
    login
  }
}
</script>

<style>
body{
  /*取消body标签的默认样式*/
  margin: 0;
  padding: 0;
  /*body的大小就是浏览器的全屏*/
  width: 100vh;
  height: 80vh;
}
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin:auto;
  width:100%;
  height: 100%;
}
</style>
