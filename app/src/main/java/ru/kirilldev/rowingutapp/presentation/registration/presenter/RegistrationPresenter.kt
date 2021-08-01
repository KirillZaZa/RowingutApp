package ru.kirilldev.rowingutapp.presentation.registration.presenter

import ru.kirilldev.rowingutapp.presentation.registration.interfaces.RegistrationView

class RegistrationPresenter(
    val regView: RegistrationView
) {

    fun onSignInPageClicked(){
        regView.showSignIn()
    }

    fun onSignUpPageClicked(){
        regView.showSignUp()
    }

    fun onEmailSelected(){

    }

    fun onPasswordSelected(){

    }

    fun onConfirmPasswordSelected(){

    }





}