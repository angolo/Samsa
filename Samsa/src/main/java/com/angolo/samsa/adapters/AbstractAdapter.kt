package com.angolo.samsa.adapters

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import inflate

abstract class AbstractAdapter<TYPE>(
    protected var itemList: List<TYPE>,
    protected val layoutId : Int) : RecyclerView.Adapter<AbstractAdapter.Holder>(){


    override fun getItemCount() = itemList.size


    override fun onCreateViewHolder(parent: ViewGroup,viewType: Int): Holder {
        val view = parent inflate layoutId
        val holder= Holder(view)
        val itemView= holder.itemView

        //qui imposto il listener per l'item
        itemView.setOnClickListener {
            val adapterPosition = holder.adapterPosition
            if (adapterPosition != RecyclerView.NO_POSITION) {
                onItemClick(itemView, adapterPosition)
            }
        }

        itemView.setOnLongClickListener{
            onLongItemClick(itemView,holder.adapterPosition)
            return@setOnLongClickListener true
        }

        return holder
    }

    //default onBindeViewHolder
    override fun onBindViewHolder(holder: Holder, position: Int) {
        val item = itemList[position]
        holder.itemView.bind(item)
    }

    //attualmente inutile
    protected open fun View.bind(item: TYPE) {}


    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView)

    protected open fun onItemClick(itemView: View, position: Int) {

    }

    protected open fun onLongItemClick(itemView: View, position: Int){

    }


}