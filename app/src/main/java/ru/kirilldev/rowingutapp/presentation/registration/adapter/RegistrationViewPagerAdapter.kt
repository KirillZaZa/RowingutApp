package ru.kirilldev.rowingutapp.presentation.registration.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import ru.kirilldev.rowingutapp.presentation.registration.CurrentRegistrationFragment

class RegistrationViewPagerAdapter(fm: FragmentActivity): FragmentStateAdapter(fm) {


    override fun getItemCount() = 2
    override fun createFragment(position: Int): Fragment {
        return CurrentRegistrationFragment.newInstance(position)
    }
}