package com.example.simpledbfirebase2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {

    private lateinit var editTextName: EditText
    private lateinit var editTextInfo: EditText
    private lateinit var myDB: DatabaseReference
    private val userKey = "User"
    private lateinit var id: String
    private var name = ""
    private var info = ""
    private lateinit var newUser: User

    private fun init() {
        editTextName = findViewById(R.id.editTextName)
        editTextInfo = findViewById(R.id.editTextInfo)
        myDB = FirebaseDatabase.getInstance().getReference(userKey)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    fun onClickWrite(view: android.view.View) {

        id = myDB.key.toString()
        name = editTextName.text.toString()
        info = editTextInfo.text.toString()
        newUser = User(id,name, info)
        if (name != "" && info != "") {
            myDB.push().setValue(newUser)
            Toast.makeText(this, "Written", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Empty field", Toast.LENGTH_SHORT).show()
        }
    }
    fun onClickRead(view: android.view.View) {}
}