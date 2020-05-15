package com.example.finalandroid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import java.lang.Exception
import java.util.*
import kotlin.concurrent.schedule
import kotlin.system.measureTimeMillis

class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splashscreen)

        val background = object : Thread() {
            override fun run() {
                try {
                    //Thread.sleep(measureTimeMillis {90000000000})
                    Timer("SettingUp", false).schedule(700) {
                        val intent = Intent(baseContext, MainActivity::class.java)
                        startActivity(intent)
                    }
                }catch (e: Exception){
                    e.printStackTrace()
                }
            }
        }
        background.start()
    }
}
