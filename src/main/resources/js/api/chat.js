import Vue from 'vue'

const chats = Vue.resource('/chat')

export default {
	add: chat => chats.save({}, chat)
}