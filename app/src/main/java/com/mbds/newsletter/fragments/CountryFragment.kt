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
                //Argentin
                Country("ar","https://images-na.ssl-images-amazon.com/images/I/11ZRN4et7PL._AC_.jpg"),
                //Allemand
                Country("de","https://images-na.ssl-images-amazon.com/images/I/11ZRN4et7PL._AC_.jpg"),
                //Anglais
                Country("en","https://images-na.ssl-images-amazon.com/images/I/11ZRN4et7PL._AC_.jpg"),
                //Espagnol
                Country("es","https://images-na.ssl-images-amazon.com/images/I/31HnPyXBMGL._AC_.jpg")
                        /*
                //Francais
                Country("fr","https://upload.wikimedia.org/wikipedia/commons/thumb/c/c3/Flag_of_France.svg/1200px-Flag_of_France.svg.png"),
                //?
                //Country("he","https://cdn.futura-sciences.com/buildsv6/images/largeoriginal/6/f/c/6fc6bc1b21_50021087_albert-einstein-langue.jpg"),
                //Italien
                Country("it","https://upload.wikimedia.org/wikipedia/commons/thumb/0/03/Flag_of_Italy.svg/langfr-225px-Flag_of_Italy.svg.png"),
                //Pays-Bas
                Country("nl","https://upload.wikimedia.org/wikipedia/commons/2/20/Flag_of_the_Netherlands.svg"),
                //Norvegien
                Country("no","https://upload.wikimedia.org/wikipedia/commons/d/d9/Flag_of_Norway.svg"),
                //Portugais
                Country("pt","https://upload.wikimedia.org/wikipedia/commons/5/5c/Flag_of_Portugal.svg"),
                //Russe
                Country("ru","https://upload.wikimedia.org/wikipedia/commons/f/f3/Flag_of_Russia.svg.png"),
                //Suédois
                Country("se","https://upload.wikimedia.org/wikipedia/commons/thumb/4/4c/Flag_of_Sweden.svg/langfr-338px-Flag_of_Sweden.svg.png")
                //?
                // Country("ud","https://www.science.edu/acellus/wp-content/uploads/2017/01/earth-405096_1920.jpg"),
                //?
                // Country("zh", "https://www.science.edu/acellus/wp-content/uploads/2017/01/earth-405096_1920.jpg")

                         */
        )
        val adapterRecycler = CountriesAdapter(countries, this)
        recyclerView.layoutManager = LinearLayoutManager(view.context)
        recyclerView.adapter = adapterRecycler
    }

    override fun onClick(categoryName: String) {
        (activity as? MainActivity)?.changeFragment(ListOfArticlesFragment.newInstance(categoryName))
    }
}