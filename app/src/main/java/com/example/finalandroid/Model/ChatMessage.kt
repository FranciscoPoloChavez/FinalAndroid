package com.example.finalandroid.Model

data class ChatMessage(val chat: String? = "", val user: String? = "") {
    val content: CharSequence? = null
    val isImage: Boolean = true
    val isMine: Boolean = true


    //val isMine: Boolean = false
}
