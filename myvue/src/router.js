import { createRouter, createWebHistory } from 'vue-router'; // Import necessary functions from vue-router
import login from './components/login.vue';
import GradeLookup from './components/GradeLookup.vue';


// Vue.use(Router);

// export default new Router({
//     routes: [
//         {
//             path: '/',
//             name: 'login',
//             component: login
//         },
//         {
//             path: '/gradelookup',
//             name: 'GradeLookup',
//             component: GradeLookup
//         }
//     ]
// });


const routes = [
    {
      path: '/Gardelookup',
      name: 'gradelookup',
      component: GradeLookup
    },
    {
      path: '/',
      name: 'login',
      component: login
    }
  ];
  
  const router = createRouter({
    history: createWebHistory(),
    routes
  });
  
  export default router;
  