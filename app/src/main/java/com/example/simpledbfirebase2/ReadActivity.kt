package com.example.simpledbfirebase2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.annotation.NonNull
import com.google.firebase.database.*

class ReadActivity : AppCompatActivity() {

    private lateinit var listViewData: ListView
    private lateinit var listData: ArrayList<String>
    private lateinit var listUser: ArrayList<User>
    private lateinit var adapter: ArrayAdapter<String>
    private lateinit var myDB: DatabaseReference
    private val userKey = "User"

    private fun init() {
        listViewData = findViewById(R.id.listViewData)
        listData = ArrayList()
        listUser = ArrayList()
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, listData)
        listViewData.adapter = adapter
        myDB = FirebaseDatabase.getInstance().getReference(userKey)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_read)
        init()
        getDataFromDB()
        setOnItemClick()
    }

    private fun getDataFromDB(){

        myDB.addValueEventListener(object: ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {
                if (listData.size > 0) listData.clear()
                if (listUser.size > 0) listUser.clear()
                for (ds: DataSnapshot in snapshot.children) {
                    listData.add(ds.getValue(User::class.java)!!.name)
                    listUser.add(ds.getValue(User::class.java)!!)
                }
                adapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
            }

        })


    }

    private fun setOnItemClick(){
        listViewData.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->
                var user = listUser[position]
                intent = Intent(applicationContext, DetailActivity::class.java)
                intent.putExtra(NAME, user.name)
                intent.putExtra(INFO, user.info)
                startActivity(intent)
            }
    }

}