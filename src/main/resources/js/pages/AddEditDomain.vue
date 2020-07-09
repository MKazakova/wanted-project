<template>
	<div class="mx-auto shadow-sm p-3 mt-2 mb-5 bg-white rounded" style="width: 70%;">
	    <form>
		  <div class="form-group row">
		    <label for="domainName" class="col-sm-2 col-form-label">{{$t('domainName')}}</label>
		    <div class="col-sm-10">
		      <input type="text" class="form-control" v-model="domain.name" required>
		    </div>
		  </div>
		  <div class="form-group row">
		    <div class="col-sm-10">
		      <button type="submit" class="btn btn-primary" @click.stop="submit">{{$t('submit')}}</button>
		      <router-link class="btn btn-link" to="alldomains">{{$t('backToDomains')}}</router-link>
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
      		   domain : {
               		name: ""
		   }
            }
        },
        methods: {
         ...mapActions(['addDomainAction']),
            submit(event) {
            	event.preventDefault();

            	this.addDomainAction(this.domain);
            	this.$router.push({ path: 'alldomains' })
            }
        }
    }

</script>

<style>
</style>