package com.example.finalandroid.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import android.util.Log
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.example.finalandroid.Model.ChatMessage
import com.example.finalandroid.R

class ChatMessageAdapter(
    context: Context,
    data: List<ChatMessage?>?
) : ArrayAdapter<ChatMessage?>(context, R.layout.user_query_layout, data!!) {
    override fun getItemViewType(position: Int): Int {
        val item = getItem(position)
        return if (item!!.isMine && !!item.isImage) {
            MY_MESSAGE
        } else {
            BOT_MESSAGE
        }
        return super.getItemViewType(position)
    }

    override fun getView(
        position: Int,
        convertView: View?,
        parent: ViewGroup
    ): View {
        var convertView = convertView
        val viewType = getItemViewType(position)
        if (viewType == MY_MESSAGE) {
            convertView =
                LayoutInflater.from(context).inflate(R.layout.user_query_layout, parent, false)
            val textView = convertView.findViewById<TextView>(R.id.text)
            textView.text = getItem(position)!!.content
        } else if (viewType == BOT_MESSAGE) {
            convertView =
                LayoutInflater.from(context).inflate(R.layout.user_query_layout, parent, false)
            val textView = convertView.findViewById<TextView>(R.id.text)
            textView.text = getItem(position)!!.content
        }
        convertView!!.findViewById<View>(R.id.chatMessageView)
            .setOnClickListener { Toast.makeText(context, "Clicked...", Toast.LENGTH_SHORT).show() }
        return convertView
    }

    override fun getViewTypeCount(): Int {
        return 2
    }

    companion object {
        private const val MY_MESSAGE = 0
        private const val BOT_MESSAGE = 1
    }
}