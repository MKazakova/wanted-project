<template>
	<div class="mx-auto shadow-sm p-3 mt-2 mb-5 bg-white rounded" style="width: 70%;">
	   <form>
		 <div class="form-group row">
		    <label for="firstName" class="col-sm-2 col-form-label">{{$t('position')}}</label>
		    <div class="col-sm-10">
		      <input type="text" class="form-control" v-model="cv.position" required>
		    </div>
		  </div>
		  <div class="form-group row">
		    <label for="lastName" class="col-sm-2 col-form-label">{{$t('competences')}}</label>
		    <div class="col-sm-10">
		      <input type="text" class="form-control" v-model="cv.competences" required>
		    </div>
		  </div>
		  <div class="form-group row">
		    <label for="city" class="col-sm-2 col-form-label">{{$t('country')}}</label>
		    <div class="col-sm-10">
			<select class="form-control" id="country" v-model="cv.country">
  			    <option v-for="country in allcountries">{{country.name}}</option>
    		</select>
		    </div>
		  </div>
		  <div class="form-group row">
		    <label for="city" class="col-sm-2 col-form-label">{{$t('city')}}</label>
		    <div class="col-sm-10">
		      <input type="text" class="form-control" v-model="cv.city" placeholder="Votre ville">
		    </div>
		  </div>
		  <div class="form-group row">
		    <label for="lastName" class="col-sm-2 col-form-label">{{$t('minSalaryYear')}}</label>
		    <div class="col-sm-10">
		      <input type="number" class="form-control" v-model="cv.minSalary">
		    </div>
		  </div>
		  <div class="form-group row">
			<label class="col-sm-4 form-check-label" for="exampleCheck1">{{$t('showSalaryYear')}}</label>
			<input type="checkbox" class="form-check-input col-sm-8" id="showsalary" v-model="cv.showSalary">
			<span class="info">{{$t('showSalaryInfo')}}</span>
		  </div>
		  <div class="form-group row">
		    <label for="description" class="col-sm-2 col-form-label" required>{{$t('cvdescription')}}</label>
		    <div class="col-sm-10">
		      <input type="text" class="form-control" v-model="cv.description">
		    </div>
		  </div>
		 <div>{{$t('youreducation')}} : <span v-if="cv.educations.length === 0">{{$t('noeducation')}}</span>
		 	<a href="#" @click.stop="addForm('Ed')" class="img-btn"><img :src="addEd?'/images/minus.png':'/images/add.png'" alt="add" height="25"></a>
		 </div>
		 	<div class="col-sm-8 ml-5">
		 	<table v-if="cv.educations" class="table table-hover">
		  	<tbody  v-for="education in cv.educations">
		   	 <tr>
 		     	 <td>{{education.finishDate}}</td>
		     	 <td>{{education.title}}</td>
		     	 <td>{{education.establishment}}</td>
			 <td></td>
		   	 </tr>
		 	</tbody>
		 	</table>
		</div>
		  <fieldset v-if="addEd" class="form-group shadow p-3 mb-5 bg-white rounded">
		  	<p>{{$t('addeducation')}}</p>
			<div class="form-group row">
		  		<label for="title" class="col-sm-2 col-form-label ml-5" required>{{$t('educationtitle')}}</label>
		    		<div class="col-sm-8">
		      			<input type="text" class="form-control" v-model="educationToAdd.title">
		    		</div>
		  	</div>
			<div class="form-group row">
		  		<label for="title" class="col-sm-2 col-form-label ml-5" required>{{$t('establishment')}}</label>
		    		<div class="col-sm-8">
		      			<input type="text" class="form-control" v-model="educationToAdd.establishment">
		    		</div>
		  	</div>
			<div class="form-group row">
				<label class="col-sm-2 col-form-label ml-5" for="level">{{$t('educationlevel')}}</label>
				<div class="col-sm-8">
    				<select class="form-control" id="level" v-model="educationToAdd.bacPlusLevel">
  			    		<option>1</option>
   			    		<option>2</option>
  			    		<option>3</option>
  			    		<option>4</option>
  			    		<option>5</option>
			    		<option>8</option>
    				</select>
				</div>
		  	</div>
			<div class="form-group row">
				<label class="col-sm-2 col-form-label ml-5" for="level">{{$t('domains')}}</label>
				<div class="col-sm-8">
    				<select class="form-control" id="level" v-model="educationToAdd.domain">
  			    		<option v-for="domain in alldomains" :key="domain.id" :value="domain">{{domain.name}}</option>
    				</select>
				</div>
		  	</div>
			<div class="form-group row">
		  		<label for="title" class="col-sm-2 col-form-label ml-5" required>{{$t('educationfinishdate')}}</label>
		    		<div class="col-sm-8">
		      			<input type="date" class="form-control" v-model="educationToAdd.finishDate">
		    		</div>
		  	</div>
			<button class="btn btn-secondary" @click.stop="addEducation"> {{$t('add')}} </button>
		  </fieldset>
		 <div>{{$t('yourexperience')}} :  <span v-if="cv.experiences.length === 0">{{$t('noexperience')}}</span>
			<a href="#" @click.stop="addForm('Ex')" class="img-btn"><img :src="addEx?'/images/minus.png':'/images/add.png'" alt="add" height="25"></a>
		 </div>
			<div v-if="cv.experiences" class="col-sm-8 ml-5">
			 <table class="table table-hover">
			  <tbody  v-for="experience in cv.experiences">
			    <tr>
 			      <td>{{experience.startDate}} - {{experience.endDate}}</td>
			      <td>{{experience.position}}</td>
			      <td>{{experience.companyName}}</td>
			      <td></td>
			    </tr>
			  </tbody>
			 </table>
			</div>
		  <fieldset v-if="addEx" class="form-group shadow p-3 mb-5 bg-white rounded">
		  	<p>{{$t('addexperience')}}</p>
			<div class="form-group row">
		  		<label for="title" class="col-sm-2 col-form-label ml-5" required>{{$t('exposition')}}</label>
		    		<div class="col-sm-8">
		      			<input type="text" class="form-control" v-model="experienceToAdd.position">
		    		</div>
		  	</div>
			<div class="form-group row">
		  		<label for="title" class="col-sm-2 col-form-label ml-5" required>{{$t('companyName')}}</label>
		    		<div class="col-sm-8">
		      			<input type="text" class="form-control" v-model="experienceToAdd.companyName">
		    		</div>
		  	</div>
			<div class="form-group row">
		  		<label for="startDate" class="col-sm-2 col-form-label ml-5" required>{{$t('expstartDate')}}</label>
		    		<div class="col-sm-8">
		      			<input type="date" class="form-control" v-model="experienceToAdd.startDate">
		    		</div>
		  	</div>
			<div class="form-group row">
		  		<label for="startDate" class="col-sm-2 col-form-label ml-5" required>{{$t('expendDate')}}</label>
		    		<div class="col-sm-8">
		      			<input type="date" class="form-control" v-model="experienceToAdd.endDate">
		    		</div>
		  	</div>
			<div class="form-group row">
		  		<label for="title" class="col-sm-2 col-form-label ml-5" required>{{$t('description')}}</label>
		    		<div class="col-sm-8">
		      			<input type="textarea" class="form-control" v-model="experienceToAdd.description">
		    		</div>
		  	</div>
			<button class="btn btn-secondary" @click.stop="addExperience"> {{$t('add')}} </button>
		  </fieldset>
		  <div>{{$t('yourlanguages')}} : <span v-if="cv.languages.length === 0">{{$t('nolanguages')}}</span>
			<a href="#" @click.stop="addForm('Lg')" class="img-btn"><img :src="addLang?'/images/minus.png':'/images/add.png'" alt="add" height="25"></a>
						 <ul v-if="cv.languages.length > 0">
							<li v-for="lang in cv.languages">{{lang.language}} {{lang.level}}</li>
						 </ul>
		  </div>
		  <div class="form-group row shadow p-3 mb-5 bg-white rounded" v-if="addLang">
		  		<label for="title" class="col-sm-2 col-form-label ml-5" required>{{$t('languageLabel')}}</label>
		    		<div class="col-sm-3">
		      			<select class="form-control" id="language" v-model="languageToAdd.language">
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
		  <div class="form-group row">
				<label class="col-sm-2 col-form-label" for="level">{{$t('readyToMove')}}</label>
				<div class="custom-control custom-radio mr-2">
				  <input type="radio" id="customRadio1" name="customRadio"  @click.stop="setReady(true)" class="custom-control-input">
 				  <label class="custom-control-label" for="customRadio1">{{$t('yes')}} </label>
				</div>
				<div class="custom-control custom-radio">
				  <input type="radio" id="customRadio2" name="customRadio" @click.stop="setReady(false)" class="custom-control-input" checked>
				  <label class="custom-control-label" for="customRadio2">{{$t('no')}} </label>
				</div>
		  </div> 
		  <div class="form-group row">
		    <div class="col-sm-10">
		      <button type="submit" class="btn btn-primary mt-3" @click.stop="submit"> {{$t('submit')}} </button>
		      <router-link class="btn btn-link" to="/cvs">{{$t('backToCvs')}}</router-link>
		    </div>
		  </div>
		</form> 
	</div>
