<template>
	<transition name="edit-popup">
        <div class="modal-mask">
          <div class="modal-wrapper">
            <div class="modal-container">

              <div class="modal-header">
                <slot name="header">
                  <h3>{{popupTitle}}</h3>
                </slot>
              </div>

              <div class="modal-body">
                <slot name="body">
                  <div class="form-group row">
				    <label for="username" class="col-sm-2 col-form-label"></label>
				    <div class="col-sm-10">
				      {{user.username}}
				    </div>
				  </div>
				  <div class="form-group row">
				    <label for="email" class="col-sm-2 col-form-label">{{$t('email')}}</label>
				    <div class="col-sm-10">
				      <input type="email" class="form-control" v-model="user.email" required>
				    </div>
				  </div>
                  <div class="form-group row">
				    <label for="country" class="col-sm-2 col-form-label">{{$t('role')}}</label>
				    <div class="col-sm-10">
				      <select class="form-control" id="country" v-model="user.role">
	  			    	<option value='JS'>{{$t('JS')}}</option>
	  			    	<option value='HH'>{{$t('HH')}}</option>
	  			    	<option value='ADMIN'>{{$t('ADMIN')}}</option>
	    			  </select>
				    </div>
				   </div>
                </slot>
              </div>

              <div class="modal-footer">
                <slot name="footer">
                  <button class="modal-default-button" @click="$emit('edit', user)">
                    {{$t('submit')}}
                  </button>
                  <button class="modal-default-button" @click="$emit('close')">
                    {{$t('close')}}
                  </button>
                </slot>
              </div>
            </div>
          </div>
        </div>
      </transition>
</template>

<script>
    import { mapState, mapActions } from 'vuex'
    export default {
		name: "edit-popup",
		props: {popupTitle: "", user: ""},
		data(){
			return {}
		},
		computed: {}
    }

</script>

<style>
</style>