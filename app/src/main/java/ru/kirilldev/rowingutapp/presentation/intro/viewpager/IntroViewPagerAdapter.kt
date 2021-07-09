package ru.kirilldev.rowingutapp.presentation.intro.viewpager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import ru.kirilldev.rowingutapp.presentation.intro.SlideFragment

class IntroViewPagerAdapter(fm: FragmentActivity) : FragmentStateAdapter(fm) {


    companion object{
        private const val NUM_PAGES = 3
    }

    override fun getItemCount(): Int  = NUM_PAGES

    override fun createFragment(position: Int): Fragment {
        return SlideFragment.newInstance(position)
    }


}