import Vue from 'vue'

const jobseekers = Vue.resource('/jobSeeker{/id}')
export default {
    update: user => jobseekers.update({id: user.id}, user.profile),
   // remove: id => messages.remove({id})
}