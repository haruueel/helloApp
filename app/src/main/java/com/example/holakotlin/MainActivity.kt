package com.example.holakotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import org.w3c.dom.Text
import java.util.*

class MainActivity : AppCompatActivity(), TextToSpeech.OnInitListener{

    var tts: TextToSpeech? = null
    var text: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tts = TextToSpeech(this,this)
        text = findViewById<Button>(R.id.textView)

        findViewById<Button>(R.id.btnPlay).setOnClickListener{
            speech()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (tts != null) {
            tts!!.stop()
            tts!!.shutdown()
        }
    }

    fun speech(){
        var msg: String = findViewById<EditText>(R.id.field).text.toString()
        if (msg.isEmpty()) {
            text!!.setText("?")
            return
        }
        tts!!.speak(msg,TextToSpeech.QUEUE_FLUSH,null,"")
        text!!.setText(msg)
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            text!!.text = "Listo"
        tts!!.setLanguage(Locale.US)
        }
        else text!!.text = "No listo"
    }
}