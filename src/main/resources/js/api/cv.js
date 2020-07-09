import Vue from 'vue'

const cvs = Vue.resource('/cv{/id}')
export default {
    update: cvlist => cvs.update(cvlist),
    remove: id => cvs.remove({id})
}