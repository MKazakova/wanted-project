import Vue from 'vue'
import Vuex from 'vuex'
import hhApi from 'api/hhprofile'
import jsApi from 'api/jsprofile'
import domainApi from 'api/domain'
import annonceApi from 'api/annonce'
import cvApi from 'api/cv'
import languageApi from 'api/language'
import countryApi from 'api/country'
import requestApi from 'api/answer'
import chatApi from 'api/chat'

Vue.use(Vuex)

export default new Vuex.Store({
    state: {
        user: userInfo.user,
		alldomains: domains,
		chats: userInfo.chats,
		alllanguages: userInfo.languages?userInfo.languages:[],
		allcountries: userInfo.countries?userInfo.countries:[],
		requests: userInfo.requests?userInfo.requests:[],
		cvs: userInfo.cvs?userInfo.cvs:[],
		annonces: userInfo.annonces?userInfo.annonces:[],
		answers: userInfo.answers?userInfo.answers:[]

    },
    mutations: {
    	addAnnonceMutation(state, annonce) {
    		const updateIndex = state.annonces.findIndex(item => item.id === annonce.id)
           1
    		if(updateIndex!==-1){
    			state.annonces = [
    				...state.annonces.slice(0, updateIndex),
    				annonce,
    				...state.annonces.slice(updateIndex + 1)
    				]}
    		else{
    			state.annonces = [
    				...state.annonces,
    				annonce
    				]
            }
        },
    	addCvMutation(state, cv) {
        	const updateIndex = state.cvs.findIndex(item => item.id === cv.id)
            
    		if(updateIndex!==-1){
    			state.cvs = [
    				...state.cvs.slice(0, updateIndex),
    				cv,
    				...state.cvs.slice(updateIndex + 1)
    				]}
    		else{
    			state.cvs = [
    				...state.cvs,
    				cv
    			]
    		}
        },
        addDomainMutation(state, domain) {
            state.alldomains = [
                ...state.alldomains,
                domain
            ]
        },
        updateDomainMutation(state, domain) {
            const updateIndex = state.alldomains.findIndex(item => item.id === domain.id)
            
            state.alldomains = [
                ...state.alldomains.slice(0, updateIndex),
                domain,
                ...state.alldomains.slice(updateIndex + 1)
            ]
        },
		updateHeadHunterMutation(state, hh) {
        	state.user.role = 'HH';
            state.user.userProfile = hh.profile;
        },
        updateJobSeekerMutation(state, js) {
        	state.user.role = 'JS';
            state.user.userProfile = js.profile;
        },
        removeDomainMutation(state, domain) {
            const deletionIndex = state.alldomains.findIndex(item => item.id === domain.id)

            if (deletionIndex > -1) {
                state.alldomains = [
                    ...state.alldomains.slice(0, deletionIndex),
                    ...state.alldomains.slice(deletionIndex + 1)
                ]
            }
        },
        removeLanguageMutation(state, language) {
            const deletionIndex = state.alllanguages.findIndex(item => item.id === language.id)

            if (deletionIndex > -1) {
                state.alldomains = [
                    ...state.alllanguages.slice(0, deletionIndex),
                    ...state.alllanguages.slice(deletionIndex + 1)
                ]
            }
        },
        updateLanguageMutation(state, language) {
            const updateIndex = state.alllanguages.findIndex(item => item.id === language.id)
            
            state.alldomains = [
                ...state.alllanguages.slice(0, updateIndex),
                language,
                ...state.alllanguages.slice(updateIndex + 1)
            ]
        },
        addLanguageMutation(state, language) {
            state.alllanguages = [
                ...state.alllanguages,
                language
            ]
        },
        removeCountryMutation(state, country) {
            const deletionIndex = state.allcountries.findIndex(item => item.id === country.id)

            if (deletionIndex > -1) {
                state.allcountries = [
                    ...state.allcountries.slice(0, deletionIndex),
                    ...state.allcountries.slice(deletionIndex + 1)
                ]
            }
        },
        updateCountryMutation(state, country) {
            const updateIndex = state.allcountries.findIndex(item => item.id === country.id)
            
            state.allcountries = [
                ...state.allcountries.slice(0, updateIndex),
                country,
                ...state.allcountries.slice(updateIndex + 1)
            ]
        },
		changeAnswerMutation(state, answer) {
            const updateIndex = state.answers.findIndex(item => item.id === answer.id)
            
            state.answers = [
                ...state.answers.slice(0, updateIndex),
                answer,
                ...state.answers.slice(updateIndex + 1)
            ]
        },
        addCountryMutation(state, country) {
            state.allcountries = [
                ...state.allcountries,
                country
            ]
        },
        removeCvMutation(state, cv) {
            const deletionIndex = state.cvs.findIndex(item => item.id === cv.id)
            if (deletionIndex > -1) {
                state.cvs = [
                    ...state.cvs.slice(0, deletionIndex),
                    ...state.cvs.slice(deletionIndex + 1)
                ]
            }
        },
        removeAnnonceMutation(state, annonce) {
            const deletionIndex = state.annonces.findIndex(item => item.id === annonce.id)
            if (deletionIndex > -1) {
                state.annonces = [
                    ...state.annonces.slice(0, deletionIndex),
                    ...state.annonces.slice(deletionIndex + 1)
                ]
            }
        },
        addRequestMutation(state, request) {
        	state.requests = [
                ...state.requests,
                request
            ]
        },
        addChatMutation(state, chat) {
        	const updateIndex = state.chats.findIndex(item => item.id === chat.id)
            
    		if(updateIndex!==-1){
    			state.chats = [
    				...state.chats.slice(0, updateIndex),
    				chat,
    				...state.chats.slice(updateIndex + 1)
    			]
    		}
    		else{
    			state.chats = [
    				...state.chats,
    				chat
    			]
    		}
        }
    },
    actions: {
        async updateHeadHunterAction({commit}, user) {
            const result = await hhApi.update(user)
            const data = await result.json()
            commit('updateHeadHunterMutation', data)
        },
        async updateJobSeekerAction({commit}, user) {
            const result = await jsApi.update(user)
            const data = await result.json()
            commit('updateJobSeekerMutation', data)
        },
        async addDomainAction({commit}, domain) {
            const result = await domainApi.add(domain)
            const data = await result.json()
            commit('addDomainMutation', data)
        },
        async addChatAction({commit}, chat) {
            const result = await chatApi.add(chat)
            const data = await result.json()
            commit('addChatMutation', data)
			return data;
        },
        async removeDomainAction({commit}, domain) {
            const result = await domainApi.remove(domain.id)
            if (result.ok) {
            	commit('removeDomainMutation', domain)
            }
        },
        async updateDomainAction({commit}, domain) {
            const result = await domainApi.update(domain)
            const data = await result.json()
            commit('updateDomainMutation', data)
        },
        async addLanguageAction({commit}, language) {
            const result = await languageApi.add(language)
            const data = await result.json()
            commit('addLanguageMutation', data)
        },
        async removeLanguageAction({commit}, language) {
            const result = await languageApi.remove(language.id)
            if (result.ok) {
            	commit('removeLanguageMutation', language)
            }
        },
        async updateLanguageAction({commit}, language) {
            const result = await languageApi.update(language)
            const data = await result.json()
            commit('updateLanguageMutation', data)
        },
        async addCountryAction({commit}, country) {
            const result = await countryApi.add(country)
            const data = await result.json()
            commit('addCountryMutation', data)
        },
        async removeCountryAction({commit}, country) {
            const result = await countryApi.remove(country.id)
            if (result.ok) {
            	commit('removeCountryMutation', country)
            }
        },
        async updateCountryAction({commit}, country) {
            const result = await countryApi.update(country)
            const data = await result.json()
            commit('updateCountryMutation', data)
        },
        async addUserCvAction({commit}, cvs) {
            const result = await cvApi.update(cvs)
            const data = await result.json()
            commit('addCvMutation', data)
        },
        async addUserAnnonceAction({commit}, annonces) {
        	console.log("annonces "+annonces);
            const result = await annonceApi.update(annonces)
            const data = await result.json()
            commit('addAnnonceMutation', data)
        },
        async deleteCvAction({commit}, cv) {
            const result = await cvApi.remove(cv.id)
            if (result.ok) {
            	commit('removeCvMutation', cv)
            }
        },
        async deleteAnnonceAction({commit}, annonce) {
            const result = await annonceApi.remove(annonce.id)
            if (result.ok) {
            	commit('removeAnnonceMutation', annonce)
            }
        },
        async addRequestAction({commit}, request) {
            const result = await requestApi.add(request)
            const data = await result.json()
            commit('addRequestMutation', data)
        },
        async changeAnswerAction({commit}, request) {
            const result = await requestApi.update(request)
            const data = await result.json()
            commit('changeAnswerMutation', data)
        }
    }
})