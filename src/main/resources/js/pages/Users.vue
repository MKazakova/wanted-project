<template>
	<div class="mx-auto shadow-sm p-3 mt-2 mb-5 bg-white rounded" style="width: 70%;">
	  <v-popup v-if="showModal" 
		:popupTitle="$t('edit')+' '+userToEdit.username"
		:user="userToEdit"
		@close="showModal = false" @edit="edit"></v-popup>
	  <table class="table">
	  <thead class="thead-dark">
	    <tr>
	      <th scope="col">id</th>
	      <th scope="col">{{$t('userName')}}</th>
	      <th scope="col" style="width: 10vw">{{$t('email')}}</th>
	      <th scope="col" style="width: 10vw">{{$t('role')}}</th>
	      <th scope="col" style="width: 10vw">{{$t('actions')}}</th>
	    </tr>
	  </thead>
	  <tbody>
	    <tr v-for="user in users" :key="user.id">
 	      <th scope="row">{{user.id}}</th>
	      <td>{{user.username}}</td>
	      <td>{{user.email}}</td>
	      <td>{{user.role}}</td>
	      <td>
		 	<button type="button" class="btn btn-primary btn-sm" @click="showEdit(user)">{{$t('edit')}}</button>
		  </td>
	    </tr>
	  </tbody>
	</table>
	</div>
</template>

<script>
	import vPopup from '../popup/Edit'
    export default {
    	components: {vPopup},
        data() {
            return {
      		   users: [],
      		   userToEdit: "",
      		   showModal: false
            }
        },
        methods: {
		    showEdit(user){
		    	this.userToEdit = user;
		    	this.showModal = true;
		    },
		    async edit(user){
		    	let result = await this.$http.post("/wantedUser", user);
		    	user = result.data;
		    	this.showModal = false;
		    }
        },
        async created(){
        	var result = await this.$http.get("/wantedUser");
        	this.users = result.data;
        }
    }

</script>

<style>
</style>