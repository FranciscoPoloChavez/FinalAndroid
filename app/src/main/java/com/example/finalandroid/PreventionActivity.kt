package com.example.finalandroid

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
import com.example.finalandroid.Adapter.ChatMessageAdapter
import com.example.finalandroid.Model.ChatMessage
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_prevention.*
import org.alicebot.ab.*
import java.io.*
import java.util.ArrayList

abstract class PreventionActivity : AppCompatActivity() {

    var listView: ListView? = null
    var alistView = listView    //opcion
    var btnSend: FloatingActionButton? = null
    var abtnSend = btnSend  //opcion
    var edtTextMsg: EditText? = null
    var aedtTextMsg = edtTextMsg    //opcion
    var imageView: ImageView? = null
    private var bot: Bot? = null
    private var adapter: ChatMessageAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_prevention)
        alistView = findViewById(R.id.listView) //Original : listView = findViewById(R.id.listView)
        abtnSend = findViewById(R.id.btnSend)    //Original: btnSend = findViewById(R.id.btnSend)
        aedtTextMsg = findViewById(R.id.editTextMsg) //Original : edtTextMsg = findViewById(R.id.editTextMsg)
        imageView = findViewById(R.id.imageView)
        adapter = ChatMessageAdapter(this, ArrayList())
        listView?.setAdapter(adapter)    //Original: listView.setAdapter(adapter)
        btnSend?.setOnClickListener(View.OnClickListener {   //Original btnSend.setOnClickListener(View.OnClickListener {
            val message = edtTextMsg?.getText().toString()       //Orginial: val message = edtTextMsg.getText().toString()
            val response =
                chat!!.multisentenceRespond(edtTextMsg?.getText().toString())       //Original: chat!!.multisentenceRespond(edtTextMsg.getText().toString())
            if (TextUtils.isEmpty(message)) {
                Toast.makeText(
                    this@PreventionActivity,
                    "Please enter a query",
                    Toast.LENGTH_SHORT
                ).show()
                return@OnClickListener
            }
            sendMessage(message)
            botsReply(response)

            //clear editttext
            edtTextMsg?.setText("")      //Original: edtTextMsg.setText("")
            listView?.setSelection(adapter!!.count - 1) //Original: listView.setSelection(adapter!!.count - 1)
        })

        //
        /*
        Dexter.vithActivity(this)
                .withPermissions(
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_EXTERNAL_STORAGE)
                .withListener(new MultiplePermissionsListener()){
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report){
                        if(report.areAllPermissionsGranted()){
                            custom();
                            Toast.makeText(PreventionActivity.this, "Permission Granted", Toast.LENGTH_SHORT).show();
                        }
                        if(report.isAnyPermissionsPermanentlyDenied()){
                            Toast.makeText(PreventionActivity.this, "Please Grant all the Permissions...", Toast.LENGTH_SHORT).show();
                        }

                    }
                    @Override
                    public void onPermissionsRationaleShouldBeShown(List<PermissionsRequest> permissions, PermissionToken token){

                    }
                }
        //
        */
        val available = isSDCartAvailable
        val assets = resources.assets
        val fileName = File(
            Environment.getExternalStorageDirectory().toString() + "/TBC/bots/TBC"
        )
        val makeFile = fileName.mkdirs()
        if (fileName.exists()) {
            //read the line
            try {
                for (dir in assets.list("TBC")!!) {
                    val subDir = File(fileName.path + "/" + dir)
                    val subDir_Check = subDir.mkdirs()
                    for (file in assets.list("TBC/$dir")!!) {
                        val newFile =
                            File(fileName.path + "/" + dir + "/" + file)
                        if (newFile.exists()) {
                            continue
                        }
                        var `in`: InputStream
                        var out: OutputStream
                        var str: String
                        `in` = assets.open("TBC/$dir/$file")
                        out = FileOutputStream(fileName.path + "/" + dir + "/" + file)

                        //copy files from assets to te mobile´s sd card or any secondary memory available
                        copyFile(`in`, out)
                        `in`.close()
                        out.flush()
                        out.close()
                    }
                }
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }

        //get the working directory
        MagicStrings.root_path =
            Environment.getExternalStorageDirectory().toString() + "/TBC"
        AIMLProcessor.extension = PCAIMLProcessorExtension()
        bot = Bot("TBC", MagicStrings.root_path, "chat")
        chat = Chat(bot)
    }

    @Throws(IOException::class)
    private fun copyFile(`in`: InputStream, out: OutputStream) {
        val buffer = ByteArray(1024)
        var read: Int
        while (`in`.read(buffer).also { read = it } != -1) {
            out.write(buffer, 0, read)
        }
    }

    private fun botsReply(response: String) {
        val chatMessage = ChatMessage(false, false, response)
        adapter!!.add(chatMessage)
    }

    private fun sendMessage(message: String) {
        val chatMessage = ChatMessage(false, true, message)
        adapter!!.add(chatMessage)
    }

    companion object {
        var chat: Chat? = null
        val isSDCartAvailable: Boolean
            get() = if (Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED) true else false
    }
}
