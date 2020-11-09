package com.mbds.newsletter.adapters

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

class CountriesAdapter(private val dataset: List<Country>, private val callback: CountryFragment) :
        RecyclerView.Adapter<CountriesAdapter.ViewHolder>() {

    inner class ViewHolder(private val root: View) : RecyclerView.ViewHolder(root) {
        fun bind(item: Country) {
            val txtTitle = root.findViewById<TextView>(R.id.title)
            val img = root.findViewById<AppCompatImageView>(R.id.image)
            txtTitle.text = convertCountry(item.language)

            Glide.with(root)
                    .load(item.url)
                    .into(img)

            root.setOnClickListener {
                callback.onClick(item.language)
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