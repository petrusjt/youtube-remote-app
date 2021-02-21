package com.ignissen.youtube_remote

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import java.util.regex.Pattern

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.requestWindowFeature(Window.FEATURE_NO_TITLE)
        this.supportActionBar?.hide()
        setContentView(R.layout.activity_main)


    }

    fun openControllerActivity(view: View) {
        val IPAddress = findViewById<EditText>(R.id.txt_IPAddress).text.toString().replace("\\s".toRegex(), "")
        if (Pattern.matches("^[0-9]{1,3}.[0-9]{1,3}.[0-9]{1,3}.[0-9]{1,3}$", IPAddress))
        {
            val intent = Intent(this, ControllerActivity::class.java).apply {
                putExtra("IP_ADDRESS", IPAddress)
            }
            startActivity(intent)
        }
        else
        {
            Toast.makeText(this, "Please enter a correct IP address!", Toast.LENGTH_SHORT).show()
        }

    }
}