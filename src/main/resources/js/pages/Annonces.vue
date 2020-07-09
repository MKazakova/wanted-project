<template>
	<div class="mx-auto shadow-sm p-3 mt-2 mb-5 bg-white rounded" style="width: 70%;">
		<v-popup v-if="showModal" 
		:popupTitle="$t('delete')+' '+annonceToDelete.position"
		:popupBody="$t('wanttodelete')+' '+annonceToDelete.position+'?'"
		@close="showModal = false" @remove="remove(annonceToDelete)"></v-popup>
		<h3>{{$t('yourannonces')}}</h3>
		<ul v-if="annonces" class="list-group">
			<li v-for="annonce in annonces" class="list-group-item"><b>{{annonce.position}}</b>
			<router-link :to="'/advertisements/'+annonce.id" class="img-btn"><img src="/images/look.png" alt="delete" height="20"></a></router-link>
			<a href="#" @click="askToDelete(annonce)" class="img-btn"><img src="/images/delete2.png" alt="delete" height="20"></a></li>
		</ul>
		<div v-else>
			<p>{{$t('noannonces')}}</p>
		</div>
		<router-link class="btn btn-outline-info mt-2" to="/advertisements">{{$t('addannonce')}}</router-link>
	</div>
</template>

<script>
	import vPopup from '../popup/Modal'
    import { mapState, mapActions } from 'vuex'
    export default {
	    components: {vPopup},
		computed: mapState(['user', 'annonces']),
		data() {
            return {
			  	showModal: false,
			  	annonceToDelete: ""
			}
		},
	    methods: {
	         ...mapActions(['deleteAnnonceAction']),
	            askToDelete(annonce) {
	            	this.annonceToDelete = annonce;
	            	this.showModal = true;
	            },
	            remove(annonce) {
	            	this.deleteAnnonceAction(annonce);
	            	this.showModal = false;
	            }
	    }
    }

</script>

<style>
</style>