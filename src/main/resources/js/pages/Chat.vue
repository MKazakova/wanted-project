<template>
	<div class="mx-auto shadow-sm p-3 mt-2 mb-5 bg-white rounded" style="width: 50%;">
		
		<div v-if="!chosenPostId">
			{{$t('choseyourpost'+user.role)}}
			<select class="form-control" id="country" v-model="chosenPostId" @change="searchChat">
  			    <option v-for="post in posts" :value="post.id">{{post.position}}</option>
    		</select>
		</div>
		<div v-else>
			<h5 class="text-info">{{$t('chatbetween', { first: firstName, second: secondName })}}</h5>
		    <div v-if="chat.id && connected" v-for="message in chat.messages" class="container row">
		       	<div class="col-12">
			    	<div :class="message.authorId===chosenPostId?'card border-info mb-3 float-right':'card text-white bg-info mb-3'" style="max-width: 25rem;">
					  <div :class="message.authorId===chosenPostId?'card-body text-info':'card-body'">
					  	<p>{{message.text}}</p>
					    <em>{{message.authorId===chosenPostId?firstName:secondName}}</em><br/>
					    {{message.creationDate}}
					  </div>
					</div>
				</div>
		    </div>
		    <div v-if="!connected">
				<button type="button" class="btn btn-info" @click="createConnection">{{$t('startChat')}}</button>
			</div>
			<div v-if="chat.id&&connected" class="form-group row">
					<textarea class="form-control col-6 ml-3" id="exampleFormControlTextarea1" rows="3" col="5" :placeholder="$t('leaveamessagehere')" v-model="currentMessage">
					</textarea>
					<button type="submit" class="btn btn-primary" @click.stop="save">{{$t('submit')}}</button>
			</div>
			<button v-if="connected" type="button" class="btn btn-info" @click="closeChat">{{$t('closeChat')}}</button>
		</div>
	</div>
</template>

<script>
	    import { mapState, mapActions } from 'vuex'
	    import { sendMessage, connect, disconnect } from 'util/ws'
	    import { addHandler } from 'util/ws'
	    export default {
		computed: mapState(['messages', 'user', 'chats', 'cvs', 'annonces']),
		params: ['post'],
        data() {
            return {
               chosenPostId: null,
      		   chat : "",
      		   currentMessage : "",
      		   object: {},
      		   posts: [],
      		   isConnected: false,
      		   firstName: "",
      		   secondName: "",
      		   connected: false
            }
        },
        methods: {
        	...mapActions(['addChatAction']),
           async save() {
               	await sendMessage({chatId: this.chat.id, text: this.currentMessage, authorId: this.chosenPostId})
		this.currentMessage = ""
		
           },
           async createConnection() {
	             var chat = {};
	        	 if (this.user.role==='JS') {
		            	    	chat = {jsId: this.chosenPostId,
		            	    			hhId: this.post.id};
		         }
		         else {
		            	    	chat = {hhId: this.chosenPostId,
		            	    			jsId: this.post.id};
		         }
		       	 this.chat = await this.addChatAction(chat);
		         connect(this.chat.id)
            	 	 addHandler(data => {
	                	this.chat.messages.push(data)
	             	})
	             	this.connected = true;
            },
            giveName(user, role){
            	return role==='JS'?user.username:user.companyName;
            },
            closeChat(){
            	disconnect()
            	this.connected = false;
            },
            searchChat(){
            	this.createConnection();
            }
        },
        created(){
        	 this.post = this.$route.query.post;
        	 this.chosenPostId = this.$route.params.id?parseInt(this.$route.params.id):null;
        	 this.firstName = this.giveName(this.user.role==='JS'?this.user:this.user.userProfile, this.user.role);
        	 this.secondName = this.giveName(this.post.author, this.user.role==='JS'?'HH':'JS');
        	 
        	 this.posts = this.user.role==='JS'?this.cvs:this.annonces;
        	 if(this.user.role==='JS'&&this.cvs.length===1){
        	 	this.chosenPostId = this.cvs[0].id;
        	 }
        	 else if(this.user.role==='HH'&&this.annonces.length===1){
        	 	this.chosenPostId = this.annonces[0].id;
        	 }
        	 if(this.chosenPostId!==null){
        	 	this.createConnection();
        	 } 
         }
    }

</script>