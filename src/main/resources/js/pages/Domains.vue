<template>
	<div class="mx-auto shadow-sm p-3 mt-2 mb-5 bg-white rounded" style="width: 70%;">
	  <router-link class="btn btn-outline-dark mb-2" to="domains">{{$t('adddomain')}}</router-link>
	  <table class="table">
	  <thead class="thead-dark">
	    <tr>
	      <th scope="col">id</th>
	      <th scope="col">{{$t('domainName')}}</th>
	      <th scope="col" style="width: 10vw">{{$t('actions')}}</th>
	    </tr>
	  </thead>
	  <tbody>
	    <tr v-for="domain in alldomains" :key="domain.id">
 	      <th scope="row">{{domain.id}}</th>
	      <td v-if="domainToModifyId==domain.id"><input type="text" class="form-control" v-model="domain.name"></td>
	      <td v-else>{{domain.name}}</td>
	      <td scope="row" v-if="domainToModifyId==domain.id">
		 <button type="button" class="btn btn-primary btn-sm" @click.stop="validateEdit(domain)">{{$t('submit')}}</button></td>
	      <td scope="row" v-else>
		<a href="#" @click="remove(domain)" class="img-btn"><img src="/images/delete.png" alt="delete" height="30"></a>
		<a href="#" @click="edit(domain.id)" class="img-btn"><img src="/images/edit-icon.png" alt="edit" height="30"></a></td>
	    </tr>
	  </tbody>
	</table>

	</div>
</template>

<script>
    import { mapState, mapActions } from 'vuex'
    export default {
	computed: mapState(['alldomains']),
        data() {
            return {
      		   domainToModifyId : ""
            }
        },
        methods: {
         ...mapActions(['addDomainAction', 'removeDomainAction', 'updateDomainAction']),
            remove(domain) {
            	this.removeDomainAction(domain);
		this.deselect();
            },
	    edit(id){
		this.domainToModifyId=id;
	    },
	    validateEdit(domain){
		this.updateDomainAction(domain);
		this.deselect();
	    },
	    deselect(){
		this.domainToModifyId="";
	    }
        }
    }

</script>

<style>
</style>