package com.project.bookmenow.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import com.project.bookmenow.MainActivity
import com.project.bookmenow.R

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val timer = object  : CountDownTimer(3000,100){
            override fun onTick(millisUntilFinished: Long) {
            }

            override fun onFinish() {

               // val sharedPreference = getSharedPreferences(CREDENTIAL, Context.MODE_PRIVATE)
               // val editor = sharedPreference.edit()
               // editor.putBoolean("loginStatus", true)
               // editor.apply()

                val intent = Intent(this@SplashActivity, MainActivity::class.java)
                startActivity(intent)
            }
        }
        timer.start()
    }
}