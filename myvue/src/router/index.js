import {createRouter, createWebHistory} from 'vue-router'
import Student from '../components/Student.vue'
import Login from '../components/Login.vue'


const routes = [
    {
        path: '/student',
        name: 'Student',
        component: Student
    },
    {
        path: '/login',
        name: 'Login',
        component: Login
    }
]

const router = createRouter({
    history: createWebHistory(),
    routes
})

export default router;

