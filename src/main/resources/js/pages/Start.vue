<template>
    <div>
	<img src="/images/writing.png" alt="writing" width="100%">
	<div v-if="user&&user.profile&&user.role!=='ADMIN'" class="row d-flex justify-content-center">
		<div class="col-3 shadow-lg bg-white rounded center-block bg-white m-4 module" style="height: 200px">
			<img src="/images/cvs.png" alt="cvs" width="50px" class="float-right m-2">
			<div v-if="user.role==='JS'">
				<h2 class="text-info"><em>{{$t('cvs')}}</em></h2>
				<div v-if="cvs&&cvs.length>0">
					<p>{{$t('postanswers'+user.role, {nb : answers.length})}}</p>
					<router-link class="btn btn-outline-info mt-2 bottom-left" to="/answers">{{$t('lookdetailsansw')}}</router-link>
				</div>
				<div v-else>
					<p>{{$t('nocvs')}}</p>
					<router-link class="btn btn-outline-info mt-2 bottom-left" to="/addEditCV">{{$t('addcv')}}</router-link>
				</div>
			</div>
			<div v-if="user.role==='HH'">
				<h2 class="text-info"><em>{{$t('repannonces')}}</em></h2>
				<div v-if="annonces&&annonces.length>0">
					<p>{{$t('postanswers'+user.role, {nb : answers.length})}}</p>
					<router-link class="btn btn-outline-info mt-2 bottom-left" to="/answers">{{$t('lookdetailsansw')}}</router-link>
				</div>
				<div v-else>
					<p>{{$t('noannonces')}}</p>
					<router-link class="btn btn-outline-info mt-2 bottom-left" to="/advertisements">{{$t('addannonce')}}</router-link>
				</div>
			</div>

		</div>
		<div class="col-3 shadow-lg bg-white rounded center-block bg-white m-4 module" style="height: 200px">
			<img src="/images/request.png" alt="request" width="50px" class="float-right m-2">
			<h2 class="text-info"><em>{{$t('yourrequests'+user.role)}}</em></h2>
			<div v-if="requests&&requests.length>0">
				{{$t('haverequests'+user.role, {nb : requests.length, favnb: nbRequests('ACCEPTED'), refnb: nbRequests('REFUSED')})}}
			</div>
			<div v-else>
					<p>{{$t('norequests'+user.role)}}</p>
					<router-link class="btn btn-outline-info bottom-right mt-2" to="/search">{{$t('search'+user.role)}}</router-link>
			</div>
			<router-link class="btn btn-outline-info mt-2 bottom-left" to="/requests">{{$t('lookdetails')}}</router-link>
		</div>
		<div class="col-3 shadow-lg bg-white rounded center-block bg-white m-4 module" style="height: 200px">
			<img src="/images/suggestions.png" alt="request" width="50px" class="float-right m-2">
			<h2 class="text-info"><em>{{$t('suggestions'+user.role)}}</em></h2>
			<div>
				{{$t('suggestionsinfo')}}
			</div>
			<router-link class="btn btn-outline-info mt-2 bottom-left" to="/suggestions">{{$t('showsuggestions')}}</router-link>
		</div>
	</div>
    </div>
</template>

<script>
    import { mapState, mapMutations } from 'vuex'
    import { addHandler } from 'util/ws'
    export default {
        computed: mapState(['user', 'cvs', 'annonces', 'requests', 'answers']),
	data() {
            return {
            }
        },
	methods: {
		nbRequests(status){
			return this.requests.filter(r=>r.status===status).length;
		}
	}
    }
</script>

<style>
</style>