<template>
	<div class="mx-auto shadow-sm p-3 mt-2 mb-5 bg-white rounded" style="width: 70%;">
	   <form>
		 <div class="form-group row">
		    <label for="firstName" class="col-sm-2 col-form-label">{{$t('position')}}</label>
		    <div class="col-sm-10">
		      <input type="text" class="form-control" v-model="advertisement.position" required>
		    </div>
		  </div>
		  <div class="form-group row">
		    <label for="lastName" class="col-sm-2 col-form-label">{{$t('competences')}}</label>
		    <div class="col-sm-10">
		      <input type="text" class="form-control" v-model="advertisement.competences" required>
		    </div>
		  </div>
		  <div class="form-group row">
		    <label for="lastName" class="col-sm-2 col-form-label">{{$t('minSalaryYear')}}</label>
		    <div class="col-sm-10">
		      <input type="number" class="form-control" v-model="advertisement.minSalary">
		    </div>
		  </div>
		  <div class="form-group row">
		    <label for="lastName" class="col-sm-2 col-form-label">{{$t('maxSalaryYear')}}</label>
		    <div class="col-sm-10">
		      <input type="number" class="form-control" v-model="advertisement.maxSalary">
		    </div>
		  </div>
		  <div class="form-group row">
			<label class="col-sm-3 form-check-label" for="exampleCheck1">{{$t('showSalaryYear')}}</label>
			<input type="checkbox" class="form-check-input col-sm-9" id="showsalary" v-model="advertisement.showSalary">
			<span class="info">{{$t('showSalaryInfo')}}</span>
		  </div>
		  <div class="form-group row">
		    <label for="city" class="col-sm-2 col-form-label">{{$t('country')}}</label>
		    <div class="col-sm-10">
			<select class="form-control" id="country" v-model="advertisement.country">
  			    <option v-for="country in allcountries">{{country.name}}</option>
    			</select>
		    </div>
		  </div>
		  <div class="form-group row">
		    <label for="city" class="col-sm-2 col-form-label">{{$t('domain')}}</label>
		    <div class="col-sm-10">
			<select class="form-control" id="domain" v-model="advertisement.workDomain">
  			    <option v-for="domain in alldomains" :value="domain">{{domain.name}}</option>
    			</select>
		    </div>
		  </div>
		  <div class="form-group row">
		    <label for="city" class="col-sm-2 col-form-label">{{$t('city')}}</label>
		    <div class="col-sm-10">
		      <input type="text" class="form-control" v-model="advertisement.city" placeholder="Votre ville">
		    </div>
		  </div>
		  <div class="form-group row">
		    <label for="description" class="col-sm-2 col-form-label" required>{{$t('postdescription')}}</label>
		    <div class="col-sm-10">
		      <input type="text" class="form-control" v-model="advertisement.description">
		    </div>
		  </div>
		  <div class="form-group row">
				<label class="col-sm-2 col-form-label" for="level">
					{{$t('educationlevel')}}
				</label>
				<div class="col-sm-8">
    				<select class="form-control" id="level" v-model="advertisement.bacPlusLevel">
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
				<label class="col-sm-2 col-form-label" for="level">{{$t('minexperiencelevel')}}</label>
				<div class="col-sm-8">
					<input type="number" step="0.1"  class="form-control" v-model="experienceYears" required>
				</div>
		  </div>
		  <div>{{$t('yourlanguages')}} : <span v-if="advertisement.languages.length === 0">{{$t('nolanguages')}}</span>
			<a href="#" @click.stop="addForm('Lg')" class="img-btn"><img :src="addLang?'/images/minus.png':'/images/add.png'" alt="add" height="25"></a>
				<ul v-if="advertisement.languages.length > 0">
					<li v-for="lang in advertisement.languages">{{lang.language}} {{lang.level}} 
						<a href="#" @click.stop="delLang(lang)" class="img-btn"><img src="/images/delete2.png" alt="delete" height="20"></a>
					</li>
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
		  <div class="form-group row mt-3">
		    <label for="city" class="col-sm-2 col-form-label">{{$t('typeContrat')}}</label>
		    <div class="col-sm-10">
		     	 <select class="form-control" id="level" v-model="advertisement.typeContrat">
  			    		<option>CDI</option>
   			    		<option>CDD</option>
    			 </select>
		    </div>
		  </div>
		  <h5>{{$t('addressemap')}}</h5>
		  <div id="map" style="width: 600px; height: 400px;"></div>
		  <div class="form-group row">
		    <div class="col-sm-10">
		      <button type="submit" class="btn btn-primary mt-3" @click.stop="submit"> {{$t('submit')}} </button>
		      <router-link class="btn btn-link" to="/annonces">{{$t('backToAnnonces')}}</router-link>
		    </div>
		  </div>
		  
		</form>
	</div>
