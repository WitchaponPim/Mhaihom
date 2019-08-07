package com.phoenix.mhaihom

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.snack_item.view.*

class SnackAdapter(private val items: ArrayList<Snack>,
                    private val listener: SnackListener): RecyclerView.Adapter<SnackAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.snack_item, parent, false))
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
        holder.itemView.setOnClickListener { listener.onItemClick(position,items[position]) }
    }

    class ViewHolder(itemsView: View): RecyclerView.ViewHolder(itemsView) {
        fun bind(member: Snack) {
            itemView.apply {
                textMemberNickName.text = member.price.toString()+ " ฿"
                textMemberName.text = member.name
            }
            Picasso.get().load(member.imgUrl).into(itemView.imgMember)
        }
    }
}