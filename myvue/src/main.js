import { createApp } from 'vue'; // Import createApp instead of Vue
import App from './App.vue';
import router from './router'; // Import your router configuration

// Create the Vue application instance
const app = createApp(App);

// Add the router to the Vue application instance
app.use(router);

// Mount the Vue application instance to the DOM
app.mount('#app');
