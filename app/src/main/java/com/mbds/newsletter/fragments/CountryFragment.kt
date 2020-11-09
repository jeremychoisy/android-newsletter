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
import com.mbds.newsletter.adapters.CountriesAdapter
import com.mbds.newsletter.data.models.Country

/**
 * A simple [Fragment] subclass.
 */
class CountryFragment : Fragment(), CategoriesAdapter.CategoryCallback {
    lateinit var recyclerView: RecyclerView

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_country, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.recycler_view)
        val countries = listOf<Country>(
                Country("ar","https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcTqHRYCG74D6qQ2IT6TUWpt9Mrn3iBgfS0nww&usqp=CAU"),
                Country("de","https://i.kym-cdn.com/entries/icons/facebook/000/029/959/Screen_Shot_2019-06-05_at_1.26.32_PM.jpg"),
                Country("en","https://upload.wikimedia.org/wikipedia/commons/0/04/Barack_Obama_Mic_Drop_2016.jpg"),
                Country("es","https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcRYpjMsQJvcXdh-nsUZ4J7KqVPY5165xHgycQ&usqp=CAU"),
                Country("fr","https://www.cetim.ch/wp-content/uploads/Pages-2-et-3-1.png"),
                Country("he","https://cdn.futura-sciences.com/buildsv6/images/largeoriginal/6/f/c/6fc6bc1b21_50021087_albert-einstein-langue.jpg"),
                Country("it","https://www.science.edu/acellus/wp-content/uploads/2017/01/earth-405096_1920.jpg"),
                Country("nl","https://i.kym-cdn.com/entries/icons/facebook/000/029/959/Screen_Shot_2019-06-05_at_1.26.32_PM.jpg"),
                Country("no","https://upload.wikimedia.org/wikipedia/commons/0/04/Barack_Obama_Mic_Drop_2016.jpg"),
                Country("pt","https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcRYpjMsQJvcXdh-nsUZ4J7KqVPY5165xHgycQ&usqp=CAU"),
                Country("ru","https://www.cetim.ch/wp-content/uploads/Pages-2-et-3-1.png"),
                Country("se","https://cdn.futura-sciences.com/buildsv6/images/largeoriginal/6/f/c/6fc6bc1b21_50021087_albert-einstein-langue.jpg"),
                Country("ud","https://www.science.edu/acellus/wp-content/uploads/2017/01/earth-405096_1920.jpg"),
                Country("zh", "https://www.science.edu/acellus/wp-content/uploads/2017/01/earth-405096_1920.jpg")
        )
        val adapterRecycler = CountriesAdapter(countries, this)
        recyclerView.layoutManager = LinearLayoutManager(view.context)
        recyclerView.adapter = adapterRecycler
    }

    override fun onClick(categoryName: String) {
        (activity as? MainActivity)?.changeFragment(ListOfArticlesFragment.newInstance(categoryName))
    }
}