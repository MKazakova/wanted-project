<template>
    <div>
	<div class="text-center">
		<div  class="float-right">
			<a href="#" @click="setLocale('fr')"><img src="/images/fr.png" alt="french" height="15" width="20"></a>
			<a href="#" @click="setLocale('en')"><img src="/images/en.png" alt="english" height="15" width="20"></a>
			<a href="#" @click="setLocale('es')"><img src="/images/es.png" alt="spanish" height="15" width="20"></a>
		</div>
		<h3 class='title'>Wanted<img src="/images/wanted.png" alt="spanish" height="50" width="50"></h3>
	</div>
	<nav class="navbar navbar-expand-lg navbar-light bg-light">
		<router-link class="nav-link" to="/"><img src="/images/home.png" alt="home" height="30px"></router-link>
  		<a v-if="!user" class="navbar-brand" href="/login">{{$t('logGoogle')}}</a>
  		<div v-else class="collapse navbar-collapse" id="navbarNavDropdown">
    		<ul class="navbar-nav">
      			<li v-if="user.role!=='ADMIN'" class="nav-item">
					<router-link class="nav-link" to="profile">{{$t('profile')}}</router-link>
      			</li>
				<li class="nav-item">
				<router-link class="nav-link" v-if="user.role==='JS'" to="/cvs">{{$t('cv')}}</router-link>
				<router-link class="nav-link" v-if="user.role==='HH'" to="/annonces">{{$t('annonces')}}</router-link>
      			</li>
      			<li v-if="user.userProfile&&user.role!=='ADMIN'" class="nav-item">
				<router-link class="nav-link" to="/search">{{$t('search'+user.role)}}</router-link>
      			</li>
      			<li v-if="user.role!=='ADMIN'&&user.userProfile" class="nav-item">
        			<router-link class="nav-link" to="/answers">{{$t('answers')}}</router-link>
      			</li>
			<li v-if="user.role!=='ADMIN'&&user.userProfile" class="nav-item">
        			<router-link class="nav-link" to="/chats">{{$t('chats')}}</router-link>
      			</li>
      			<li v-if="user.role==='ADMIN'" class="nav-item dropdown pull-rigth">
        			<a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          			{{$t('administration')}}
        			</a>
        			<div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
          				<router-link class="dropdown-item" to="alldomains">{{$t('domains')}}</router-link>
					<router-link class="dropdown-item" to="/languages">{{$t('languages')}}</router-link>
					<router-link class="dropdown-item" to="/countries">{{$t('countries')}}</router-link>
					<router-link class="dropdown-item" to="/users">{{$t('users')}}</router-link>
        			</div>
      			</li>
    			</ul>
  		</div>
		<div  v-if="user" class="pull-right">{{user.username}}&nbsp;<a href="/logout">{{$t('logout')}}</a></div>
	</nav>
            <router-view></router-view>
    </div>
</template>

<script>
    import { mapState, mapMutations } from 'vuex'
    import { addHandler } from 'util/ws'
    export default {
        computed: mapState(['user']),
		data() {
	            return {}
	        },
		methods: {
			setLocale(locale){
			  this.$i18n.locale = locale
			}
		}
    }
</script>

<style>
</style>