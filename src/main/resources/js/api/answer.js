import Vue from 'vue'

const answers = Vue.resource('/answer{/id}')

export default {
	add: answer => answers.save({}, answer),
	update: answer => answers.update({id: answer.id}, answer),
    remove: id => answers.remove({id: id})
}