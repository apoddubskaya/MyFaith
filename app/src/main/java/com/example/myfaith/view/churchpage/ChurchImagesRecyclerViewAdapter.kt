package com.example.myfaith.view.churchpage

import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myfaith.R

class ChurchImagesRecyclerViewAdapter(private val names: List<String>)
    : RecyclerView.Adapter<ChurchImagesRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.church_activity_viewpager_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = names.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(names[position])
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val image: ImageView = view.findViewById(R.id.church_activity_viewpager_imageview)
        fun bind(imageName: String) {
            with(itemView) {
                val drawableId = resources.getIdentifier(
                        imageName,
                        "drawable",
                        context.packageName
                )
                image.setImageDrawable(resources.getDrawable(drawableId, context.theme))
            }
        }
    }
}