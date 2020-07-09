import Vue from 'vue';
import VueResource from 'vue-resource';
import 'api/resource';
import App from 'pages/Home.vue';
import router from 'router/router'
import '@babel/polyfill'
import store from 'store/store'
import {i18n} from 'i18n/i18n'
import 'bootstrap'
import 'assets/app.scss'
import VModal from 'vue-js-modal'

Vue.use(VModal)

Vue.use(VueResource);

new Vue({
    el: '#app',
    router,
    i18n,
    store,
    render: a => a(App)
});