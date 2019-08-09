package com.phoenix.mhaihom

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*

class InformationActivity : AppCompatActivity() {

    private val TAG = "Comic"
    private var response_data: ArrayList<DataModel>?= null
    private var dataAdapter: ComicDataAdapter? = null
    private var mRecyclerView: RecyclerView? = null
    private var get_key: String? = null
    private lateinit var firebaseDatabase: FirebaseDatabase
    private lateinit var databaseReference: DatabaseReference


    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_information)
        val intent = intent
        get_key = intent.getStringExtra("keys")

        firebaseDatabase = FirebaseDatabase.getInstance()
        databaseReference = firebaseDatabase!!.getReference("bookshelf/data/$get_key/pages")

        Log.d(TAG, "bookshelf/data/$get_key/pages")

        response_data = ArrayList<DataModel>()
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        mRecyclerView = findViewById(R.id.recycleComic) as RecyclerView
        mRecyclerView!!.layoutManager = layoutManager
        dataAdapter = ComicDataAdapter(response_data!!)
        mRecyclerView!!.adapter = dataAdapter

        comicBindingData()
    }

    private fun comicBindingData() {
        databaseReference!!.addChildEventListener(object : ChildEventListener {
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onChildMoved(p0: DataSnapshot, p1: String?) {

            }

            override fun onChildChanged(p0: DataSnapshot, p1: String?) {

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
