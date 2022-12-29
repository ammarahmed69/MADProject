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
class ChangePinActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_pin)

        val settings : SharedPreferences = getSharedPreferences("PREFS", 0)
        val passwordValue = settings.getString("pinValue", "")
        val oldPinView = findViewById<Pinview>(R.id.oldPinView)
        val newPinView = findViewById<Pinview>(R.id.newPinView)

        oldPinView.setPinViewEventListener { _, _ ->

            val view : View = this.currentFocus
            val inputMethodManager : InputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view.windowToken,0)
            if (oldPinView.value == passwordValue) {
                newPinView.setPinViewEventListener { _, _ ->
                    if (newPinView.value != "") {
                        val editor: SharedPreferences.Editor = settings.edit()
                        editor.putString("pinValue", newPinView.value)
                        editor.apply()
                        Toast.makeText(this, "PIN password have been changed", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                    } else Toast.makeText(this, "Enter new PIN password", Toast.LENGTH_SHORT).show()
                }
            } else Toast.makeText(this, "Incorrect PIN password", Toast.LENGTH_SHORT).show()
        }

    }
}
