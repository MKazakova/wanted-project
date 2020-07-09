<template>
	<div class="mx-auto shadow-sm p-3 mt-2 mb-5 bg-white rounded" style="width: 70%;">
	  <router-link class="btn btn-outline-dark mb-2" to="/addcountry">{{$t('addcountry')}}</router-link>
	  <table class="table">
	  <thead class="thead-dark">
	    <tr>
	      <th scope="col">id</th>
	      <th scope="col">{{$t('name')}}</th>
	      <th scope="col" style="width: 10vw">{{$t('countries')}}</th>
	    </tr>
	  </thead>
	  <tbody>
	    <tr v-for="country in allcountries" :key="country.id">
 	      <th scope="row">{{country.id}}</th>
	      <td v-if="countryToModifyId==country.id"><input type="text" class="form-control" v-model="country.name"></td>
	      <td v-else>{{country.name}}</td>
	      <td scope="row" v-if="countryToModifyId==country.id">
		 <button type="button" class="btn btn-primary btn-sm" @click.stop="validateEdit(country)">{{$t('submit')}}</button></td>
	      <td scope="row" v-else>
		<a href="#" @click="remove(country)" class="img-btn"><img src="/images/delete.png" alt="delete" height="30"></a>
		<a href="#" @click="edit(country.id)" class="img-btn"><img src="/images/edit-icon.png" alt="edit" height="30"></a>
	      </td>
	    </tr>
	  </tbody>
	</table>

	</div>
</template>

<script>
    import { mapState, mapActions } from 'vuex'
    export default {
	computed: mapState(['allcountries']),
        data() {
            return {
      		   countryToModifyId : ""
            }
        },
        methods: {
         ...mapActions(['addCountryAction', 'removeCountryAction', 'updateCountryAction']),
            remove(country) {
            	this.removeCountryAction(country);
				this.deselect();
	            },
		    edit(id){
				this.countryToModifyId=id;
		    },
		    validateEdit(country){
				this.updateCountryAction(country);
				this.deselect();
		    },
		    deselect(){
				this.countryToModifyId="";
		    }
        }
    }

</script>

<style>
</style>