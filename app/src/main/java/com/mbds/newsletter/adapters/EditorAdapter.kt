package com.mbds.newsletter.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mbds.newsletter.R
import com.mbds.newsletter.data.models.Article
import com.mbds.newsletter.data.models.Editor
import com.mbds.newsletter.fragments.EditorsFragment

class EditorAdapter(private val dataSet: MutableList<Editor>, private val callback: EditorCallback) :
        RecyclerView.Adapter<EditorAdapter.ViewHolder>() {

    inner class ViewHolder(private val root: View) : RecyclerView.ViewHolder(root) {
        fun bind(item: Editor) {
            val name = root.findViewById<TextView>(R.id.source_name)
            val language = root.findViewById<TextView>(R.id.source_language)
            val txtDesc = root.findViewById<TextView>(R.id.source_description)
            val img = root.findViewById<AppCompatImageView>(R.id.image)
            name.text = item.name
            txtDesc.text = item.description
            language.text = item.language

            root.setOnClickListener {
                callback.onClick(item.name)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val rootView = LayoutInflater.from(parent.context)
                .inflate(R.layout.list_editor, parent, false)
        return ViewHolder(rootView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataSet[position])
    }

    override fun getItemCount(): Int = dataSet.size

    fun setEditors(editors: List<Editor>) {
        this.dataSet.apply{
            clear()
            addAll(editors)
        }
        notifyDataSetChanged()
    }

    interface EditorCallback {
        fun onClick(categoryName: String)
    }


}

