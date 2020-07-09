<template>
	<div>
		<div class="mx-auto mt-2" v-for="chat in chats" style="width: 70%;">
		
			<div v-if="!chat.messages.length==0" class="shadow-lg p-4 bg-white rounded advertise">
					<router-link v-if="user.role==='JS'" class="nav-link" :to="{ path: '/chat/'+chat.jsId, query: { post : chat.hh }}">
						{{$t('chatwith', {name: chat.hh.author.companyName, post: chat.hh.position})}}
					</router-link><br/>
					<router-link v-if="user.role==='HH'" class="nav-link" :to="{ path: '/chat/'+chat.hhId, query: { post : chat.js }}">
						{{$t('chatwith', {name: chat.js.author.username, post: chat.js.position})}}
					</router-link>
			</div>
		</div>
	</div>
</template>
<script>
    import { mapState, mapMutations } from 'vuex'
    export default {
        computed: mapState(['user', 'chats']),
		methods: {
		}
}
</script>