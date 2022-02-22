package com.example.myapplication.Model

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.myapplication.View.Earth
import com.example.myapplication.View.Planet
import com.example.myapplication.View.SomePicture

private const val EARTH = 0
private const val PLANET = 1
private const val SOME_PICTURE = 2

class ViewPagerAdapter(private val fragmentManager: FragmentManager):
    FragmentStatePagerAdapter(fragmentManager) {

    private val fragments = arrayOf(Earth(),Planet(),SomePicture())

    override fun getCount(): Int {
        return fragments.size
    }

    override fun getItem(position: Int): Fragment {
        return when(position){
            0 -> fragments[EARTH]
            1 -> fragments[PLANET]
            2 -> fragments[SOME_PICTURE]
            else -> fragments[EARTH]
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
            0 -> "EARTH"
            1 -> "PLANET"
            2 -> "SOME_PICTURE"
            else -> "EARTH"
        }
    }
}