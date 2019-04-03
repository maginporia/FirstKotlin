package com.flyavis.firstkotlin.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class FragmentAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
    private lateinit var fragmentDog: ContentFragment
    private lateinit var fragmentCat: ContentFragment

    override fun getItem(position: Int): Fragment {
        var fragment: Fragment? = null
        fragmentDog = ContentFragment()
        fragmentCat = ContentFragment()
        fragment = if (position == 0) {
            fragmentDog.arguments = Bundle().apply {
                putInt("key", 0)
            }
            fragmentDog
        } else {
            fragmentDog.arguments = Bundle().apply {
                putInt("key", 1)
            }
            fragmentCat
        }
        return fragment
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when (position) {
            0 -> return "狗"
            1 -> return "貓"
        }
        return super.getPageTitle(position)
    }
}