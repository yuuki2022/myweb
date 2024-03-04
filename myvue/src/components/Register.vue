<template>
    <div id="login">
      <h1>考试系统登录</h1>
      <form @submit.prevent="submitForm">
        <br>
        <input type="text" placeholder="账号" id="account" v-model="account">
        <input type="password" placeholder="密码" id="password" v-model="password">
        <br>
        <button type="submit">登录</button>
        <br>
        <p><a href="">重设密码</a></p>
      </form>
    </div>
  </template>
  
  <script>
  export default {
    name: 'RegistComponent',
    data() {
      return {
        account: '',
        password: ''
      }
    },
    methods: {
      submitForm() {
      fetch('http://localhost:8081/authentication', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify({
          adminName: this.account,
          saltPassword: this.password
        })
      })
      .then(res => res.json())
      .then(res => {
        console.log(res)
        if (res.code === '200') {
          alert(res['message'])
        } else {
          alert(res['message'])
        }
      })
    }
    }
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
    margin-top: 20%;
    margin-left: 72%;
    width: 50.667%;
    height: 45.556%;
  
  }
  form {
    width: 90.667%;
    height: 90.556%;
    border-radius: 30px;
    margin: 0 auto;
    border: 3px solid #000;
    /*所有元素锤子居中*/
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    padding: 0%;
    background-color:  #F5F5F3;
  
  }
  input {
    width: 85%;
    height: 15%;
    border-radius: 10px;
    border: 1px solid #000;
    display: block;
    text-align: center;
    /*设置字体大小 */
    font-size: 20px;
  }
  #account{
    margin-bottom: 3%;
  }
  #password{
    margin-bottom: 3%;
  }
  button {
    width: 85%;
    height: 15%;
    border-radius: 20px;
    border: 1px solid #000;
    display: block;
    text-align: center;
    /*设置字体大小 */
    font-size: 20px;
    background-color: #D9D9D9;
    width: 20%;
  }
  button:hover{
    background-color: #BEBEBE;
  }
  button:active{
    background-color: #A9A9A9;
  }
  
  p{
    margin-top: 3%;
  }
  
  </style>
  