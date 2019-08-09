package com.phoenix.mhaihom

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class ComicDataAdapter (val dataModelList: List<DataModel>) : RecyclerView.Adapter<DataViewHolder>(){

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): DataViewHolder {
        return DataViewHolder(LayoutInflater.from(p0.context).inflate(R.layout.pages,p0,false))
    }

    override fun onBindViewHolder(p0: DataViewHolder, p1: Int) {
        val dataModel = dataModelList[p1]

        Picasso.get().load(dataModel.photo)
            .error(R.mipmap.ic_launcher)
            .placeholder(R.mipmap.ic_launcher)
            .into(p0.imageView)
    }

    override fun getItemCount(): Int {
        return dataModelList.size
    }
}

class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    var imageView: ImageView
    init {
        imageView = itemView.findViewById(R.id.imageView)
    }

}