<template>
	<div>
	<div v-if="cvs.length>1||annonces.length>1" class="mx-auto shadow-sm p-3 mt-2 mb-5 bg-white rounded" style="width: 70%;">
     	<div v-if="user.role==='JS'"> 
			<div class="row"><span class="position-title col-3"> {{$t('suggestioncvinfo')}} : </span>
				<select class="form-control col-3" @change="searchByCV" v-model="applicantsPost">
  				    	<option v-for='cv in cvs' :value="cv">{{cv.position}}</option>
    				</select>
			</div>
		</div>
		<div v-else>
			<div class="row"><span class="position-title col-3"> {{$t('suggestionannonceinfo')}} : </span>
				<select class="form-control col-3" @change="searchByCV" v-model="applicantsPost">
  				    	<option v-for='annonce in annonces' :value="annonce">{{annonce.position}}</option>
    				</select>
			</div>
		</div>
    	</div>
	<div class="mx-auto mt-2" v-for="post in result" style="width: 70%;">
		<div class="shadow-lg p-4 bg-white rounded advertise" @click="details(post)">
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
	<div v-if="result.length===0&&!applicantsPost==''" style="width: 70%;" class="mx-auto shadow-lg p-4 bg-white rounded advertise mt-2">
		{{$t('nosuggestions')}}<br>
		<router-link class="btn btn-outline-info mt-2" to="/search">{{$t('search'+user.role)}}</router-link>
	</div>
	</div>
	</div>
</template>
<script>
    import { mapState, mapMutations } from 'vuex'
    import { addHandler } from 'util/ws'
    export default {
        computed: mapState(['user', 'cvs', 'annonces']),
	data() {
            return {
		applicantsPost: '',
		result: []
            }
        },
	methods: {
		async searchByCV(){
			var response = "";
			if(this.user.role === 'JS'){
				response = await this.$http.post("searches/ai/cv",this.applicantsPost);
			}
			else if(this.user.role === 'HH'){
				response = await this.$http.post("searches/ai/ad",this.applicantsPost);
			}
			this.result = await response.json();
		},
		details(post){
			this.$router.push({ path: '/annonceAnswer', query: { post }})
		}
	  },
	  created(){

		if(this.user.role==='JS'&&this.cvs.length===1){
			this.applicantsPost = this.cvs[0];
			this.searchByCV();
		}
		if(this.user.role==='HH'&&this.annonces.length===1){
			this.applicantsPost = this.annonces[0];
			this.searchByCV();
		}
	  }
    }
</script>