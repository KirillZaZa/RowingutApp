package ru.kirilldev.rowingutapp.presentation.intro

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.fragment.app.FragmentActivity
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.kirilldev.rowingutapp.R
import ru.kirilldev.rowingutapp.databinding.ActivityOnBoardingBinding
import ru.kirilldev.rowingutapp.presentation.intro.adapter.IntroViewPagerAdapter
import ru.kirilldev.rowingutapp.viewmodels.ActivityOnBoardingData
import ru.kirilldev.rowingutapp.viewmodels.OnBoardingViewModel
import ru.kirilldev.rowingutapp.viewmodels.OnBoardingViewModelFactory


class RowingutIntroActivity : FragmentActivity(R.layout.activity_on_boarding),
    View.OnClickListener {

    private val viewModel: OnBoardingViewModel by viewModels { OnBoardingViewModelFactory(this) }
    private val viewBinding by viewBinding(ActivityOnBoardingBinding::bind)
    private val vbBottombar
        get() = viewBinding.bottombarDown.binding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_boarding)
        setupViewPager()
        setListeners()

        viewModel.observeState(this) {
            renderCurrentPage(it)
        }
    }

    private fun setListeners() {
        vbBottombar.nextPageButton.setOnClickListener(this)
    }

    private fun setupViewPager(){
        val introAdapter = IntroViewPagerAdapter(this)

        viewBinding.viewpager2
            .adapter = introAdapter

        viewBinding.viewpager2.isUserInputEnabled = false

    }

    override fun onSaveInstanceState(outState: Bundle) {
        viewModel.saveState()
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        viewModel.restoreState()
        super.onRestoreInstanceState(savedInstanceState)
    }

    override fun onClick(v: View?) {
        v ?: return
        when(v){
            vbBottombar.nextPageButton -> {
                viewModel.handleCurrentPage(
                    viewBinding.viewpager2.currentItem++
                )
            }
        }
    }

    private fun renderCurrentPage(data: ActivityOnBoardingData) {
        viewBinding.viewpager2.currentItem = data.currentPage
    }



}