</template>

<script>
    import { mapState, mapMutations, mapActions } from 'vuex'
    import { addHandler } from 'util/ws'
    export default {
        computed: mapState(['user' , 'alldomains', 'cvs', 'allcountries', 'alllanguages']),
        data() {
            return {
      		  cv : '',
		  languageToAdd : {
			language : '',
			level : ''
		  },
		  educationToAdd: {
			bacPlusLevel : 0,
			domain : '',
			title : '',
			finishDate : '',
			establishment: ''
		  },
		  experienceToAdd: {
			position : '',
			startDate : '',
			endDate : '',
			companyName : '',
			description : ''
		  },
		  addEd: false,
		  addEx: false,
		  addLang: false
          }
        },
        methods: {
            ...mapActions(['addUserCvAction']),
            async submit(event) {
            	event.preventDefault();
            	await this.addUserCvAction(this.cv);
		this.$router.push({ path: '/cvs' })
                
            },
	    clearEducation(){
		this.educationToAdd = {
			bacPlusLevel : 0,
			domain : '',
			title : '',
			finishDate : '',
			establishment: ''
		  };
	    },
	    clearExperience(){
		this.experienceToAdd = {
			position : '',
			startDate : '',
			endDate : '',
			companyName : '',
			description : ''
		  };
	    },
	    clearLanguage(){
		this.languageToAdd = {
			language : '',
			level : ''
		  };
	    },
	    addEducation(){
		this.cv.educations.push({...this.educationToAdd});
		this.clearEducation();
		this.addEd = false;
	    },
	    addExperience(){
		this.cv.experiences.push({...this.experienceToAdd});
		this.clearExperience();
		this.addEx = false;
	    },
	    addLanguage(){
		this.cv.languages.push({...this.languageToAdd});
		this.clearLanguage();
		this.addLang = false;
	    },
	    addForm(form){
		if(form ==="Ed"){
			this.addEd = !this.addEd;
		}
		else if(form ==="Ex"){
			this.addEx = !this.addEx;
		}
		else if(form ==="Lg"){
			this.addLang = !this.addLang;
		}
	    },
	    setReady(value){
		this.cv.isReadyToMove = value;
	    }
        },
	created(){
		this.cv = this.$route.params.id?this.cvs.find(cv => cv.id == this.$route.params.id):{
               					position : '',
						competences : '',
						country : this.user.userProfile.country,
						city: this.user.userProfile.city,
						description: '',
						educations: [],
						experiences: [],
						domain : this.alldomains[0],
						languages: [],
						isReadyToMove: false,
						authorId: parseInt(this.user.userProfile.id),
						minSalary: 0,
						maxSalary: 0,
						showSalary: false,
						name: this.user.userName,
						email: this.user.email
               		};
	}
    }

</script>

<style>
</style>