<template>
	<div>
	<v-popup v-if="showModal" 
	:popupTitle="$t('delete')+' '+cvToDelete.position"
	:popupBody="$t('wanttodelete')+' '+cvToDelete.position+'?'"
	@close="showModal = false" @remove="remove(cvToDelete)"></v-popup>
	<div class="mx-auto shadow-sm p-3 mt-2 mb-5 bg-white rounded" style="width: 70%;">
		<h3>{{$t('yourcvs')}}</h3>
		<ul v-if="cvs" class="list-group">
			<li v-for="cv in cvs" class="list-group-item"><b>{{cv.position}}</b>
			<router-link :to="'addEditCV/'+cv.id" class="img-btn"><img src="/images/look.png" alt="delete" height="20"></a></router-link>
			<a href="#" @click="askDelete(cv)" class="img-btn"><img src="/images/delete2.png" alt="delete" height="20"></a></li>
		</ul>
		<div v-else>
			<p>{{$t('nocvs')}}</p>
		</div>
		<router-link class="btn btn-outline-info mt-2" to="addEditCV">{{$t('addcv')}}</router-link>
	</div>
	</div>
</template>

<script>
    import vPopup from '../popup/Modal'
    import { mapState, mapActions } from 'vuex'
    export default {
    	components: {vPopup},
		computed: mapState(['user', 'cvs', 'requests', 'answers']),
		data() {
            return {
			  	showModal: false,
			  	cvToDelete: ""
			  	}
			},
       	 methods: {
         ...mapActions(['deleteCvAction']),
            askDelete(cv) {
            	this.cvToDelete = cv;
            	this.showModal = true;
            },
            remove(cv) {
            	this.deleteCvAction(cv);
            	this.showModal = false;
            }
        }
    }

</script>

<style>
</style>