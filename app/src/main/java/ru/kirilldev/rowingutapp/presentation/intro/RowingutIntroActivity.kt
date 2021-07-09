package ru.kirilldev.rowingutapp.presentation.intro

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.widget.ViewPager2
import kotlinx.android.synthetic.main.activity_onboarding.*
import ru.kirilldev.rowingutapp.R
import ru.kirilldev.rowingutapp.presentation.intro.viewpager.IntroViewPagerAdapter


class RowingutIntroActivity : FragmentActivity(), View.OnClickListener{

    private lateinit var viewpager: ViewPager2
    private lateinit var pagerAdapter: IntroViewPagerAdapter
    private lateinit var bottombar: View
    private lateinit var buttonNext: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)
        initializeViews()
        setupViewPager()
        updateBottombar()
        setListeners()
    }



    private fun initializeViews(){
        bottombar = bottombarDown.getChildAt(0)
        buttonNext = bottombarDown.getChildAt(1)
    }

    private fun setListeners(){
        buttonNext.setOnClickListener(this)
    }

    private fun setupViewPager(){
        viewpager = viewpager2
        pagerAdapter = IntroViewPagerAdapter(this, )
        viewpager.adapter = pagerAdapter

    }

    private fun updateBottombar(){
        viewpager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                when(position){
                    0 -> (bottombar as ImageView).setImageResource(R.drawable.ic_pages_1)
                    1 -> (bottombar as ImageView).setImageResource(R.drawable.ic_pages_2)
                    2 -> {
                        (bottombar as ImageView).setImageResource(R.drawable.ic_pages_3)

                    }
                    else -> (bottombar as ImageView).setImageResource(R.drawable.ic_pages_1)
                }

                super.onPageSelected(position)
            }
        })
    }


    override fun onBackPressed() {
        if(viewpager.currentItem != 0){
            viewpager.currentItem--
        }else{
            finishAndRemoveTask()
        }
    }

    override fun onClick(v: View?) {
        if(viewpager.currentItem < 2){
            viewpager.currentItem++
        }else{
            finish()
        }
    }


}