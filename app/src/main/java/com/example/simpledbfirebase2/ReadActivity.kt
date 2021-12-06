package com.example.simpledbfirebase2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.annotation.NonNull
import com.google.firebase.database.*

class ReadActivity : AppCompatActivity() {

    private lateinit var listViewData: ListView
    private lateinit var listData: ArrayList<String>
    private lateinit var adapter: ArrayAdapter<String>
    private lateinit var myDB: DatabaseReference
    private val userKey = "User"

    private fun init() {
        listViewData = findViewById(R.id.listViewData)
        listData = ArrayList()
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, listData)
        listViewData.adapter = adapter
        myDB = FirebaseDatabase.getInstance().getReference(userKey)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_read)
        init()
        Toast.makeText(applicationContext, "111", Toast.LENGTH_SHORT).show()
        getDataFromDB()
        Toast.makeText(applicationContext, "222", Toast.LENGTH_SHORT).show()
    }

    private fun getDataFromDB(){

        myDB.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                Toast.makeText(applicationContext, "Clear", Toast.LENGTH_SHORT).show()
                if (listData.size > 0) listData.clear()
                for (ds: DataSnapshot in snapshot.children) {
                        listData.add(ds.getValue(User::class.java)!!.name)
                }
                adapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })


    }

}