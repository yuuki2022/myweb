import { createRouter, createWebHistory } from 'vue-router'; // Import necessary functions from vue-router
import login from './components/login.vue';
import GradeLookup from './components/GradeLookup.vue';
import Student from './components/Student.vue';
import Register from './components/Register.vue';
import CreateQuestion from './components/CreateQuestion.vue';
import Exam from './components/Exam.vue'
import store from './store';
import { ElMessage } from 'element-plus';



const routes = [
  {
    path: '/gradelookup',
    name: 'gradelookup',
    component: GradeLookup
  },
  {
    path: '/',
    name: 'login',
    component: login
  },
  {
    path: '/student',
    name: 'student',
    component: Student
  },
  {
    path: "/register",
    name: "register",
    component: Register
  },
  {
    path: '/createQuestion',
    name: 'CreateQuestion',
    component: CreateQuestion
  }, 
  {
    path: '/exam',
    name: 'Exam',
    component: Exam
  },
];

const router = createRouter({
  history: createWebHistory(),
  routes
});

router.beforeEach((to, from, next) => {
  // 假设 `store.state.userRole` 存储了当前用户的角色
  const role = store.state.role;
  console.log(role)
  if ((to.name === 'gradelookup' || to.name === 'CreateQuestion') && role === 'student') {
    // 如果用户是学生并且试图访问 GradeLookup 或 CreateQuestion 页面，重定向到 Student 页面
    ElMessage.error('你无法访问管理员页面');
    next({ name: 'student' });
  } else if ((to.name !== 'register' && to.name !== 'login') && role === 'visitor') {
    // 如果用户是访客并且试图访问非 Register 或 Login 页面，重定向到 Login 页面
    ElMessage.error('你需要登录才能访问这个页面');
    next({ name: 'login' });
  } else if ((to.name === 'Exam' || to.name === 'student') && role === 'admin') {
    // 如果用户是管理员并且试图访问 Exam 或 GradeLookup 页面，重定向到 CreateQuestion 页面
    ElMessage.error('你无法访问学生页面');
    next({ name: 'gradelookup' });
  } else {
    // 否则，允许路由
    next();
  }
});


export default router;
