package ru.kirilldev.rowingutapp.data.firebase.auth

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.*
import ru.kirilldev.rowingutapp.application.RowingutApplication
import ru.kirilldev.rowingutapp.extensions.createHash

class Authentication {

    companion object {

        private const val FIREBASE_TAG = "firebase_tag"

    }

    private val scope = RowingutApplication.scope!!

    private val auth by lazy {
        FirebaseAuth.getInstance()
    }


    private fun Job.cancelingJob() {
        if (this.isCompleted) this.cancel()
    }

    fun isSignedIn(): Boolean {
        return auth.currentUser != null
    }

    fun getCurrentUser() = auth.currentUser!!

    fun signUp(
        email: String,
        password: String,
        callback: (String) -> Unit
    ) {
        val job = scope.launch {
            try {
                var response: String = AuthenticationStatus.FAILED.name
                withContext(Dispatchers.IO) {
                    auth.createUserWithEmailAndPassword(email, password.createHash())
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                response = auth.currentUser!!.email!!
                            }
                        }
                }
                callback(response)
            } catch (e: CancellationException) {
                Log.e(FIREBASE_TAG, "signUp: ${e.message}")
                callback(AuthenticationStatus.ERROR.toString())
            }
        }

        job.cancelingJob()
    }

    fun signIn(
        email: String,
        password: String,
        callback: (String) -> Unit,
    ) {
        val job = scope.launch {
            try {
                var response: String = AuthenticationStatus.FAILED.name
                withContext(Dispatchers.IO) {
                    auth.signInWithEmailAndPassword(email, password.createHash())
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                response = auth.currentUser!!.email!!
                            }
                        }
                }

                callback(response)

            } catch (e: CancellationException) {

                Log.e(FIREBASE_TAG, "signIn: ${e.message}")

                callback(AuthenticationStatus.ERROR.toString())
            }
        }

        job.cancelingJob()
    }

    fun signOut() = auth.signOut()


}


enum class AuthenticationStatus {
    ERROR, FAILED
}