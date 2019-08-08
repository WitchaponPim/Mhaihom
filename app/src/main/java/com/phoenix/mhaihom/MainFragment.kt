package com.phoenix.mhaihom

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.*

import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.fragment_main.*


class MainFragment: Fragment(),SnackListener {
    override fun onBtnClick(model: Snack) {
        Toast.makeText(context, "เลือก ${model.name} ใส่ตะกร้า", Toast.LENGTH_SHORT).show()
    }

    override fun onItemClick(position: Int, model: Snack) {
        Log.d(TAG,"${model.name} : ${model.price} bath")
        Toast.makeText(context, "${model.name} : ${model.price} bath", Toast.LENGTH_SHORT).show()
    }

    var TAG : String = "Adapter"


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    @SuppressLint("WrongConstant")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setAdapter(2)

    }

    private fun getDataFromJson(): ArrayList<Snack> {
        val inputStream = activity!!.assets.open("snack_mock.json")
        val size = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()

        val json = String(buffer, charset("UTF-8"))
        val listType = object : TypeToken<SnackItem>() {}.type
        val gson = Gson().fromJson<SnackItem>(json, listType)
        return gson.snacks
    }

    @SuppressLint("WrongConstant")
    fun setAdapter(count:Int){
        val memberList = getDataFromJson()
        val memberAdapter = SnackAdapter(memberList, this,count)

        recyclerView.apply {
            layoutManager = GridLayoutManager(context, count, GridLayoutManager.VERTICAL, false)
            isNestedScrollingEnabled = false
            adapter = memberAdapter
            onFlingListener = null
        }
        memberAdapter.notifyDataSetChanged()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_toolbar, menu)

        super.onCreateOptionsMenu(menu, inflater)
    }
    @SuppressLint("WrongConstant")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle presses on the action bar menu items
        when (item.itemId) {
            R.id.action_grid -> {
                setAdapter(2)
                Toast.makeText(context, "Grid", Toast.LENGTH_SHORT).show()
                return true
            }
            R.id.action_list -> {
                setAdapter(1)
                Toast.makeText(context,"List", Toast.LENGTH_SHORT).show()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }


}


