<template>
  <div class="background"></div>
  <div id="login">
    <h1>考试系统登录</h1>
    <form @submit.prevent="submitForm">
      <br>
      <input type="text" placeholder="账号" id="account" v-model="account">
      <input type="password" placeholder="密码" id="password" v-model="password">
      <br>
      <button type="submit">登录</button>
      <br>
      <p><u><a href="./register">重设密码</a></u></p>
    </form>
  </div>
</template>

<script lang="js">
import axios from 'axios';
import { ElMessage } from 'element-plus'
import { useStore } from 'vuex'
import CryptoJS from 'crypto-js';


export default {
  name: 'LoginComponent',
  data() {
    const store = useStore()
    return {
      store,
      account: '',
      password: ''
    }
  },


  mounted() {
    this.store.commit('setRole', 'visitor')
    // store.state.username = ''
    this.store.commit('setUserName', '')
  },
  
  methods: {
    submitForm() {
      axios.post(`${this.store.state.path}authentication`, {
        adminName: this.account,
        saltPassword: CryptoJS.SHA256(this.password).toString()
      })
        .then(res => {
          console.log(res)
          if (res.data['code'] === '200') {
            if (res.data['message'] === 'student') {
              ElMessage.success('学生登录成功')
              this.store.commit('setUserName', this.account)
              this.store.commit('setRole', 'student')
              this.$router.push('/Student')
            } else {
              ElMessage.success('管理员登录成功')
              this.store.commit('setUserName', this.account)
              this.store.commit('setRole', 'admin')
              this.$router.push('/GradeLookup')
            }
          } else {
            ElMessage.error(res.data['message'])
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

html {
  height: 100vh;
  width: 100vh;

}

a {
  color: blue;
  text-decoration: none;
}

a:hover {
  color: red;
  text-decoration: underline;
}

h1 {
  margin-left: auto;
  padding: 0;
}

#login {
  margin-top: 25%;
  margin-left: 70%;
  width: 50.667%;
  height: 45.556%;

}



form {
  width: 90.667%;
  height: 90.556%;
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
  width: 85%;
  height: 15%;
  border-radius: 10px;
  border: 1px solid #000;
  display: block;
  text-align: center;
  /*设置字体大小 */
  font-size: 20px;
}

#account {
  margin-bottom: 3%;
}

#password {
  margin-bottom: 3%;
}

button {
  height: 15%;
  border-radius: 10px;
  border: 1px solid #000;
  text-align: center;
  /*设置字体大小 */
  font-size: 20px;
  background-color: #D9D9D9;
  width: 40%;
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
