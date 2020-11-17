package com.mbds.newsletter.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.Observer
import androidx.lifecycle.liveData
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.mbds.newsletter.MainActivity
import com.mbds.newsletter.R
import com.mbds.newsletter.adapters.HomeAdapter
import com.mbds.newsletter.adapters.SelectedFilter
import com.mbds.newsletter.data.models.Resource
import com.mbds.newsletter.data.models.Status
import com.mbds.newsletter.data.services.ArticleHttpService
import com.mbds.newsletter.data.services.EditorHttpService
import kotlinx.coroutines.Dispatchers

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment() {
    private lateinit var homeAdapter: HomeAdapter
    private lateinit var viewPager: ViewPager

    private val repository = ArticleHttpService()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeAdapter = HomeAdapter(childFragmentManager)
        viewPager = view.findViewById(R.id.pager)
        viewPager.adapter = homeAdapter

        val tabLayout = view.findViewById<TabLayout>(R.id.tab_layout)
        tabLayout.setupWithViewPager(viewPager)

        view.findViewById<Button>(R.id.button_search).setOnClickListener {
            println("URL : " + createURL(true))
            fetchData(createURL(false), createURL(true)).observe(viewLifecycleOwner, Observer {
                it?.let { resource ->
                    when (resource.status) {
                        Status.SUCCESS -> {
//                        recyclerView.visibility = View.VISIBLE
//                        spinner.visibility = View.GONE
                            println(resource.data)
                            resource.data?.let { editor -> println(editor) }
                        }
                        Status.ERROR -> {
//                        recyclerView.visibility = View.VISIBLE
//                        spinner.visibility = View.GONE
                        }
                        Status.LOADING -> {
//                        spinner.visibility = View.VISIBLE
//                        recyclerView.visibility = View.GONE
                        }
                    }
                }
            })
        }


//        view.findViewById<TabLayout>(R.id.tabLayout).addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
//
//            override fun onTabSelected(tab: TabLayout.Tab?) {
//                // Handle tab select
//                println(tab.toString())
//            }
//
//            override fun onTabReselected(tab: TabLayout.Tab?) {
//                // Handle tab reselect
//            }
//
//            override fun onTabUnselected(tab: TabLayout.Tab?) {
//                // Handle tab unselect
//            }
//        })
//        val button = view.findViewById<Button>(R.id.button)
//        button.setOnClickListener {
//            (activity as? MainActivity)?.changeFragment(CategoriesFragment())
//        }
    }

    fun createURL(select: Boolean): String {
        var url = ""
        if(select){
            SelectedFilter.list.forEachIndexed { index, s ->
                url += s.replace(" ", "-")
                if(index < SelectedFilter.list.size-1)
                    url += ","
            }
        }
        else{
            SelectedFilter.listCategoryAndCountry.forEachIndexed { index, s ->
                url += s
                if(index < SelectedFilter.listCategoryAndCountry.size-1)
                    url += " AND "
            }
        }
        return url
    }

    private fun fetchData(category: String, sources: String) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = repository.getArticlesFiltered(category, sources)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }
}