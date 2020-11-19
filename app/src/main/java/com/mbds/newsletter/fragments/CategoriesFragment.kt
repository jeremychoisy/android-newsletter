package com.mbds.newsletter.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mbds.newsletter.MainActivity
import com.mbds.newsletter.R
import com.mbds.newsletter.adapters.CategoriesAdapter
import com.mbds.newsletter.data.models.Category

/**
 * A simple [Fragment] subclass.
 */
class CategoriesFragment : Fragment(), CategoriesAdapter.CategoryCallback {
    lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_categories, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.recycler_view)
        val categories = listOf<Category>(
            Category("Sport", "Most exhausting sport topics from all around the world", "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcTqHRYCG74D6qQ2IT6TUWpt9Mrn3iBgfS0nww&usqp=CAU"),
            Category("Economic", "Wealthier economic topics from all around the world", "https://images.niooz.fr/safe_image.php?clean=1&width=600&i=/cache/mediaid/3/3/7/0/4/3370420.jpg"),
            Category("Politic", "Leading politic topics from all around the world", "https://lumieresdelaville.net/wp-content/uploads/2017/11/7392285_d60a92a8-c940-11e7-b36b-53a09aedfc0c-1_1000x625.jpg"),
            Category("Education", "Smartest education topics from all around the world", "https://www.weka.fr/actualite/wp-content/uploads/2020/10/le-ministere-de-l-education-nationale-lance-les-territoires-numeriques-educatifs-640x312.jpg"),
            Category("Pandemic", "Sickest pandemic topics from all around the world", "https://resize1.prod.docfr.doc-media.fr/rcrop/450,340,center-middle/img/var/doctissimo/storage/images/fr/www/sante/news/coronavirus-covid19-pourquoi-plus-infectieux-que-les-autres-2003/8418826-1-fre-FR/coronavirus-covid19-pourquoi-plus-infectieux-que-les-autres-2003.jpg"),
            Category("Sciences", "Mind blowing science topics from all around the world", "https://www.ican-institute.org/wp-content/uploads/2019/04/dna-3539309-pixabay.jpg"),
            Category("Ecology", "Greenest ecology topics from all around the world", "https://www.science.edu/acellus/wp-content/uploads/2017/01/earth-405096_1920.jpg")
        )
        val adapterRecycler = CategoriesAdapter(categories, this)
        recyclerView.layoutManager = LinearLayoutManager(view.context)
        recyclerView.adapter = adapterRecycler
    }

    override fun onClick(categoryName: String) {
        TODO("Not yet implemented")
    }


}