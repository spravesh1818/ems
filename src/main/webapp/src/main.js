import { createApp } from 'vue'
import App from './app/app.vue'
import './../node_modules/bulma/css/bulma.css';

import router from './app/app-routes';
import store from "@/app/app-state";
createApp(App).use(router).use(store).mount('#app')
