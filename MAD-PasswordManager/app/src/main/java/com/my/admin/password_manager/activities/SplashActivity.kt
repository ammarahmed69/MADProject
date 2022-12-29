package com.my.admin.password_manager.activities

import android.content.Intent
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.ads.MobileAds
import com.my.admin.password_manager.R

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val settings : SharedPreferences = getSharedPreferences("PREFS", 0)
        val passwordString = settings.getString("pinValue", "")

        if(passwordString == "") {
            val intent = Intent(this, CreatePinActivity::class.java)
            startActivity(intent)
            finish()
        } else {
            val intent = Intent(this, EnterPinActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
}
