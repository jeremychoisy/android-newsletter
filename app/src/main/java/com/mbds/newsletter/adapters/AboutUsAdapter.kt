package com.mbds.newsletter.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.mbds.newsletter.fragments.*

class AboutUsAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getCount(): Int  = 2

    override fun getItem(i: Int): Fragment {
        return when(i){
            0 -> AboutUsTeamFragment()
            else -> AboutUsOtherFragment()
        }
    }

    override fun getPageTitle(position: Int): CharSequence {

        return when(position){
            0 -> "Team"
            else -> "Other"
        }
    }
}