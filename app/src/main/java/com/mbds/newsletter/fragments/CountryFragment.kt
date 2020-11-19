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
                Country("ar","https://img.freepik.com/vecteurs-libre/illustration-du-drapeau-argentin_53876-27120.jpg?size=626&ext=jpg"),
                //Allemand
                Country("de","https://images-na.ssl-images-amazon.com/images/I/11ZRN4et7PL._AC_.jpg"),
                //Anglais
                Country("en","https://i.pinimg.com/originals/53/e8/7d/53e87dc1874e487d38eeba168d8ff54e.png"),
                //Espagnol
                Country("es","https://images-na.ssl-images-amazon.com/images/I/31HnPyXBMGL._AC_.jpg"),

                //Francais
                Country("fr","https://media.onnsports.com/product/bandera-francesa-lalizas-800x800.jpg"),
                //?
                //Country("he","https://cdn.futura-sciences.com/buildsv6/images/largeoriginal/6/f/c/6fc6bc1b21_50021087_albert-einstein-langue.jpg"),
                //Italien
                Country("it","https://cdn.countryflags.com/thumbs/italy/flag-400.png"),
                //Pays-Bas
                Country("nl","https://www.produire-bio.fr/wp-content/uploads/2020/04/Drapeau-NL-Pays-Bas.jpg"),
                //Norvegien
                Country("no","https://cdn.countryflags.com/thumbs/norway/flag-800.png"),
                //Portugais
                Country("pt","https://cdn.webshopapp.com/shops/31683/files/191163398/image.jpg"),
                //Russe
                Country("ru","https://www.drapeaux-flags.com/images/drapeaux/png_norm/RU.png"),
                //Suédois
                Country("se","https://cdn.webshopapp.com/shops/94414/files/54945896/image-drapeau-suedois-telechargement-gratuit.jpg")
                //?
                // Country("ud","https://www.science.edu/acellus/wp-content/uploads/2017/01/earth-405096_1920.jpg"),
                //?
                // Country("zh", "https://www.science.edu/acellus/wp-content/uploads/2017/01/earth-405096_1920.jpg")


        )
        val adapterRecycler = CountriesAdapter(countries, this)
        recyclerView.layoutManager = LinearLayoutManager(view.context)
        recyclerView.adapter = adapterRecycler
    }

    override fun onClick(categoryName: String) {
        (activity as? MainActivity)?.changeFragment(ListOfArticlesFragment.newInstance())
    }
}