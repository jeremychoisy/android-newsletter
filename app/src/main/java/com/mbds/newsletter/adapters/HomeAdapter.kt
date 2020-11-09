package com.mbds.newsletter.adapters

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.mbds.newsletter.fragments.CategoriesFragment

class HomeAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getCount(): Int  = 3

    override fun getItem(i: Int): Fragment {
        val fragment = CategoriesFragment()
//        fragment.arguments = Bundle().apply {
//            // Our object is just an integer :-P
//            putInt(ARG_OBJECT, i + 1)
//        }
        return fragment
    }

    override fun getPageTitle(position: Int): CharSequence {

        return when(position){
            0 -> "Editeur"
            1 -> "Categorie"
            else -> "Pays"
        }
    }
}