<template>
  <router-link to="/">登录  </router-link>

  <router-link to="/register">注册  </router-link>

  <router-link to="/student">学生个人主页  </router-link>

  <router-link to="/gradelookup">查成绩</router-link>

  <router-view></router-view>
</template>

<script>
// import router from './router';

</script>

<style>
body {
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
  margin: auto;
  width: 100%;
  height: 100%;
}
</style>