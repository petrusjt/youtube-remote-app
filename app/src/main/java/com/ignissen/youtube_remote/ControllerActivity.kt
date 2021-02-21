package com.ignissen.youtube_remote

import android.app.VoiceInteractor
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.EditText
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException
import org.json.JSONObject


class ControllerActivity : AppCompatActivity() {

    private var BASE_URL = ""
    private var STARTVID_URL = ""
    private var ACTION_URL = ""

    private var startVidConnection = null
    private var actionConnection = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.requestWindowFeature(Window.FEATURE_NO_TITLE)
        this.supportActionBar?.hide()
        setContentView(R.layout.activity_controller)

        val IPAddress = intent.getStringExtra("IP_ADDRESS").toString()
        BASE_URL = IPAddress
        STARTVID_URL = "http://$IPAddress/youtube/startvid"
        ACTION_URL = "http://$IPAddress/youtube/player-action"

        

        Toast.makeText(this, "Using IP Address: $IPAddress", Toast.LENGTH_SHORT).show()
    }

    fun startVideo(view : View){
        val vidURL = findViewById<EditText>(R.id.txt_URL).text.toString()

        val queue = Volley.newRequestQueue(this)
        val postData = JSONObject()
        try{
            postData.put("url", vidURL)
        }catch (e : JSONException) {
            e.printStackTrace()
        }

        val jsonObjectRequest = JsonObjectRequest(Request.Method.POST, STARTVID_URL, postData, Response.Listener {
            fun onResponse(respone : JSONObject) {
                println(respone)
            }
        }, Response.ErrorListener {
            fun onErrorResponse(error: VolleyError) {
                error.printStackTrace()
            }
        })
        queue.add(jsonObjectRequest)
    }

    fun sendActionToServer(action : String){
        val queue = Volley.newRequestQueue(this)
        val postData = JSONObject()
        try{
            postData.put("action", action)
        }catch (e : JSONException) {
            e.printStackTrace()
        }

        val jsonObjectRequest = JsonObjectRequest(Request.Method.POST, ACTION_URL, postData, Response.Listener {
            fun onResponse(respone : JSONObject) {
                println(respone)
            }
        }, Response.ErrorListener {
            fun onErrorResponse(error: VolleyError) {
                error.printStackTrace()
            }
        })
        queue.add(jsonObjectRequest)
    }

    fun resumeOrPause(view: View){
        sendActionToServer("play/pause")
    }

    fun fullscreen(view: View){
        sendActionToServer("fullscreen")
    }

    fun volPlus(view: View){
        sendActionToServer("volume+")
    }

    fun volMinus(view: View){
        sendActionToServer("volume-")
    }

    fun back10(view: View){
        sendActionToServer("back10")
    }

    fun forward10(view: View){
        sendActionToServer("forward10")
    }

    fun mute(view: View){
        sendActionToServer("mute")
    }

    fun prev(view: View){
        sendActionToServer("previous")
    }

    fun next(view: View){
        sendActionToServer("next")
    }
}












