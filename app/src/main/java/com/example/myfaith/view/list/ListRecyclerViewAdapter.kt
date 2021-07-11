package com.example.myfaith.view.list

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.ImageView
import android.widget.CheckBox
import com.example.myfaith.R
import com.example.myfaith.model.ChurchModel


class ListRecyclerViewAdapter(
        values: List<ChurchModel.ChurchListElement>,
        private val onItemClicked: (Int) -> Unit,
        private val onFavBtnCheckedChange: (Int, Boolean) -> Unit)
    : RecyclerView.Adapter<ListRecyclerViewAdapter.ViewHolder>() {

    private var valuesFiltered = values.toMutableList()

    fun updateValues(newValues: MutableList<ChurchModel.ChurchListElement>) {
        valuesFiltered = newValues
        notifyDataSetChanged()
    }

    fun removeAtPosition(position: Int) {
        valuesFiltered.removeAt(position)
        notifyItemRemoved(position)
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
        holder.bind(item, onFavBtnCheckedChange)
    }

    override fun getItemCount(): Int = valuesFiltered.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val icon: ImageView = view.findViewById(R.id.item_church_icon)
        private  val name: TextView = view.findViewById(R.id.item_church_name)
        private val adress: TextView = view.findViewById(R.id.item_church_adress)
        private val favBtn: CheckBox = view.findViewById(R.id.item_fav_btn)

        fun bind(
                item: ChurchModel.ChurchListElement,
                onFavBtnCheckedChange: (Int, Boolean) -> Unit
        ) {
            name.text = item.name
            adress.text = item.adress
            with(itemView) {
                val drawableId = resources
                        .getIdentifier(item.icon, "drawable", context.packageName)
                icon.setImageDrawable(resources.getDrawable(drawableId, context.theme))
            }
            
            favBtn.setOnCheckedChangeListener(null)
            favBtn.isChecked = item.isFavorite
            favBtn.setOnCheckedChangeListener { _, isChecked ->
                onFavBtnCheckedChange(item.id, isChecked)
            }
        }
    }
}