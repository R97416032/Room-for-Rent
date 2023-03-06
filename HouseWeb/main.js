import Vue from 'vue'
import App from './App'

import store from './store'   //引入vuex

//图片预览
import VueDirectiveImagePreviewer from 'vue-directive-image-previewer'
import 'vue-directive-image-previewer/dist/assets/style.css'
Vue.use(VueDirectiveImagePreviewer) 


// 挂载vuex
Vue.prototype.$store = store;




Vue.config.productionTip = false

App.mpType = 'app'

const app = new Vue({
    ...App
})
app.$mount()
