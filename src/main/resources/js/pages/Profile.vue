<template>
	<div>
        <div v-if="user && !user.profile">
		<h5>Vous n'avez pas completé votre profile.
		Vous êtes :</h5>
            	<div class="btn-group btn-group-toggle" data-toggle="buttons">
                	<label class="btn btn-secondary active">
                    		<input type="radio" id="option1" autocomplete="off" @click="getChoice('JS')">{{$t('candidat')}}
                	</label>
                	<label class="btn btn-secondary">
                    		<input type="radio" id="option2" @click="getChoice('HH')">{{$t('recruteur')}}
                	</label>
            	</div>
        </div></div>
</template>

<script>
    import { mapState, mapMutations } from 'vuex'
    export default {
        computed: mapState(['user']),
        data() {
            return {
                choice: ''
            }
        },
        methods: {
            getChoice(el) {
		
                if(el==='JS'){
					this.$router.push({ path: 'jobSeeker' })
				}
				else{
					this.$router.push({ path: 'hhAddEdit' })
				}
            }
        },
		created() {
			if(this.user && this.user.userProfile){
				if(this.user.role==='JS'){
					this.$router.push({ path: 'jobSeeker' })
				}
				else if(this.user.role==='HH') {
					this.$router.push({ path: 'hhAddEdit' })
				}
				
			}
		}
    }
</script>
