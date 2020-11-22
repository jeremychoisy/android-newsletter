package com.mbds.newsletter.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mbds.newsletter.R
import com.mbds.newsletter.data.models.Category
import com.mbds.newsletter.interfaces.CategoryCallback

class CategoriesAdapter(private val dataSet: List<Category>, private val callback: CategoryCallback) :
        RecyclerView.Adapter<CategoriesAdapter.ViewHolder>() {


    inner class ViewHolder(private val root: View) : RecyclerView.ViewHolder(root) {
        fun bind(item: Category) {
            val txtTitle = root.findViewById<TextView>(R.id.title)
            val img = root.findViewById<AppCompatImageView>(R.id.image)
            val txtDesc = root.findViewById<TextView>(R.id.desc)
            txtTitle.text = item.name
            txtDesc.text = item.desc

            Glide
                    .with(root)
                    .load(item.url)
                    .centerCrop()
                    .placeholder(R.drawable.plholder)
                    .into(img);

            root.setOnClickListener {
                if(SelectedFilter.listPositionCategory.contains(adapterPosition)){
                    SelectedFilter.listPositionCategory.remove(adapterPosition)
                    root.setBackgroundColor(Color.TRANSPARENT)
                    SelectedFilter.listCategory.remove(item.name)
                }
                else{
                    SelectedFilter.listPositionCategory.add(adapterPosition)
                    SelectedFilter.listCategory.add(item.name)
                    root.setBackgroundColor(Color.LTGRAY)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val rootView = LayoutInflater.from(parent.context)
                .inflate(R.layout.list_item, parent, false)
        return ViewHolder(rootView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataSet[position])
        holder.itemView.setBackgroundColor(Color.TRANSPARENT)
        SelectedFilter.listPositionCategory.forEach {
            if(position==it){
                holder.bind(dataSet[position])
                holder.itemView.setBackgroundColor(Color.LTGRAY)
            }
        }
    }

    override fun getItemCount(): Int = dataSet.size

}

