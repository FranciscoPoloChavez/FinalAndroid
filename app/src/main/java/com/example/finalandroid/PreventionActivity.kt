package com.example.finalandroid

import ai.api.AIConfiguration.SupportedLanguages.English
import ai.api.android.AIConfiguration
import ai.api.AIConfiguration.SupportedLanguages
import ai.api.AIConfiguration.SupportedLanguages.Spanish
import ai.api.android.AIDataService
import ai.api.android.AIService
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.text.TextUtils
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.ListView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.finalandroid.Adapter.ChatMessageAdapter
import com.example.finalandroid.Model.ChatAdapter
import com.example.finalandroid.Model.ChatMessage
import com.example.finalandroid.Model.MainPresenter
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_main.*
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import kotlinx.android.synthetic.main.activity_gallery.*
import kotlinx.android.synthetic.main.activity_gallery.view.*
import kotlinx.android.synthetic.main.activity_prevention.*
import kotlinx.android.synthetic.main.activity_us.view.*
import org.alicebot.ab.*
import java.io.*
import java.util.ArrayList

class PreventionActivity : AppCompatActivity(), MainContract.View {

    lateinit var adapter: ChatAdapter
    lateinit var ref: DatabaseReference
    lateinit var aiService: AIService
    lateinit var aiDataAIService: AIDataService
    var user: String? = null

    lateinit var mPresenter : MainContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_prevention)
        initPresenter()

        //rvChat.setHasFixedSize(true)

        //intento
        //rvChat.recyclerview.setHasFixedSize(true)
        //rvChat.recyclerview.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        //intento

        //val layoutManager = LinearLayoutManager(this)
        //layoutManager.stackFromEnd = true
        //rvChat.layoutManager = layoutManager

        //intento
        //val recy = findViewById<View>(R.id.rvChat) as RecyclerView
//guia
        /*
        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.layoutManager = LinearLayoutManager(this ,LinearLayoutManager.VERTICAL ,false)
        binding.recyclerView.adapter = customAdapter(this ,getList())
         */
//guia
        //nuestro

        ref.keepSynced(true)

        btnSend.setOnClickListener {
            val message = edChat.text.toString()
            if (message != "") {
                mPresenter.sendMessage(message)
            } else {
                aiService.startListening()
                Toast.makeText(applicationContext, "Enter message first", Toast.LENGTH_SHORT).show()
            }
            edChat.setText("")
        }

        val options = FirebaseRecyclerOptions.Builder<ChatMessage>()
            .setQuery(ref.child("chat"), ChatMessage::class.java)
            .build()

        adapter = ChatAdapter(options,user)

        //adapter = ChatAdapter(options)

        //rvChat.recyclerview.adapter = adapter

        //rvChat.adapter = adapter

        adapter.registerAdapterDataObserver(object : RecyclerView.AdapterDataObserver() {
            override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
                super.onItemRangeInserted(positionStart, itemCount)

                val msgCount = adapter.itemCount

                //Intento de cambio nosotros
                //val lastVisiblePosition = recyclerview.findViewHolderForLayoutPosition(5)

                //Original
                //val lastVisiblePosition = layoutManager.findLastCompletelyVisibleItemPosition()

                //if (lastVisiblePosition == -1 || positionStart >= msgCount - 1 && lastVisiblePosition == positionStart - 1) {
                  //  rvChat.scrollToPosition(positionStart)
                //}
            }
        })

    }

    override fun onStart() {
        super.onStart()
        adapter.startListening()
    }

    override fun onStop() {
        super.onStop()
        adapter.stopListening()
        mPresenter.onDestroy()
    }

    fun initPresenter(){
        val aiConfiguration = AIConfiguration("391f47e5cf914bd5bbe36a167634fb28", Spanish, AIConfiguration.RecognitionEngine.System)
            //AIConfiguration.SupportedLanguages.English,
            //AIConfiguration.RecognitionEngine.System)

        aiService = AIService.getService(this, aiConfiguration)
        aiDataAIService = AIDataService(this,aiConfiguration)

        ref = FirebaseDatabase.getInstance().reference

        mPresenter = MainPresenter(aiDataAIService, ref)

    }
}