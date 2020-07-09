<template>
	<div class="mx-auto shadow-sm p-3 mt-2 mb-5 bg-white rounded" style="width: 70%;">
		<h3>{{$t('requests'+user.role)}}</h3>
	  <table class="table">
	  <thead class="thead-dark">
	    <tr>
	      <th scope="col">{{$t('position')}}</th>
	      <th scope="col">{{$t('name'+user.role)}}</th>
	      <th scope="col">{{$t('city')}}</th>
	      <th scope="col">{{$t('link')}}</th>
	      <th scope="col" style="width: 10vw">{{$t('status')}}</th>
	      <th scope="col" style="width: 10vw">{{$t('talks')}}</th>
	    </tr>
	  </thead>
	  <tbody>
	    <tr v-for="request in requests" :key="request.id">
 	      <th scope="row">{{request.post?request.post.position:'-'}}</th>
	      <th scope="row">{{user.role=='JS'?request.post.companyName : request.post.username}}</th>
	      <th scope="row">{{request.post?request.post.city:'-'}}</th>
	      <td><a href="#" class="link mb-2" @click="details(request.post)">{{$t('requests'+user.role)}}</a></td>
	      <td>{{$t(request.status)}}</td>
	      <td><router-link class="nav-link" :to="{ path: '/chat/'+request.author.id, query: { post : request.post }}">{{$t('showConversation')}}</router-link></td>
	    </tr>
	  </tbody>
	</table>

	</div>
</template>

<script>
    import { mapState, mapActions } from 'vuex'
    export default {
	computed: mapState(['requests', 'user']),
        data() {
            return {
      		   domainToModifyId : ""
            }
        },
        methods: {
		  details(post){
			this.$router.push({ path: '/annonceAnswer', query: { post }})
		  }
        }
    }

</script>

<style>
</style>