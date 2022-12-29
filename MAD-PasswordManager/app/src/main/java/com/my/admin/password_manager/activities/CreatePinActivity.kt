package com.my.admin.password_manager.activities

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.goodiebag.pinview.Pinview
import com.my.admin.password_manager.R

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class CreatePinActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_pin)
        val createPinView = findViewById<Pinview>(R.id.createPinView)
        createPinView.setPinViewEventListener { _, _ ->
            val view : View = this.currentFocus
            val inputMethodManager : InputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view.windowToken,0)
            val settings : SharedPreferences = getSharedPreferences("PREFS", 0)
            val editor : SharedPreferences.Editor = settings.edit()
            editor.putString("pinValue", createPinView.value)
            editor.apply()
            Toast.makeText(this, "Pin saving...", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()

        }

    }
}
