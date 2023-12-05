import './assets/main.css'
// 自定义全局css
import './assets/global.css'
import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
createApp(App).mount('#app')
new VueElement({
    router,
    render:h=>h(App),
}).$mount('#app')
