package com.example.simpledbfirebase2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class DetailActivity : AppCompatActivity() {

    private lateinit var textViewName: TextView
    private lateinit var textViewInfo: TextView
    private lateinit var spinnerDis: Spinner
    private lateinit var myDB: DatabaseReference
    private val userKey = "User"
    private val listOfDis = ArrayList<String>()


    fun init() {
        textViewName = findViewById(R.id.textViewName)
        textViewInfo = findViewById(R.id.textViewInfo)
        spinnerDis = findViewById(R.id.spinnerDis)
        myDB = FirebaseDatabase.getInstance().getReference(userKey)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        init()
        if (intent.hasExtra(NAME) && intent.hasExtra(INFO) && intent.hasExtra(LIST_OF_DIS)) {
            textViewName.text = intent.getStringExtra(NAME)
            textViewInfo.text = intent.getStringExtra(INFO)
            if (listOfDis.size > 0) listOfDis.clear()
            for (i: String in intent.getStringArrayListExtra(LIST_OF_DIS)!!){
                listOfDis.add(i)
            }
        }
        spinnerDis.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, listOfDis)
    }
}