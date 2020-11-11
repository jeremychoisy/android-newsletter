package com.mbds.newsletter.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView
import androidx.recyclerview.widget.RecyclerView
import com.mbds.newsletter.R
import com.mbds.newsletter.data.models.Editor


class EditorAdapter(private val dataSet: MutableList<Editor>, private val callback: EditorCallback) :
        RecyclerView.Adapter<EditorAdapter.ViewHolder>() {
        private val selectedPosition = mutableListOf<Int>()
    inner class ViewHolder(private val root: View) : RecyclerView.ViewHolder(root) {

        fun bind(item: Editor) {
            val name = root.findViewById<TextView>(R.id.source_name)
            val language = root.findViewById<TextView>(R.id.source_language)
            val txtDesc = root.findViewById<TextView>(R.id.source_description)
            //val img = root.findViewById<AppCompatImageView>(R.id.image)
            name.text = item.name
            txtDesc.text = item.description
            language.text = convertLanguage(item.language)

            root.setOnClickListener {
                SelectedFilter.list.add(item.name)
                if(selectedPosition.contains(adapterPosition)){
                    selectedPosition.remove(adapterPosition)
                    root.setBackgroundColor(Color.TRANSPARENT)
                }
                else{
                    selectedPosition.add(adapterPosition)
                }

                root.setBackgroundColor(Color.GREEN)
//                root.findViewById<Button>(R.id.button_search).text = updateFiltreButton()
                print(" tab " + SelectedFilter.list + "\n")
                callback.onClick(item.name)
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

        println(position)
        println(selectedPosition)
//        holder.itemView.setOnClickListener {
//            print("\nselected position avant " + selectedPosition + "\n")
//            selectedPosition.add(holder.itemView)
//            notifyDataSetChanged()
//            print("\nselected position " + selectedPosition + "\n")
//        }
        holder.itemView.setBackgroundColor(Color.TRANSPARENT)
        selectedPosition.forEach {
            if(position==it){
                println("item pos : " + position + " selected pos : " + it)
                holder.bind(dataSet[position])
                holder.itemView.setBackgroundColor(Color.GREEN)
            }
//            it.setBackgroundColor(Color.GREEN)
        }
//        holder.itemView.setBackgroundColor(Color.TRANSPARENT)


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
        when(language) {
            "ar" -> return "Argentine"
            "de" -> return "Allemagne"
            "en" -> return "Anglais"
            "es" -> return "Espagne"
            "fr" -> return "France"
            "it" -> return "Italie"
            "nl" -> return "Pays-Bas"
            "no" -> return "Norvège"
            "pt" -> return "Portugal"
            "ru" -> return "Russe"
            "se" -> return "Suède"
            else -> return language
        }
    }

    fun updateFiltreButton(): String {
        var text = "Filtre : "
//        SelectedFilter.list.forEach {
//            text += it
//        }
        text += "\n LANCER LA RECHERCHE"
        return text
    }
}

