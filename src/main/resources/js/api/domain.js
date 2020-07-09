import Vue from 'vue'

const domains = Vue.resource('/domain{/id}')
export default {
	add: domain => domains.save({}, domain),
	update: domain => domains.update({id: domain.id}, domain),
    remove: id => domains.remove({id: id})
}