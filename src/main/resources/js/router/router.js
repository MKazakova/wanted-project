import Vue from 'vue'
import VueRouter from 'vue-router'
import JsAddEdit from 'pages/JsAddEdit.vue'
import HhAddEdit from 'pages/HhAddEdit.vue'
import AddEditDomain from 'pages/AddEditDomain.vue'
import adminJS from 'pages/AdminJS.vue'
import Profile from 'pages/Profile.vue'
import Domains from 'pages/Domains.vue'
import Advertisement from 'pages/Advertisement.vue'
import CV from 'pages/CV.vue'
import addEditCV from 'pages/AddEditCV.vue'
import Annonces from 'pages/Annonces.vue'
import Search from 'pages/Search.vue'
import Language from 'pages/Languages.vue'
import AddLanguage from 'pages/AddEditLangue.vue'
import Country from 'pages/Countries.vue'
import AddCountry from 'pages/AddEditCountry.vue'
import AnnonceAnswer from 'pages/AnnonceAnswer.vue'
import StartPage from 'pages/Start.vue'
import Suggestions from 'pages/Suggestions.vue'
import Answers from 'pages/Answers.vue'
import Requests from 'pages/Requests.vue'
import Chat from 'pages/Chat.vue'
import Chats from 'pages/Chats.vue'
import Users from 'pages/Users.vue'

Vue.use(VueRouter)

const routes = [
    { path: '/profile', component: Profile },
    { path: '/jobSeeker', component: JsAddEdit },
    { path: '/hhAddEdit', component: HhAddEdit },
    { path: '/jslist', component: adminJS },
    { path: '/domains', component: AddEditDomain },
    { path: '/alldomains', component: Domains },
    { path: '/cvs', component: CV },
    { path: '/addEditCV', component: addEditCV},
    { path: '/addEditCV/:id', component: addEditCV},
    { path: '/advertisements', component: Advertisement},
    { path: '/advertisements/:id', component: Advertisement},
    { path: '/annonces', component: Annonces},
    { path: '/search', component: Search},
    { path: '/languages', component: Language},
    { path: '/addlanguage', component: AddLanguage},
    { path: '/countries', component: Country},
    { path: '/addcountry', component: AddCountry},
    { path: '/annonceAnswer', component: AnnonceAnswer},
    { path: '/', component: StartPage},
    { path: '/suggestions', component: Suggestions},
    { path: '/answers', component: Answers},
    { path: '/requests', component: Requests},
    { path: '/chat', component: Chat},
    { path: '/chat/:id', component: Chat},
    { path: '/users', component: Users},
    { path: '/chats', component: Chats}
]

export default new VueRouter({
    mode: 'history',
    routes
})   