package com.example.simpledbfirebase2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class DetailActivity : AppCompatActivity() {

    private lateinit var textViewName: TextView
    private lateinit var textViewInfo: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        textViewName = findViewById(R.id.textViewName)
        textViewInfo = findViewById(R.id.textViewInfo)
        if (intent.hasExtra(NAME) && intent.hasExtra(INFO)) {
            textViewName.text = intent.getStringExtra(NAME)
            textViewInfo.text = intent.getStringExtra(INFO)
        }
    }
}