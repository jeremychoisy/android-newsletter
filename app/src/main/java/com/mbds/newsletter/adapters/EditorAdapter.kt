package com.mbds.newsletter.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mbds.newsletter.R
import com.mbds.newsletter.data.models.Editor


class EditorAdapter(private val dataSet: MutableList<Editor>, private val callback: EditorCallback) :
        RecyclerView.Adapter<EditorAdapter.ViewHolder>() {
    inner class ViewHolder(private val root: View) : RecyclerView.ViewHolder(root) {

        fun bind(item: Editor) {
            val name = root.findViewById<TextView>(R.id.source_name)
            val language = root.findViewById<TextView>(R.id.source_language)
            val txtDesc = root.findViewById<TextView>(R.id.source_description)
            name.text = item.name
            txtDesc.text = item.description
            language.text = "Pays : " + convertLanguage(item.language)

            root.setOnClickListener {
                if(SelectedFilter.listPositionEditor.contains(adapterPosition)){
                    SelectedFilter.listPositionEditor.remove(adapterPosition)
                    root.setBackgroundColor(Color.TRANSPARENT)
                    SelectedFilter.list.remove(item.id)
                }
                else{
                    SelectedFilter.listPositionEditor.add(adapterPosition)
                    SelectedFilter.list.add(item.id)
                    root.setBackgroundColor(Color.LTGRAY)
                }
                print(" tab " + SelectedFilter.list + "\n")
                print(" tabPos " + SelectedFilter.listPositionEditor + "\n")
                callback.onClick(item.id)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        //SelectedFilter.list.clear()
        val rootView = LayoutInflater.from(parent.context)
                .inflate(R.layout.list_editor, parent, false)
        return ViewHolder(rootView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataSet[position])

        holder.itemView.setBackgroundColor(Color.TRANSPARENT)
        SelectedFilter.listPositionEditor.forEach {
            if(position==it){
                println("item pos : $position selected pos : $it")
                holder.bind(dataSet[position])
                //holder.itemView.setBackgroundColor(ContextCompat.getColor(holder.itemView.context, R.color.graySelected))
                holder.itemView.setBackgroundColor(Color.LTGRAY)
            }
        }
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

    fun convertLanguage(language: String): String {
        return when(language) {
            "ar" -> "Argentine"
            "de" -> "Allemagne"
            "en" -> "Anglais"
            "es" -> "Espagne"
            "fr" -> "France"
            "it" -> "Italie"
            "nl" -> "Pays-Bas"
            "no" -> "Norvège"
            "pt" -> "Portugal"
            "ru" -> "Russe"
            "se" -> "Suède"
            else -> language
        }
    }
}

