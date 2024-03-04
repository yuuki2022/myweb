import { createApp } from 'vue'
import App from './App.vue'
import router from './router'


const app = createApp(App)
app.use(router)
app.mount('#app')

app.prototype.$courseId={
    "计算机网络":1,
    "计算机组成原理":2,
    "计算机操作系统":3,
    "数据结构":4,
};
app.prototype.$course=[0,"计算机网络","计算机组成原理","计算机操作系统","数据结构"]
