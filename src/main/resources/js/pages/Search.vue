<template>
	<div>
	<div class="mx-auto shadow-sm p-3 mt-2 mb-5 bg-white rounded" style="width: 70%;">
		<h3>{{$t('search')}}</h3>
		    	<form>
			  <div class="form-group row">
			    <label for="domainName" class="col-sm-2 col-form-label">{{$t('position')}}</label>
			    <div class="col-sm-10">
			      <input type="text" class="form-control" v-model="search.position" required>
			    </div>
			  </div>
			  <div class="form-group row">
			    <label for="domainName" class="col-sm-2 col-form-label">{{$t('city')}}</label>
			    <div class="col-sm-10">
			      <input type="text" class="form-control" v-model="search.city" required>
			    </div>
			  </div>
			  <div v-if="search.largeSearch">
			   <div class="form-group row">
			    <label for="country" class="col-sm-2 col-form-label">{{$t('country')}}</label>
			    <div class="col-sm-10">
			      <select class="form-control" id="country" v-model="search.country">
  			    	<option v-for="country in allcountries">{{country.name}}</option>
    			  </select>
			    </div>
			   </div>
			   <div class="form-group row" v-if="user.role==='JS'">
				<label class="col-sm-2 col-form-label" for="level">{{$t('domain')}}</label>
				<div class="col-sm-10">
    				<select class="form-control" id="level" v-model="search.domain">
  			    		<option v-for="domain in alldomains" :key="domain.id" :value="domain" >{{domain.name}}</option>
    				</select>
		  	   	</div>
			   </div>
			   <div class="form-group row">
			    <label for="salary" class="col-sm-2 col-form-label">{{$t('minSalaryYear')}}</label>
			    <div class="col-sm-10">
			      <input type="number" class="form-control" v-model="search.minSalary" required>
			    </div>
			   </div>
			  <div class="form-group row">
				<label class="col-sm-2 col-form-label" for="level">{{$t(user.role==='JS'?'experiencelevel':'candexperiencelevel')}}</label>
				<div class="col-sm-10">
    				<select v-if="user.role==='JS'" class="form-control" id="level" v-model="search.experience">
					<option value="-1">{{$t('showAll')}}</option>
					<option value="0">{{$t('beginner')}}</option>
  			    		<option value="1">6 {{$t('months')}} - 2 {{$t('year')}}</option>
  			    		<option value="2">2 - 5 {{$t('years')}}</option>
  			    		<option value="3">5 - 9 {{$t('years')}}</option>
					<option value="4"> + 10 {{$t('years')}}</option>
    				</select>
					<input v-else type="number" class="form-control" v-model="yearsOfExperience" required>
				</div>
		          </div>
			  <div class="form-group row">
				<label class="col-sm-2 col-form-label" for="level">{{$t(user.role==='JS'?'youreducationlevel':'candeducationlevel')}}</label>
				<div class="col-sm-10">
    				<select class="form-control" id="level" v-model="search.education">
					<option value="-1">{{$t('showAll')}}</option>
					<option value=0>BAC</option>
  			    		<option value=1>BAC+1</option>
   			    		<option value=2>BAC+2</option>
  			    		<option value=3>BAC+3</option>
  			    		<option value=4>BAC+4</option>
  			    		<option value=5>BAC+5</option>
			    		<option value=8>BAC+8</option>
    				</select>
				</div>
		  	  </div>
			  <div class="form-group row">
			    <label for="domainName" class="col-sm-2 col-form-label">{{$t('competences')}}. <span class='info'>({{$t('competencesInfo')}})</span></label>
			    <div class="col-sm-10">
			      <input type="text" class="form-control" v-model="search.competences" required>
			    </div>
			  </div>
			  <div v-if="user.role==='HH'" class="mb-5">
			  	<div>{{$t('yourlanguages')}} : <span v-if="search.languages&&search.languages.length === 0">{{$t('nolanguages')}}</span>
				<a href="#" @click.stop="addForm('Lg')" class="img-btn"><img :src="addLang?'/images/minus.png':'/images/add.png'" alt="add" height="25"></a>
						 <ul v-if="search.languages.length > 0">
							<li v-for="lang in search.languages">{{lang.language}} {{lang.level}}</li>
						 </ul>
		 	 	</div>
		  	 	<div class="form-group row shadow p-3 mb-5 bg-white rounded" v-if="addLang">
		  			<label for="title" class="col-sm-2 col-form-label ml-5" required>{{$t('languageLabel')}}</label>
		    			<div class="col-sm-3">
							<select class="form-control" id="level" v-model="languageToAdd.language">
  			    				<option v-for="language in alllanguages">{{language.name}}</option>
    						</select>
		    			</div>
					<label class="col-sm-2 col-form-label ml-5" for="level">{{$t('languagelevel')}}</label>
					<div class="col-sm-3">
    						<select class="form-control" id="level" v-model="languageToAdd.level">
  			    				<option>A1</option>
   			    				<option>A2</option>
  			    				<option>B1</option>
  			    				<option>B2</option>
  			    				<option>C1</option>
							<option>C2</option>
    						</select>
					</div>
					<button class="btn btn-secondary" @click.stop="addLanguage"> {{$t('add')}} </button>
		  	  	</div>
			      </div>
			  </div>
			  <div class="form-group row">
			    <div class="col-sm-10">
			      <button class="btn btn-primary" @click.stop="submit">{{$t('search')}}</button>
			      <button class="btn btn-outline-primary" @click.stop="largeSearch">{{$t(search.largeSearch?'smallsearch':'bigsearch')}}</button>
			    </div>
			  </div>
		</form>
	</div>
	<div class="mx-auto" v-for="post in result" style="width: 70%;">
	<div class="shadow-lg p-4 mb-2 bg-white rounded advertise" @click="details(post)">
		<div class="text-success" v-if="allreadySent(post)">{{$t('allreadyApplied')}}</div>
		<table class="table">
		  <tbody>
		    <tr>
		      <th scope="row">
			<div class="position-title">{{post.position}}</div>
			<div class="position-title" v-if="post.showSalary">
				<span v-if="!post.maxSalary">{{$t('from')}} </span>{{post.minSalary}}
				<span v-if="post.maxSalary"> - {{post.maxSalary}}</span>
			</div>
		      </th>
		      <td class="bg-light text-secondary"><div class="text-center ml-2"><strong>{{post.description}}</strong></div>
		      </td>
		      <td><div class="company-title">{{post.companyName}}</div>
				
		      </td>
		    </tr>
		  </tbody>
		</table>
		<div class="float-right"><em>{{post.city}} {{post.country}}</em></div>
		<div>{{$t('typeContrat')}} : {{post.typeContrat?post.typeContrat : "Non reinseigné"}}</div>
		</div>
	    </div>
	</div>
