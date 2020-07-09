import Vue from 'vue'

const languages = Vue.resource('/language{/id}')

export default {
	add: language => languages.save({}, language),
	update: language => languages.update({id: language.id}, language),
    remove: id => languages.remove({id: id})
}