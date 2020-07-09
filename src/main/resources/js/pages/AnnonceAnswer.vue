<template>
	<div class="mx-auto shadow-sm p-3 mt-2 mb-5 bg-white rounded" style="width: 70%;">
		<h3 class="company-title float-right">{{post.companyName}}</h3>
		<h2 class="position-title text-center">{{post.position}}</h2>
		<h4 v-if="post.role==='JS'" class="position-title text-center"><em><strong>{{post.author.firstName}} {{post.author.lastName}}</strong></em></h4>
		<div>{{post.description}}</div>
		<div v-if="post.role==='HH'&&hasCoordinates" id="map" style="width: 400px; height: 350px;" class="float-right"></div>
		<div v-if="post.role==='JS'" id="map" class="float-right">
			<img v-if="post.author.gender==='Female'" src="/images/femaleuser.png" alt="cvs" width="250px" class="float-right m-2">
			<img v-else src="/images/maleuser.png" alt="cvs" width="250px" class="float-right m-2">
		</div>
		<div><span class="position-title">{{$t('competences')}} : </span>
			{{post.competences}}
		</div>
		<div v-if="post.workDomain"><span class="position-title">{{$t('workDomain')}} : </span>
			{{post.workDomain.name}}
		</div>
		<div><span class="position-title">{{$t('country')}} : </span>
			{{post.country}}
		</div>
		<div><span class="position-title">{{$t('city')}} : </span>
			{{post.city}}
		</div>
		<div><span class="position-title">{{$t('email')}} : </span>
			{{post.email}}
		</div>
		<div v-if="user.role==='JS'"><span class="position-title">{{$t('experiencelevel')}} : </span>
			{{post.monthsOfExperience>12?post.monthsOfExperience/12 :post.monthsOfExperience}} {{post.monthsOfExperience>12?$t('years'):$t('months')}} 
		</div>
		<div v-else><span class="position-title">{{$t('experience')}} : </span>
			<div v-if="!post.experiences||post.experiences.length===0">{{$t('noexperience')}}</div>
			<div v-for="exp in post.experiences" class="ml-5">{{exp.startDate}} {{exp.endDate}} {{exp.position}} {{$t('at')}} {{exp.companyName}}</div>
		</div>
		<div v-if="user.role==='JS'"><span class="position-title">{{$t('educationlevel')}} : </span>
			BAC+{{post.bacPlusLevel}}
		</div>
		<div v-else>
			<span class="position-title">{{$t('education')}} : </span>
			<div v-if="!post.educations||post.educations.length===0">{{$t('noeducation')}}</div>
			<div v-for="educ in post.educations" class="ml-5">{{educ.finishDate}} : {{educ.title}}(BAC+{{educ.bacPlusLevel}}) {{$t('at')}} {{educ.establishment}} </div>
		</div>
		<div v-if="post.showSalary"><span class="position-title">{{$t('salary')}} : </span>
			{{post.minSalary}} - {{post.maxSalary}} 
		</div>
		<div v-if="post.languages&&post.languages.length!=0"><span class="position-title">{{$t('languages')}} : </span>
			<ul>
				<li v-for="lang in post.languages">{{lang.language}} {{lang.level}} 
				</li>
			</ul>
		</div>
		<div v-if="post.typeContrat"><span class="position-title">{{$t('typeContrat')}} : </span>
			{{post.typeContrat}} 
		</div>
		<div  v-if="!allreadySent()">
			<div class="text-center">
				<h2 class="position-title">{{$t('interested')}} ?</h2>
			</div>
			<div v-if="user.role==='JS'"> 
				<div class="row"><span class="position-title col-3"> {{$t('applicationinfo')}} : </span>
					<select class="form-control col-3" v-model="applicantsPost">
	  				    	<option v-for='cv in cvs' :value="cv">{{cv.position}}</option>
	    				</select>
				</div>
			</div>
			<div v-else> 
				<div class="row"><span class="position-title col-3"> {{$t('propositioninfo')}} : </span>
					<select class="form-control col-3" v-model="applicantsPost">
	  				    	<option v-for='annonce in annonces' :value="annonce">{{annonce.position}}</option>
	    				</select>
				</div>
			</div>
		</div>
		<label for="exampleFormControlTextarea1" class="position-title">{{$t(!allreadySent()?'leaveamessage':'yourmessage')}} : </label>
		<div class="form-group row">
			<textarea v-if="!allreadySent()" class="form-control col-6 ml-3" id="exampleFormControlTextarea1" rows="3" col="5" :placeholder="$t('leaveamessagehere')" v-model="answer.message"></textarea>
			<div v-else> {{yourrequest().message}}</div>
		</div>
		<br/>
		<br/>
		<div class="form-group row">
		    <div class="col-sm-10">
		      <button v-if="!allreadySent()" type="submit" class="btn btn-primary mt-3" @click.stop="submit"> {{user.role==='JS'?$t('apply'):$t('send')}} </button>
		      <router-link class="btn btn-link" to="/search">{{$t('toSearch')}}</router-link>
			  <router-link class="btn btn-link" to="/suggestions">{{$t('toSuggestions')}}</router-link>
			  <router-link class="btn btn-link" to="/answers">{{$t('toAnswers')}}</router-link>
			  <router-link class="btn btn-link" :to="{ path: '/chat', query: { post }}">{{$t('askQuestion')}}</router-link>
		    </div>
		</div>
	</div>
</template>

<script>
    import { mapState, mapActions } from 'vuex'
    export default {
	computed: mapState(['user', 'alldomains', 'alllanguages', 'allcountries', 'cvs', 'annonces', 'requests']),
	params: ['post'],
        data() {
            return {
			  post : {
				languages: [],
				experiences: [],
				educations: []
			  },
			  answer : {
				message: "",
				postId: "",
				authorId: ""
			  },
			  applicantsPost: "",
			  map: {},
			  markerSource: {},
			  hasCoordinates: false
            }
        },
        methods: {
         ...mapActions(['addRequestAction']),
		  async submit(event){
			event.preventDefault();
			this.answer.authorId = this.applicantsPost.id;
			this.addRequestAction(this.answer);
		  },
		  allreadySent(){
			return this.requests.findIndex(r=>r.postId===this.post.id)===-1?false:true;
		  },
		  yourrequest(){
		    return this.requests.find(r=>r.postId===this.post.id);
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
				 this.addMarker(this.post.xCoord, this.post.yCoord);
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
						  zoom: 7
				});
		
				this.map.setView(view);
			}
		},
		created(){
			this.post = this.$route.query.post;
			this.answer = {
				message: "",
				postId: this.post.id,
				authorId: "",
			};
			this.applicantsPostId = (this.user.role==='JS'&&this.cvs.length>0)?this.cvs[0]:this.annonces.length>0?this.annonces[0]:"";
			this.hasCoordinates = this.post.xCoord&&this.post.yCoord&&(this.post.xCoord!==null&&this.post.yCoord!==null);
		},
		mounted(){
			this.loadMap();
		}
}

</script>

<style>
