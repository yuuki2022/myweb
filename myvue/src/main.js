import { createApp } from 'vue'; // Import createApp instead of Vue
import App from './App.vue';
import router from './router'; // Import your router configuration


import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'

const app = createApp(App);

app.use(ElementPlus)




// Create the Vue application instance


// Add the router to the Vue application instance
app.use(router);

// Mount the Vue application instance to the DOM
app.mount('#app');
