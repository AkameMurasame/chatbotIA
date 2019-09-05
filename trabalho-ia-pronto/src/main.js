import Vue from 'vue'
import App from './App'
import VueFire from 'vuefire'
import firebase from 'firebase/app'
import Buefy from 'buefy'
import 'firebase/firestore'
import 'buefy/lib/buefy.css'
import VModal from 'vue-js-modal'


Vue.config.productionTip = false;
Vue.use(VueFire);

firebase.initializeApp({
  projectId: 'trabson-de-ia',
  databaseURL: 'https://trabson-de-ia.firebaseio.com'
});

Vue.prototype.$db = firebase.firestore();
Vue.use(Buefy);
Vue.use(VModal, { dynamic: true, injectModalsContainer: true })
/* eslint-disable no-new */
new Vue({
  el: '#app',
  render: h => h(App)
});
