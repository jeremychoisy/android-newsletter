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

    private var row_index = -1

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
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val rootView = LayoutInflater.from(parent.context)
                .inflate(R.layout.list_country, parent, false)
        return ViewHolder(rootView)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataSet[position])
        println(SelectedFilter.listCountry)
        if(SelectedFilter.listPositionCountry.size > 0){
            row_index = SelectedFilter.listPositionCountry[0]
        }

        holder.itemView.setOnClickListener(View.OnClickListener {
            row_index = position
            SelectedFilter.listPositionCountry.clear()
            SelectedFilter.listCountry.clear()
            notifyDataSetChanged()
        })
        if (row_index == position && SelectedFilter.listPositionCountry.size == 0) {
            holder.itemView.setBackgroundColor(Color.LTGRAY)
            SelectedFilter.listPositionCountry.add(position)
            SelectedFilter.listCountry.add(dataSet[position].language)
        }
        else if(row_index == position){
            holder.itemView.setBackgroundColor(Color.LTGRAY)
        }
        else {
            holder.itemView.setBackgroundColor(Color.TRANSPARENT)
        }
    }

    override fun getItemCount(): Int = dataSet.size
}

interface CountryCallback {
    fun onClick(countryName: String)
}

fun convertCountry(countryISO: String): String {
    return when(countryISO) {
        "ar" -> "Arabe"
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
        else -> ""
    }
}