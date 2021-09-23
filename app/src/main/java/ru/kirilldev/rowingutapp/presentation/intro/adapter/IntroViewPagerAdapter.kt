package ru.kirilldev.rowingutapp.presentation.intro.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import ru.kirilldev.rowingutapp.presentation.intro.SlideFragment

class IntroViewPagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {

    override fun getItemCount(): Int  = 3

    override fun createFragment(position: Int): Fragment {
        return SlideFragment.newInstance(position)
    }


}