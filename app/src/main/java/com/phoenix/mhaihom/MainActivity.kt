package com.phoenix.mhaihom

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

import androidx.appcompat.widget.Toolbar


class MainActivity : AppCompatActivity() {
    private lateinit var toolbar: Toolbar
    private lateinit var textview : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        savedInstanceState ?: supportFragmentManager.beginTransaction()
            .replace(R.id.container, MainFragment())
            .commit()
    }



}

