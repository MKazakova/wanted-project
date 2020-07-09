<template>
	<div class="mx-auto shadow-sm p-3 mt-2 mb-5 bg-white rounded" style="width: 70%;">
	  <router-link class="btn btn-outline-dark mb-2" to="/addlanguage">{{$t('addlanguage')}}</router-link>
	  <table class="table">
	  <thead class="thead-dark">
	    <tr>
	      <th scope="col">id</th>
	      <th scope="col">{{$t('name')}}</th>
	      <th scope="col" style="width: 10vw">{{$t('languages')}}</th>
	    </tr>
	  </thead>
	  <tbody>
	    <tr v-for="language in alllanguages" :key="language.id">
 	      <th scope="row">{{language.id}}</th>
	      <td v-if="languageToModifyId==language.id"><input type="text" class="form-control" v-model="language.name"></td>
	      <td v-else>{{language.name}}</td>
	      <td scope="row" v-if="languageToModifyId==language.id">
		 <button type="button" class="btn btn-primary btn-sm" @click.stop="validateEdit(language)">{{$t('submit')}}</button></td>
	      <td scope="row" v-else>
		<a href="#" @click="remove(language)" class="img-btn"><img src="/images/delete.png" alt="delete" height="30"></a>
		<a href="#" @click="edit(language.id)" class="img-btn"><img src="/images/edit-icon.png" alt="edit" height="30"></a>
	      </td>
	    </tr>
	  </tbody>
	</table>

	</div>
</template>

<script>
    import { mapState, mapActions } from 'vuex'
    export default {
	name: 'Langues',
	computed: mapState(['alllanguages']),
        data() {
            return {
      		   languageToModifyId : ""
            }
        },
        methods: {
         ...mapActions(['addLanguageAction', 'removeLanguageAction', 'updateLanguageAction']),
            remove(language) {
            	this.removeLanguageAction(language);
		this.deselect();
            },
	    edit(id){
		this.languageToModifyId=id;
	    },
	    validateEdit(language){
		this.updateLanguageAction(language);
		this.deselect();
	    },
	    deselect(){
		this.languageToModifyId="";
	    }
        }
    }

</script>

<style>
</style>