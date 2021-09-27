package ru.kirilldev.rowingutapp.presentation.intro

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.core.app.ActivityCompat
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


class RowingutIntroActivity : FragmentActivity(),
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
        Log.e("Intro", "onCreate: Setup")
        viewModel.observeState(this) {
            renderCurrentPage(it)
        }
    }

    private fun setListeners() {
        vbBottombar.nextPageButton.setOnClickListener(this)
    }

    private fun setupViewPager() {
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
        when (v) {
            vbBottombar.nextPageButton -> {
                viewModel.handleCurrentPage(
                    page = viewBinding.viewpager2.currentItem.inc()
                )
            }
        }

    }

    private fun renderCurrentPage(data: ActivityOnBoardingData) {
        Log.e(REGISTRATION_TAG, "page : ${data.currentPage}, item : ${viewBinding.viewpager2.currentItem}", )
        viewBinding.viewpager2.currentItem = data.currentPage
        with(viewBinding.bottombarDown){
            setBottombarImage(data.currentPage)
        }

        if(data.currentPage > 2){
            launchRegistration()
        }
    }

    private fun launchRegistration(){
        val intent = Intent(this, RegistrationActivity::class.java)
            .apply {
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            }
        ActivityCompat.startActivity(this, intent, null)
        overridePendingTransition(R.anim.fade_in,R.anim.fade_out)
        finish()

    }

    override fun onStop() {
        super.onStop()
        viewModel.handleEntrySettings(
            EntrySettings(
                isFirstEntry = false
            )
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e(REGISTRATION_TAG, "onDestroy: finished")
        viewBinding.viewpager2.adapter = null
    }


    companion object{
        private const val REGISTRATION_TAG = "registration_tag"
    }

}