<template>
	<div class="mx-auto shadow-sm p-3 mt-2 mb-5 bg-white rounded" style="width: 70%;">
	    <form>
		  <div class="form-group row">
		    <label for="languageName" class="col-sm-2 col-form-label">{{$t('languageName')}}</label>
		    <div class="col-sm-10">
		      <input type="text" class="form-control" v-model="language.name" required>
		    </div>
		  </div>
		  <div class="form-group row">
		    <div class="col-sm-10">
		      <button type="submit" class="btn btn-primary" @click.stop="submit">{{$t('submit')}}</button>
		      <router-link class="btn btn-link" to="/languages">{{$t('backToLanguages')}}</router-link>
		    </div>
		  </div>
		</form>
	</div>
</template>

<script>
    import { mapState, mapActions } from 'vuex'
    export default {
        data() {
            return {
      		   language : {
               		name: ""
		   }
            }
        },
        methods: {
         ...mapActions(['addLanguageAction']),
            submit(event) {
            	event.preventDefault();

            	this.addLanguageAction(this.language);
            	this.$router.push({ path: 'languages' })
            }
        }
    }

</script>

<style>
</style>