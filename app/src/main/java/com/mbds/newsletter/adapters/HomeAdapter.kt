package com.mbds.newsletter.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.mbds.newsletter.fragments.CategoriesFragment
import com.mbds.newsletter.fragments.CountryFragment
import com.mbds.newsletter.fragments.EditorsFragment

class HomeAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getCount(): Int  = 3

    override fun getItem(i: Int): Fragment {
        return when(i){
            0 -> EditorsFragment()
            1 -> CategoriesFragment()
            else -> CountryFragment()
        }
    }

    override fun getPageTitle(position: Int): CharSequence {

        return when(position){
            0 -> "Editeur"
            1 -> "Categorie"
            else -> "Pays"
        }
    }
}