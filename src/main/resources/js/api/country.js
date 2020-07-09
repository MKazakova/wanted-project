import Vue from 'vue'

const countries = Vue.resource('/country{/id}')

export default {
	add: country => countries.save({}, country),
	update: country => countries.update({id: country.id}, country),
    remove: id => countries.remove({id: id})
}