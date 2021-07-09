package com.example.myfaith.view.list

import android.content.Context
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Toast
import android.widget.TextView
import android.widget.ImageView
import android.widget.Filterable
import com.example.myfaith.R
import com.example.myfaith.model.ChurchModel

class ListRecyclerViewAdapter(
        private val context: Context,
        private val values: List<ChurchModel.Church>,
        private var valuesFiltered: List<ChurchModel.Church> = values,
        private val onItemClicked: (Int) -> Unit)
    : RecyclerView.Adapter<ListRecyclerViewAdapter.ViewHolder>() {

    fun updateValues(newValues: List<ChurchModel.Church>) {
        valuesFiltered = newValues
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.church_list_item, parent, false)
        return ViewHolder(view).apply {
            itemView.setOnClickListener {
                onItemClicked(valuesFiltered[adapterPosition].id)
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = valuesFiltered[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = valuesFiltered.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val icon: ImageView = view.findViewById(R.id.item_church_icon)
        private  val name: TextView = view.findViewById(R.id.item_church_name)
        private val adress: TextView = view.findViewById(R.id.item_church_adress)

        fun bind(item: ChurchModel.Church) {
            name.text = item.name
            adress.text = item.adress
            with(itemView) {
                val drawableId = resources
                        .getIdentifier(item.icon, "drawable", context.packageName)
                icon.setImageDrawable(resources.getDrawable(drawableId, context.theme))
            }
        }
    }
}