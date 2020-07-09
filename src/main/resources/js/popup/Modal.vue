<template>
	<transition name="v-popup">
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
                  {{popupBody}}
                </slot>
              </div>

              <div class="modal-footer">
                <slot name="footer">
                  <button class="modal-default-button" @click="$emit('remove')">
                    {{$t('delete')}}
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
		name: "v-popup",
		props: {popupTitle: "", popupBody: ""},
		data(){
			return {}
		},
		computed: {}
    }

</script>

<style>
</style>