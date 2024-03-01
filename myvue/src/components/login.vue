<template>
  <div id="login">
    <h1>考试系统登录</h1>
    <form method="post">
      <input type="text" value="账号">
      <input type="text" value="密码">
      <button type="submit">确认</button>
      <p>密码重设</p>
    </form>
  </div>
</template>

<script>
export default {
  name: 'TestComponent',
}
</script>

<style scoped>
/*将form整体页面居中*/
h1{
  margin: auto;

}

#login{
  top: 50%;
}
form {
  width: 41.667%;
  height: 55.556%;
  border-radius: 50px;
  margin: auto;
  border: 3px;
  /*把内容居中*/
  text-align: center;
  
}
input {
  width: 85%;
  height: 15%;
  border-radius: 20px;
  border: 1px;
  display: block;
  text-align: center;
}

</style>
