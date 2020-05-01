package com.example.finalandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id=item.itemId

        if(id==R.id.aboutus)
        {
            Toast.makeText(this,"Not available: About Us", Toast.LENGTH_LONG).show()
        }

        if(id==R.id.contactus)
        {
            Toast.makeText(this,"Not available: Contact Us", Toast.LENGTH_LONG).show()
        }

        return super.onOptionsItemSelected(item)
    }
}
