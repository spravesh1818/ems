import { createApp } from 'vue'
import App from './app/app.vue'
import './../node_modules/bulma/css/bulma.css';
import router from './app/app-routes';
createApp(App).use(router).mount('#app')
