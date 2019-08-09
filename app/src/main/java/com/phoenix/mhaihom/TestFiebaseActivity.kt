package com.phoenix.mhaihom

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*

class TestFiebaseActivity : AppCompatActivity() {

    private val TAG = "Comic"
    private lateinit var response_data: MutableList<DataModel>
    private var dataAdapter: DataAdapter? = null
    private lateinit var recyclerView: RecyclerView

    private lateinit var firebaseDatabase: FirebaseDatabase
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_fiebase)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView!!.layoutManager = LinearLayoutManager(this)
        recyclerView!!.setLayoutManager(GridLayoutManager(this, 2))
        firebaseDatabase = FirebaseDatabase.getInstance()
        databaseReference = firebaseDatabase!!.getReference("bookshelf/data")
        response_data = mutableListOf()

        dataAdapter = DataAdapter(response_data)
        recyclerView!!.setAdapter(dataAdapter)
        bindingData()
    }
    private fun bindingData() {
        databaseReference!!.addChildEventListener(object : ChildEventListener {
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onChildMoved(p0: DataSnapshot, p1: String?) {

            }

            override fun onChildChanged(p0: DataSnapshot, p1: String?) {
                Toast.makeText(applicationContext, "Change ${p0.key}",Toast.LENGTH_SHORT).show()
                val newvalue = p0.getValue(DataModel::class.java)
                val commentKey = p0.key
                Log.d("Ammy",commentKey)
                Log.d("Ammy", newvalue.toString())
//
//
//                response_data!!.remove(p0.getValue(DataModel::class.java))
            }

            override fun onChildAdded(p0: DataSnapshot, p1: String?) {
                response_data!!.add(p0.getValue(DataModel::class.java)!!)
                dataAdapter!!.notifyDataSetChanged()
            }

            override fun onChildRemoved(p0: DataSnapshot) {

            }
        })
    }
}
