import Vue from 'vue'

const annonces = Vue.resource('/annonce{/id}')
export default {
    update: ans => annonces.update(ans),
    remove: id => annonces.remove({id})
}