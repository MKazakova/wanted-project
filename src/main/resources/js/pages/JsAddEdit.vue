<template>
	<div class="mx-auto shadow-sm p-3 mt-2 mb-5 bg-white rounded" style="width: 70%;">
	    <form>
		  <div class="form-group row">
		    <label for="firstName" class="col-sm-2 col-form-label" >{{$t('prenom')}}</label>
		    <div class="col-sm-10">
		      <input type="text" class="form-control" v-model="profile.firstName" placeholder="Votre prénom" required>
		      <label v-if="!validePrenom" class="text-danger">{{$t('emptyfielderror')}}</label>
		    </div>
		  </div>
		  <div class="form-group row">
		    <label for="lastName" class="col-sm-2 col-form-label">{{$t('nom')}}</label>
		    <div class="col-sm-10">
		      <input type="text" class="form-control" v-model="profile.lastName" placeholder="Votre nom" required>
		      <label v-if="!valideNom" class="text-danger">{{$t('emptyfielderror')}}</label>
		    </div>
		  </div>
		  <div class="form-group row">
		    <label for="city" class="col-sm-2 col-form-label">{{$t('ville')}}</label>
		    <div class="col-sm-10">
		      <input type="text" class="form-control" v-model="profile.city" placeholder="Votre ville">
		    </div>
		  </div>
		  <div class="form-group row">
		    <label for="inputEmail" class="col-sm-2 col-form-label" required>{{$t('email')}}</label>
		    <div class="col-sm-10">
		      <input type="email" class="form-control" @change.stop="isEmailValide(profile.email)" v-model="profile.email" placeholder="Email" required>
		      <label v-if="!valideEmail" class="text-danger">{{$t('bademailformat')}}</label>
		    </div>
		  </div>
		  <div class="form-group row">
		    <label for="inputEmail" class="col-sm-2 col-form-label" required>{{$t('telephone')}}</label>
		    <div class="col-sm-10">
		      <input type="text" class="form-control" @change.stop="isTelephoneValide(profile.telephone)" v-model="profile.telephone" placeholder="Telephone">
		      <label v-if="!valideNumero" class="text-danger">{{$t('badphoneformat')}}</label>
		    </div>
		  </div>
		  <fieldset class="form-group">
		    <div class="row">
		      <legend class="col-form-label col-sm-2 pt-0">{{$t('youare')}}</legend>
		      <div class="col-sm-10">
		        <div class="form-check">
		          <input class="form-check-input" type="radio" v-model="profile.gender" id="male" value="Male">
		          <label class="form-check-label" for="male">
		            {{$t('male')}}
		          </label>
		        </div>
		        <div class="form-check">
		          <input class="form-check-input" type="radio" v-model="profile.gender" id="female" value="Female">
		          <label class="form-check-label" for="female">
		            {{$t('female')}}
		          </label>
		        </div>
		      </div>
		    </div>
		  </fieldset>
		  <select class="browser-default custom-select" v-model="profile.domain">
			  <option v-for="domain in alldomains" :value="domain.id" :key="domain.id" :selected="domain.id == profile.domain">{{domain.name}}</option>
		  </select>
		  <div class="form-group row">
		    <div class="col-sm-10">
		      <button type="submit" class="btn btn-primary" @click="submit">{{$t('submit')}}</button>
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
               profile : userInfo.user.profile?userInfo.user.profile:{
               		firstName: "",
			lastName: "",
			email: "",
			telephone: "",
			city: "",
			gender: "",
			domain: 2
               },
               valideNumero: true,
               valideNom: true,
               validePrenom: true,
               valideEmail: true
            }
        },
        methods: {
            ...mapActions(['updateJobSeekerAction']),
            async submit(event) {
            	event.preventDefault();
            	
            	if(this.isValid(this.profile)) {
	            	this.user.profile = this.profile;
	            	await this.updateJobSeekerAction(this.user);
	            	this.$router.push({ path: '/'})
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
				if(profile.firstName.length<1){
					this.validePrenom=false;
				}
				if(profile.lastName.length<1){
					this.valideNom=false;
				}
				if(this.validePrenom&&this.valideNom&&this.isEmailValide(profile.email)&&this.isTelephoneValide(profile.telephone)){
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