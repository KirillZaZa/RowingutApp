package ru.kirilldev.rowingutapp.presentation.intro

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.core.view.size
import androidx.fragment.app.FragmentActivity
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.kirilldev.rowingutapp.R
import ru.kirilldev.rowingutapp.data.local.EntrySettings
import ru.kirilldev.rowingutapp.databinding.ActivityOnBoardingBinding
import ru.kirilldev.rowingutapp.presentation.intro.adapter.IntroViewPagerAdapter
import ru.kirilldev.rowingutapp.presentation.registration.RegistrationActivity
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
                val currentItem = viewBinding.viewpager2.currentItem

                if(currentItem == viewBinding.viewpager2.size){

                    viewModel.handleEntrySettings(EntrySettings(
                        isFirstEntry = false
                    ))

                    val intent = Intent(this, RegistrationActivity::class.java)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    startActivity(intent)

                }else viewModel.handleCurrentPage(
                        currentItem.inc()
                    )
            }
        }
    }

    private fun renderCurrentPage(data: ActivityOnBoardingData) {
        viewBinding.viewpager2.currentItem = data.currentPage
    }



}