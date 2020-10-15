package com.gilbram.whatsapp.listerner

interface ChatClickListener {
    fun onChatClicked(
        chatId: String?,
        otherUserId: String?,
        chatsImageUrl:String?,
        chatsName:String?)
}