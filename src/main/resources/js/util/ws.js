import SockJS from 'sockjs-client'
import { Stomp } from '@stomp/stompjs'


var stompClient = null
const handlers = []
var id = null

export function connect(chatId) {
	id = chatId
    const socket = new SockJS('/gs-guide-websocket')
    stompClient = Stomp.over(socket)
    stompClient.connect({}, frame => {
        stompClient.subscribe('/topic/activity/'+id, message => {
            handlers.forEach(handler => handler(JSON.parse(message.body)))
        })
    })
}

export function addHandler(handler) {
    handlers.push(handler)
}

export function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect()
    }
}

export function sendMessage(chat) {
    stompClient.send("/app/changeMessage/"+id, {}, JSON.stringify(chat))
}