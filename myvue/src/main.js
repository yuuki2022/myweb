import { createApp } from 'vue'; // Import createApp instead of Vue
import App from './App.vue';
import router from './router'; // Import your router configuration
import store from './store'; 


import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'

const app = createApp(App);

app.use(ElementPlus)
app.use(store); 
app.use(router);
app.mount('#app');
