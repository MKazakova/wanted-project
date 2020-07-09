import Vue from 'vue'

const users = Vue.resource('/wantedUser')

export default {
    add: jobseeker => jobseekers.save({}, jobseeker),
    update: user => users.update({id: user.id}, user, type),
   // remove: id => messages.remove({id})
}