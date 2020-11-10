package com.mbds.newsletter.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mbds.newsletter.R
import com.mbds.newsletter.data.models.Category

class CategoriesAdapter(private val dataset: List<Category>, private val callback: CategoryCallback) :
        RecyclerView.Adapter<CategoriesAdapter.ViewHolder>() {
    var selectedFilter: SelectedFilter = SelectedFilter()

    inner class ViewHolder(private val root: View) : RecyclerView.ViewHolder(root) {
        fun bind(item: Category) {
            val txtTitle = root.findViewById<TextView>(R.id.title)
            val img = root.findViewById<AppCompatImageView>(R.id.image)
            val txtDesc = root.findViewById<TextView>(R.id.desc)
            txtTitle.text = item.name
            txtDesc.text = item.desc
            Glide.with(root).load(item.url).into(img)

            root.setOnClickListener {
                selectedFilter.list.add(item.name)
                callback.onClick(item.name)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val rootView = LayoutInflater.from(parent.context)
                .inflate(R.layout.list_item, parent, false)
        return ViewHolder(rootView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataset[position])
    }

    override fun getItemCount(): Int = dataset.size

    interface CategoryCallback {
        fun onClick(categoryName: String)
    }
}

