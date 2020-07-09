<template>
	<div class="mx-auto shadow-sm p-3 mt-2 mb-5 bg-white rounded" style="width: 70%;">
	    <form>
		  <div class="form-group row">
		    <label for="inputCompanyName" class="col-sm-2 col-form-label">{{$t('companyName')}}</label>
		    <div class="col-sm-10">
		      <input type="text" class="form-control" v-model="profile.companyName" placeholder="Le nom de compagnie" required>
		      <label v-if="!validCompanyName" class="text-danger">{{$t('emptyfielderror')}}</label>
		    </div>
		  </div>
		  <div class="form-group row">
		    <label for="city" class="col-sm-2 col-form-label">{{$t('city')}}</label>
		    <div class="col-sm-10">
		      <input type="text" class="form-control" v-model="profile.city" placeholder="Votre ville">
		    </div>
		  </div>
		  <div class="form-group row">
		    <label for="inputPassword3" class="col-sm-2 col-form-label">{{$t('address')}}</label>
		    <div class="col-sm-10">
		      <input type="text" class="form-control" v-model="profile.adresse" id="inputPassword3" placeholder="Adresse">
		    </div>
		  </div>
		  <div class="form-group row">
		    <label for="inputEmail" class="col-sm-2 col-form-label" required>Email</label>
		    <div class="col-sm-10">
		      <input type="email" class="form-control" @change.stop="isEmailValide(profile.email)" v-model="profile.email" placeholder="Email">
		      <label v-if="!valideEmail" class="text-danger">{{$t('bademailformat')}}</label>
		    </div>
		  </div>
		  <div class="form-group row">
		    <label for="inputEmail" class="col-sm-2 col-form-label" required>{{$t('telephone')}}</label>
		    <div class="col-sm-10">
		      <input type="text" class="form-control"  @change.stop="isTelephoneValide(profile.telephone)" v-model="profile.telephone" placeholder="Telephone">
		      <label v-if="!valideNumero" class="text-danger">{{$t('badphoneformat')}}</label>
		    </div>
		  </div>
		  <select class="browser-default custom-select" v-model="profile.domain">
			  <option disabled selected>Open this select menu</option>
			  <option v-for="domain in alldomains" :value="domain.id" :key="domain.id" :selected="domain.id == profile.domain">{{domain.name}}</option>
		  </select>
		  <div class="form-group row">
		    <div class="col-sm-10">
		      <button type="submit" class="btn btn-primary" @click.stop="submit">{{$t('submit')}}</button>
		    </div>
		  </div>
		</form>
	</div>
</template>

<script>
    import { mapState, mapMutations, mapActions } from 'vuex'
    import { addHandler } from 'util/ws'
    export default {
        computed: mapState(['user', 'alldomains']),
        data() {
            return {
      	       exist : !(userInfo.user.profile==null),
               profile : userInfo.user.profile?userInfo.user.profile:{
               		companyName: "",
               		adresse: "",
			email: userInfo.user.email,
			telephone: "",
			city: "",
			domain: 2
               },
               validCompanyName: true,
               validePrenom: true,
               valideEmail: true,
               valideNumero: true
            }
        },	
        methods: {
         ...mapActions(['updateHeadHunterAction']),
           async submit(event) {
            	event.preventDefault();
            	if(this.isValid(this.profile)) {
	            	this.user.profile = this.profile;
	            	await this.updateHeadHunterAction(this.user);
	            	this.$router.push({ path: '/'});
            	}
            },
            isTelephoneValide(telephone) {
	            if(telephone.length===0){
	            	this.valideNumero = true;
	            	return true;
	            }
				var phoneRe = /^[+]*[(]{0,1}[0-9]{1,3}[)]{0,1}[-\s\./0-9]*$/g;
				if(phoneRe.test(telephone)){
					this.valideNumero = true;
				}
				else {
					this.valideNumero = false;
				}
				
				return this.valideNumero;
			},
            isEmailValide(email) {
	            if(email.length===0){
	            	this.valideEmail = true;
	            	return true;
	            }
				var emailRe = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
				if(emailRe.test(email)){
					this.valideEmail = true;
				}
				else {
					this.valideEmail = false;
				}
				
				return this.valideEmail;
			},
			isValid(profile){
				if(profile.companyName.length<1){
					this.validCompanyName=false;
				}
				if(this.validCompanyName&&this.isEmailValide(profile.email)&&this.isTelephoneValide(profile.telephone)){
					return true;
				}
				else{
					return false;
				}
			}
        }
    }

</script>

<style>
</style>