</template>

<script>
    import { mapState, mapActions } from 'vuex'
    export default {
	computed: mapState(['user', 'alldomains', 'alllanguages', 'allcountries', 'answers', 'requests']),
        data() {
            return {
		  search : '',
		  result : [],
		  languageToAdd : {
			language : '',
			level : ''
		  },
	    	  addLang: false,
		  yearsOfExperience: 0
            }
        },
        methods: {
         ...mapActions(['searchAction']),
	 async submit(event){
		event.preventDefault();
		var response = "";
		if(this.user.role === 'JS'){
			response = await this.$http.post("searches/ad",this.search);
		}
		else if(this.user.role === 'HH'){
			this.search.experience = this.yearsOfExperience === 0? -1: this.yearsOfExperience*12;
			response = await this.$http.post("searches/cv",this.search);
		}
		this.result = await response.json();
		this.clearSearch();
	 },
	 largeSearch(event){
		event.preventDefault();
         	this.search.largeSearch = !this.search.largeSearch;
		if(!this.search.largeSearch){
			this.clearSearch();
		}
         },
	 clearSearch(){
		this.search = {
			position : '',
			city:'',
			country:'',
			domain:null,
			salary:0,
			experience: -1,
			education:-1,
			languages:[],
			competences:'',
			largeSearch:false,
			typeContrat: ''
		};
	 },
	 addLanguage(){
		this.search.languages.push({...this.languageToAdd});
		this.clearLanguage();
		this.addLang = false;
	 },
	 addForm(form){
		if(form ==="Lg"){
			this.addLang = !this.addLang;
		}
	 },
	 clearLanguage(){
		this.languageToAdd = {
			language : '',
			level : ''
		};
	 },
	  details(post){
		this.$router.push({ path: '/annonceAnswer', query: { post }})
	  },
	  allreadySent(post){
		return this.requests.findIndex(r=>r.postId===post.id)===-1?false:true;
	  }
	},
	 created(){
		this.search = {
			position : '',
			city:'',
			country:'',
			domain:null,
			salary:0,
			experience: -1,
			education:-1,
			languages:[],
			competences:'',
			largeSearch:false,
			typeContrat: ''
		};
	}
}

</script>

<style>
</style>