</template>

<script>
    import { mapState, mapMutations, mapActions } from 'vuex'
    import { addHandler } from 'util/ws'
    
    export default {
        computed: mapState(['user' , 'alldomains', 'annonces', 'alllanguages', 'allcountries']),
        data() {
            return {
      		  advertisement : '',
			  experienceYears: "",
			  languageToAdd : {
				language : '',
				level : ''
			  },
			  addLang: false,
			  map: {},
			  markerSource: {}
	       }
        },
        methods: {
            ...mapActions(['addUserAnnonceAction']),
            	   async submit(event) {
            		event.preventDefault();
			this.advertisement.monthsOfExperience = this.experienceYears*12;
		        await this.addUserAnnonceAction(this.advertisement);
			this.$router.push({ path: '/annonces' })
                
            	    },
		    addLanguage(){
				this.advertisement.languages.push({...this.languageToAdd});
				this.clearLanguage();
				this.addLang = false;
		    },
		    addForm(form){
				if(form ==="Lg"){
					this.addLang = !this.addLang;
				}
		    },
		    delLang(language){
				this.advertisement.languages = this.advertisement.languages.filter(l=>l !== language);
		    },
		    clearLanguage(){
				this.languageToAdd = {
					language : '',
					level : ''
				  };
		    },
		    loadMap(){
			  	   this.markerSource = new ol.source.Vector(); 
				   var markerStyle = new ol.style.Style({
					  image: new ol.style.Icon(({
						anchor: [0.5, 0.5],
						anchorOrigin: 'bottom-left',
						anchorXUnits: 'fraction',
						anchorYUnits: 'pixels',
						opacity: 1,
						scale: 1,
						src: 'https://openlayers.org/en/latest/examples/data/icon.png'
					  }))
					});
			
					this.map = new ol.Map({
						 target: 'map',
						 layers: [
						   new ol.layer.Tile({
								  source: new ol.source.OSM()
							   }),
						   new ol.layer.Vector({
							  source: this.markerSource,
							  style: markerStyle,
						   })
						 ]
						 });
						 var view = new ol.View({
								  center: ol.proj.transform([2.350942, 48.858688], 'EPSG:4326', 'EPSG:3857'),
								  zoom: 5
						 });
						 this.map.setView(view);
						 const markerSource = this.markerSource;
						 var advertisement = this.advertisement;
						 this.map.on('singleclick', function(event){
						 	markerSource.clear();
							var lonLat = ol.proj.toLonLat(event.coordinate);
							var X=lonLat[0];
							var Y=lonLat[1];
							var iconFeatures = [];
							var iconFeature = new ol.Feature({
									geometry: new ol.geom.Point(ol.proj.transform([X, Y], 'EPSG:4326','EPSG:3857'))
							});
							markerSource.addFeature(iconFeature);
							advertisement.xCoord = X;
							advertisement.yCoord = Y;
							this.setView(view);
						});
						if(advertisement.xCoord&&advertisement.yCoord){
							this.addMarker(advertisement.xCoord, advertisement.yCoord);
						}
					
				},
				addMarker(X, Y){
					var iconFeatures = [];
			
					var iconFeature = new ol.Feature({
							geometry: new ol.geom.Point(ol.proj.transform([X, Y], 'EPSG:4326',
							  'EPSG:3857'))
					});
					
					this.markerSource.addFeature(iconFeature);
					var view = new ol.View({
							  center: ol.proj.transform([X, Y], 'EPSG:4326', 'EPSG:3857'),
							  zoom: 5
					});
			
					this.map.setView(view);
				}
				
	        },
			created(){
				this.advertisement = this.$route.params.id?this.annonces.find(a => a.id == this.$route.params.id):{
		               	position : '',
						competences : '',
						country : this.user.userProfile.country,
						city: this.user.userProfile.city,
						description: '',
						monthsOfExperience: 0,
						bacPlusLevel: 0,
						workDomain : this.alldomains[0],
						languages: [],
						minSalary: '',
						authorId: parseInt(this.user.userProfile.id),
						maxSalary: '',
						showSalary: false,
						typeContrat: 'CDI',
						authorId: this.user.userProfile.id,
						companyName : this.user.userProfile.companyName,
						email : this.user.email
		        };
				this.experienceYears = Math.round((this.advertisement.monthsOfExperience/12)*10)/10;
			},
			mounted(){
				this.loadMap();
			}
}

</script>

<style>
</style>