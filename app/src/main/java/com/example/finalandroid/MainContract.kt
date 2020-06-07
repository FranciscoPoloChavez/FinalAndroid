package com.example.finalandroid

interface MainContract {

    interface View{

    }

    interface Presenter{
        fun sendMessage(message: String)
        fun onDestroy()
    }

}