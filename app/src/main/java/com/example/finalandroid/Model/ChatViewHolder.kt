package com.example.finalandroid.Model

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.finalandroid.R

class ChatViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    var userText : TextView = itemView.findViewById(R.id.userText) as TextView
    var botText  :TextView = itemView.findViewById(R.id.botText) as TextView

}
