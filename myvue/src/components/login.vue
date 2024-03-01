<template>
  <div id="login">
    <h1>考试系统登录</h1>
    <form method="post">
      <input type="text" value="账号" id="account">
      <input type="password" value="密码" id="password">
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
p{
  /*取消p标签的默认样式*/
  margin: 0;
  padding: 0;

}
h1{
  margin-left: auto;
  padding: 0;
}

#login{ 
  top: 50%;
}
form {
  width: 41.667%;
  height: 80.556%;
  border-radius: 50px;
  margin: 0 auto;
  border: 3px;
  /*所有元素锤子居中*/
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  padding: 0%;
  
}
input {
  width: 35%;
  height: 85%;
  border-radius: 20px;
  border: 1px solid #000;
  display: block;
  text-align: center;
}
#account{
  margin-bottom: 3%;
}
#password{
  margin-bottom: 3%;
}

</style>
