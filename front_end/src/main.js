import Vue from "vue";
import "./plugins/axios";
import index from "./index.vue";
import './plugins/iview.js'
import router from './router'

Vue.config.productionTip = false;

new Vue({
  router,
  render: h => h(index)
}).$mount("#app");
