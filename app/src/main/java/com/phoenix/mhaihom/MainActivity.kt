package com.phoenix.mhaihom

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast

import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat


class MainActivity : AppCompatActivity() {
    private lateinit var toolbar: Toolbar
    private lateinit var textview : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById(R.id.toolbar) as Toolbar?
//        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setSupportActionBar(toolbar)
        savedInstanceState ?: supportFragmentManager.beginTransaction()
            .replace(R.id.container, MainFragment())
            .commit()
    }


}




