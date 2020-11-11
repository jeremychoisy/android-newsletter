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
import com.mbds.newsletter.data.models.Country
import com.mbds.newsletter.fragments.CountryFragment

class CountriesAdapter(private val dataSet: List<Country>, private val callback: CountryFragment) :
        RecyclerView.Adapter<CountriesAdapter.ViewHolder>() {

    inner class ViewHolder(private val root: View) : RecyclerView.ViewHolder(root) {
        fun bind(item: Country) {
            val country = root.findViewById<TextView>(R.id.country)
            val img = root.findViewById<AppCompatImageView>(R.id.flag)
            country.text = convertCountry(item.language)

            Glide
                    .with(root)
                    .load(item.url)
                    .centerCrop()
                    .placeholder(R.drawable.plholder)
                    .into(img);

            root.setOnClickListener {
                if(SelectedFilter.listPositionCountry.contains(adapterPosition)){
                    SelectedFilter.listPositionCountry.remove(adapterPosition)
                    root.setBackgroundColor(Color.TRANSPARENT)
                    SelectedFilter.listCategoryAndCountry.remove(item.language)
                }
                else{
                    SelectedFilter.listPositionCountry.add(adapterPosition)
                    SelectedFilter.listCategoryAndCountry.add(item.language)
                    root.setBackgroundColor(Color.GREEN)
                }
                print(" tab " + SelectedFilter.listCategoryAndCountry + "\n")
                print(" tabPos " + SelectedFilter.listPositionCountry + "\n")
                //callback.onClick(convertCountry(item.language))
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val rootView = LayoutInflater.from(parent.context)
                .inflate(R.layout.list_country, parent, false)
        return ViewHolder(rootView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataSet[position])
        holder.itemView.setBackgroundColor(Color.TRANSPARENT)
        SelectedFilter.listPositionCountry.forEach {
            if(position==it){
                println("item pos : " + position + " selected pos : " + it)
                holder.bind(dataSet[position])
                //holder.itemView.setBackgroundColor(ContextCompat.getColor(holder.itemView.context, R.color.graySelected))
                holder.itemView.setBackgroundColor(Color.GREEN)
            }
        }
    }

    override fun getItemCount(): Int = dataSet.size
}

interface CountryCallback {
    fun onClick(countryName: String)
}

fun convertCountry(countryISO: String): String {
    when(countryISO) {
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
        else -> return ""
    }
}