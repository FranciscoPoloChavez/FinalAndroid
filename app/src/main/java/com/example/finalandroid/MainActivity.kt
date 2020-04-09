package com.example.finalandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import android.content.Intent as Intent1

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        botoninformacion.setOnClickListener{
            val intent = Intent1(this,InformationActivity::class.java)
            startActivity(intent)
        }

        botonmapa.setOnClickListener{
            val intent = Intent1(this,MapaActivity::class.java)
            startActivity(intent)
        }

        botongallery.setOnClickListener{
            val intent = Intent1(this,GalleryActivity::class.java)
            startActivity(intent)
        }

        botonprevention.setOnClickListener{
            val intent = Intent1(this,PreventionActivity::class.java)
            startActivity(intent)
        }
    }
}
