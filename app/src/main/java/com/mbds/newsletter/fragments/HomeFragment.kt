package com.mbds.newsletter.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.mbds.newsletter.MainActivity
import com.mbds.newsletter.R
import com.mbds.newsletter.adapters.HomeAdapter
import com.mbds.newsletter.adapters.SelectedFilter
import com.mbds.newsletter.data.services.ArticleHttpService

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

        SelectedFilter.list.clear()
        SelectedFilter.listCategory.clear()
        SelectedFilter.listCountry.clear()
        SelectedFilter.listPositionEditor.clear()
        SelectedFilter.listPositionCategory.clear()
        SelectedFilter.listPositionCountry.clear()

        homeAdapter = HomeAdapter(childFragmentManager)
        viewPager = view.findViewById(R.id.pager)
        viewPager.adapter = homeAdapter

        val tabLayout = view.findViewById<TabLayout>(R.id.tab_layout)
        tabLayout.setupWithViewPager(viewPager)

        view.findViewById<Button>(R.id.button_search).setOnClickListener {
            if(SelectedFilter.listCategory.isEmpty() && SelectedFilter.list.isEmpty()) {
                Toast.makeText(context, "SELECT AT LEAST ONE EDITOR OR ONE CATEGORY", Toast.LENGTH_LONG).show()
            } else {
                (activity as? MainActivity)?.changeFragment(
                    ListOfArticlesFragment.newInstance()
                )
            }
        }
    }

}