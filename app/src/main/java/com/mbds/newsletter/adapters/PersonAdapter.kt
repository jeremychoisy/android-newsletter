package com.mbds.newsletter.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.mbds.newsletter.R
import com.bumptech.glide.Glide
import com.mbds.newsletter.data.models.Country
import com.mbds.newsletter.data.models.Person
import com.mbds.newsletter.fragments.AboutUsFragment

class PersonAdapter(private val dataSet: List<Person>, private val callback: AboutUsFragment) :
    RecyclerView.Adapter<PersonAdapter.ViewHolder>() {

    inner class ViewHolder(private val root: View) : RecyclerView.ViewHolder(root) {
        fun bind(item: Person) {
            val name = root.findViewById<TextView>(R.id.person_name)
            val desc = root.findViewById<TextView>(R.id.person_desc)
            val linkedInUrl = root.findViewById<TextView>(R.id.person_linkedIn_url)
            val personImg = root.findViewById<AppCompatImageView>(R.id.person_avatar)

            name.text = item.name;
            desc.text = item.description
            linkedInUrl.text = item.url_linkdIn

            Glide
                .with(root)
                .load(item.url_img)
                .centerCrop()
                .placeholder(R.drawable.plholder)
                .into(personImg);

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonAdapter.ViewHolder {
        val rootView = LayoutInflater.from(parent.context)
            .inflate(R.layout.about_us_item, parent, false)
        return ViewHolder(rootView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataSet[position])
    }

    override fun getItemCount(): Int = dataSet.size

}