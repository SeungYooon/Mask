package com.example.rxkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.preference.PreferenceManager

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val sps = PreferenceManager.getDefaultSharedPreferences(this)
        val addShortcout = sps.getBoolean("key_add_shortcut", false)
        val screeOn = sps.getBoolean("key_switch_on", false)
        val editName = sps.getString("key_edit_name", "")
    }
}
