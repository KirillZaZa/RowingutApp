package ru.kirilldev.rowingutapp.presentation.registration

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.widget.ViewPager2
import ru.kirilldev.rowingutapp.R
import ru.kirilldev.rowingutapp.presentation.registration.adapter.RegistrationViewPagerAdapter
import ru.kirilldev.rowingutapp.presentation.registration.interfaces.RegistrationView
import ru.kirilldev.rowingutapp.presentation.registration.presenter.RegistrationPresenter
import ru.kirilldev.rowingutapp.ui.reg.RegistrationBottombar
import kotlin.math.abs

class RegistrationActivity : FragmentActivity(){


    /**
     *
//     *
//      */
//    private lateinit var regBottombar: RegistrationBottombar
//    private lateinit var btnSignIn: TextView
//    private lateinit var btnSignUp: TextView
//    private lateinit var viewpager: ViewPager2
//    private lateinit var pagerAdapter: RegistrationViewPagerAdapter
//    private lateinit var registrationPresenter: RegistrationPresenter
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_registration)
//
//        registrationPresenter = RegistrationPresenter(this)
//        initializeViewPager()
//        updateBottombar()
//        initViews()
//        setListeners()
//    }
//
//
//    private fun initViews() {
//        regBottombar = reg_bottombar
//        btnSignIn = regBottombar.tv_sign_in
//        btnSignUp = regBottombar.tv_sign_up
//    }
//
//    private fun initializeViewPager() {
//        viewpager = viewpager_registration
//        pagerAdapter = RegistrationViewPagerAdapter(this)
//        viewpager.adapter = pagerAdapter
//    }
//
//    private fun setListeners() {
//        btnSignIn.setOnClickListener(this)
//        btnSignUp.setOnClickListener(this)
//    }
//
//    private fun updateBottombar() {
//        viewpager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
//            override fun onPageSelected(position: Int) {
//                when (position) {
//                    0 -> regBottombar.signUpAnimation {}
//                    1 -> regBottombar.signInAnimation {}
//                }
//
//                super.onPageSelected(position)
//            }
//        })
//    }
//
//
//    override fun showSignUp() {
//        // show sign up when animation starts
//        regBottombar.signInAnimation { isStart ->
//            if (isStart) {
//                viewpager.setCurrentItem(1, true)
//
//            }
//        }
//    }
//
//
//    override fun showSignIn() {
//        regBottombar.signUpAnimation { isStart ->
//            if (isStart) {
//                viewpager.setCurrentItem(0, true)
//            }
//        }
//    }
//
//    override fun showProgressBar() {
//
//    }
//
//    override fun showSuccess() {
//
//    }
//
//    override fun navigateToMainPage() {
//
//    }
//
//    override fun onBackPressed() {
//        super.onBackPressed()
//
//    }
//
//    override fun onClick(v: View?) {
//
//        if (v?.id == btnSignIn.id) {
//            registrationPresenter.onSignInPageClicked()
//        } else if (v?.id == btnSignUp.id) {
//            registrationPresenter.onSignUpPageClicked()
//        }
//
//    }

}
