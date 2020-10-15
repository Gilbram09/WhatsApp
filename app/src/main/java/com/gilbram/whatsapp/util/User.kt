package com.gilbram.whatsapp.util


data class User(
    val email: String? = "",
    val phone: String? = "",
    val name: String? = "",
    val imageUrl: String? = "",
    val status: String? = "",
    val statusUrl: String? = "",
    val statusTime: String? = ""
)

data class Contact(
    val nama: String?,
    val phone: String?

)

data class Chat(
    val chatParticipants: ArrayList<String>
)

data class Message(
    val sendBy: String? = "",
    val message: String = "",
    val messageTime: Long? = 0
)


