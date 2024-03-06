import { createRouter, createWebHistory } from 'vue-router'; // Import necessary functions from vue-router
import login from './components/login.vue';
import GradeLookup from './components/GradeLookup.vue';
import Student from './components/Student.vue';
import Register from './components/Register.vue';
import CreateQuestion from './components/CreateQuestion.vue';
import Exam from './components/Exam.vue'



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

export default router;
