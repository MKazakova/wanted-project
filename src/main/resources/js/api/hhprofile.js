import Vue from 'vue'

const headhunters = Vue.resource('/headHunter{/id}')
export default {
    update: user => headhunters.update({id: user.id}, user.profile),
   // remove: id => messages.remove({id})
}