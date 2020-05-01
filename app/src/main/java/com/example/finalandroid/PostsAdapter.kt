package com.example.finalandroid

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.appcompat.view.menu.MenuView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.row_post.view.*


class PostsAdapter(val posts: ArrayList<String>) : RecyclerView.Adapter<PostsAdapter.PostsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostsViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.row_post, parent, false)
        return PostsViewHolder(view)
    }

    override fun getItemCount() = posts.size

    override fun onBindViewHolder(holder: PostsViewHolder, position: Int) {
        holder.username.text = posts[position]
    }
    
    class PostsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val username: TextView = itemView.username
    }
}