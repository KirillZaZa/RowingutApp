package ru.kirilldev.rowingutapp.viewmodels.interfaces

import com.google.firebase.auth.FirebaseUser

interface IRegistrationViewModel {

    fun handleSignIn(email: String, passwordHash: String)

    fun handleSignUp(email: String, passwordHash: String)

    fun handleIsSignedIn(callback: (FirebaseUser?) -> Unit)

}