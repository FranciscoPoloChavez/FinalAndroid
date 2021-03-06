package com.example.finalandroid.Model

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.finalandroid.R
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions

class ChatAdapter(options: FirebaseRecyclerOptions<ChatMessage>, val user: String?)
    : FirebaseRecyclerAdapter<ChatMessage, ChatViewHolder>(options) {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ChatViewHolder {
        return ChatViewHolder(LayoutInflater.from(p0.context).inflate(R.layout.item_chat, p0, false))
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int, model: ChatMessage) {
        if (model.user == "user") {
            holder.userText.text = model.chat
            holder.userText.visibility = View.VISIBLE
            holder.botText.visibility = View.GONE
            Log.v("testing", "${model.chat}")
        } else {
            holder.botText.text = model.chat
            holder.botText.visibility = View.VISIBLE
            holder.userText.visibility = View.GONE
        }
    }

}