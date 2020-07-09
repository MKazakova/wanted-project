<template>
	<div>
		<div v-if="answers&&answers.length!=0" class="mx-auto text-center" style="width: 70%;">
			<h2>{{$t('youranswers'+user.role)}}</h2>
		</div>
		<div v-else class="mx-auto text-center" style="width: 70%;">
			<h2>{{$t('noanswers')}}</h2>
		</div>
		<div v-if="posts.length>1" class="mx-auto p-3 mt-2 mb-5" style="width: 70%;">
				<div class="row"><span class="position-title col-3"> {{$t('propositioninfo'+user.role)}} : </span>
					<select class="form-control col-3" v-model="applicantsPost" @change="searchByCV" >
	  				    	<option v-for='post in posts' :value="post" :key="post.id">{{post.position}}</option>
	    			</select>
				</div>
	    </div>
		<div v-for="post in filteredanswers" :key="post.id" class="mx-auto shadow-sm p-3 mt-2 mb-5 bg-white rounded advertise"   style="width: 70%;">
			<h3 class="company-title float-right">{{post.author?post.author.companyName:'-'}}</h3>
			<h5 :class="post.status==='SENT'?'text-info float-left':post.status==='ACCEPTED'?'text-success float-left':'text-danger float-left'">{{$t(post.status)}}</h5>
			<h2 class="position-title text-center">{{post.author?post.author.position:'-'}}</h2>
			<div>{{post.description}}</div>
			<div><span class="position-title">{{$t('competences')}} : </span>
				{{post.author?post.author.competences:'-'}}
			</div>
			<div v-if="post.workDomain"><span class="position-title">{{$t('workDomain')}} : </span>
				{{post.author?post.author.workDomain.name:'-'}}
			</div>
			<div><span class="position-title">{{$t('country')}} : </span>
				{{post.author?post.author.country:"-"}}
			</div>
			<div><span class="position-title">{{$t('city')}} : </span>
				{{post.author?post.author.city:"-"}}
			</div>
			<div v-if="user.role==='JS'&&post.author"><span class="position-title">{{$t('experiencelevel')}} : </span>
				{{post.author.monthsOfExperience>12?post.author.monthsOfExperience/12 :post.author.monthsOfExperience}} {{post.author.monthsOfExperience>12?$t('years'):$t('months')}} 
			</div>
			<div v-else><span class="position-title">{{$t('experience')}} : </span>
				<div v-if="!post.experiences||post.experiences.length===0">{{$t('noexperience')}}</div>
				<div v-for="exp in post.experiences" class="ml-5" :key="exp.id">{{exp.startDate}} {{exp.endDate}} {{exp.position}} {{$t('at')}} {{exp.companyName}}</div>
			</div>
			<div v-if="user.role==='JS'"><span class="position-title">{{$t('educationlevel')}} : </span>
				BAC+{{post.bacPlusLevel}}
			</div>
			<div v-else>
				<span class="position-title">{{$t('education')}} : </span>
				<div v-if="!post.educations||post.educations.length===0">{{$t('noeducation')}}</div>
				<div v-for="educ in post.educations"  :key="educ.id" class="ml-5">{{educ.finishDate}} : {{educ.title}}(BAC+{{educ.bacPlusLevel}}) {{$t('at')}} {{educ.establishment}} </div>
			</div>
			<div v-if="post.showSalary"><span class="position-title">{{$t('salary')}} : </span>
				{{post.minSalary}} - {{post.maxSalary}} 
			</div>
			<div v-if="post.languages&&post.languages.length!=0"><span class="position-title">{{$t('languages')}} : </span>
				<ul>
					<li v-for="lang in post.languages" :key="lang.id">{{lang.language}} {{lang.level}}
					</li>
				</ul>
			</div>
			<div v-if="post.typeContrat"><span class="position-title">{{$t('typeContrat')}} : </span>
				{{post.typeContrat}} 
			</div>
			<div class="row">
				<button v-if="!isDeciding[post.id]" @click="takedecision(post)" type="button" class="btn btn-outline-primary m-1">{{$t('takedecision')}}</button>
				<select v-else @change="changeStatus(post)" class="form-control col-3" id="level" v-model="post.status">
		  			    <option value="SENT">{{$t('recSENT')}}</option>
		  			    <option value="ACCEPTED">{{$t('ACCEPTED')}}</option>
		  			    <option value="REFUSED">{{$t('REFUSED')}}</option>
		    	</select>
		    	<button @click="details(post.author)" type="button" class="btn btn-outline-primary m-1">{{$t('details')}}</button>
		    	<router-link class="btn btn-outline-primary m-1" :to="{ path: '/chat/'+post.post.id, query: { post : post.author }}">{{$t('showConversation')}}</router-link></td>
	    	</div>
		</div>
	</div>
</template>

<script>
    import { mapState, mapActions } from 'vuex'

    export default {
	computed: mapState(['answers', 'user', 'cvs', 'annonces']),
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
			  filteredanswers: [],
			  posts: [],
			  isDeciding: []
            }
        },
        methods: {
         ...mapActions(['changeAnswerAction']),        
	        searchByCV(){
	        	this.filteredanswers = this.answers.filter(p=>this.applicantsPost.id===p.post.id);
	        },
			details(post){
				this.$router.push({ path: '/annonceAnswer', query: { post }})
			},
			takedecision(post){
				console.log("take decision");
				this.isDeciding[post.id]=true;
				console.log(this.isDeciding);
				this.$forceUpdate();
			},
			async changeStatus(post){
				this.changeAnswerAction(post);
				this.isDeciding[post.id] = false;
			}
	},
	 created(){
		this.posts = this.user.role==='JS'?this.cvs:this.annonces;
		this.posts.map(p=>{
			this.isDeciding[p.id]=false;
		});
		this.filteredanswers = this.answers;
	}
}

</script>

<style>
