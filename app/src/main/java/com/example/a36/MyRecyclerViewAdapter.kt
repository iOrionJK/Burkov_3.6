package com.example.a36

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.TextView
import androidx.activity.viewModels
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.imageview.ShapeableImageView
import java.io.IOError
import java.io.IOException
import android.graphics.drawable.ColorDrawable




class MyRecyclerViewAdapter(private val colorList : ArrayList<ColorBlock>, private val listener: OnItemClickListener) : RecyclerView.Adapter<MyRecyclerViewAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = colorList[position]
        holder.id.setBackgroundColor(currentItem.id)
        holder.color.setText(currentItem.name)
    }

    override fun getItemCount(): Int {

        return colorList.size
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener{
        val id : ConstraintLayout = itemView.findViewById(R.id.item)
        val color : TextView = itemView.findViewById(R.id.colorText)

        init{
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            listener.onItemClick((id.background as ColorDrawable).color)
        }
    }

    interface OnItemClickListener{
        fun onItemClick(position: Int)
    }

}
