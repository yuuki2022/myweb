<template>
  <div class="background"></div>
  <div id="login">
    <h1>密码重设</h1>
    <form @submit.prevent="submitForm">
      <br>
      <input type="text" placeholder="账号" id="account" v-model="account">
      <input type="password" placeholder="旧密码" id="oldPassword" v-model="oldPassword">
      <input type="password" placeholder="新密码" id="newPassword" v-model="newPassword">
      <input type="password" placeholder="确认新密码" id="ensureNewPassword" v-model="ensureNewPassword">
      <button type="submit">确认</button>
      <p><a href="./">返回登录</a></p>
<br>
    </form>
  </div>
</template>

<script>
import axios from 'axios'
import { ElMessage } from 'element-plus'

export default {
  name: 'RegisterComponent',
  data() {
    return {
      account: '',
      oldPassword: '',
      newPassword: '',
      ensureNewPassword: ''
    }
  },
  methods: {
    submitForm() {
      if (this.newPassword !== this.ensureNewPassword) {
        ElMessage.error('您的新密码两次输入不一致')
        return
      }
      axios.post(this.state.store.path+'changePassword', {
        adminName: this.account,
        oldPassword: this.oldPassword,
        newPassword: this.newPassword
      })
        .then(res => {
          console.log(res)
          if (res.data.code === '200') {
            ElMessage.success('您的密码修改成功')
            this.$router.push('/')
          } else {
            ElMessage.error(res.data.message)
          }
        })

    }
  }
}
</script>

<style scoped>
/*将form整体页面居中*/
p {
  /*取消p标签的默认样式*/
  margin: 0;
  padding: 0;

}

.background {
  background-image: url('../assets/img/login_backgroundimg.JPG');
  background-size: 100% 100%;
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  z-index: -1;
  /*背景图片透明 */
}


a{
  color: blue;
  text-decoration: none;
}
a:hover{
  color: red;
  text-decoration: underline;
}

h1 {
  margin-left: auto;
  padding: 0;
}

#login {
  margin-top: 20%;
  margin-left: 72%;
  width: 50.667%;
  height: 45.556%;
}

form {
  width: 90.667%;
  height: 93.556%;
  border-radius: 30px;
  margin: 0 auto;
  border: 3px #000;
  /*所有元素垂直居中*/
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  padding: 0%;
  background-color: #F5F5F3;
  opacity: 0.9;
}

input {
  width: 83%;
  height: 13%;
  border-radius: 10px;
  border: 1px solid #000;
  display: block;
  text-align: center;
  /*设置字体大小 */
  font-size: 20px;
  margin-bottom: 3%;
}

#account {
  margin-bottom: 3%;
}

#password {
  margin-bottom: 3%;
}

button {
  width: 85%;
  height: 15%;
  border-radius: 10px;
  border: 1px solid #000;
  display: block;
  text-align: center;
  /*设置字体大小 */
  font-size: 20px;
  background-color: #D9D9D9;
  width: 20%;
}

button:hover {
  background-color: #BEBEBE;
}

button:active {
  background-color: #A9A9A9;
}

p {
  margin-top: 3%;
}
</style>
