package ru.kirilldev.rowingutapp.api.firebase.auth

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.*
import ru.kirilldev.rowingutapp.application.RowingutApplication
import ru.kirilldev.rowingutapp.extensions.createHash

class Authentication {

    companion object{

        private const val FIREBASE_TAG = "firebase_tag"

    }

    private val scope = RowingutApplication.scope

    private val auth by lazy {
        FirebaseAuth.getInstance()
    }

    /*
    TODO: Need to finish the realisation of network and local contribution with data
     */

    /**
     * getting training of the day
     *
     */

    private fun Job.cancelingJob() {
        if (this.isCompleted) this.cancel()
    }

    fun isSignedIn(): FirebaseUser?{
        if(auth.currentUser != null) return auth.currentUser
        return null
    }

    fun signUp(
        email: String,
        password: String,
        callback: (String?) -> Unit
    ) {
        val job = scope.launch {
            try {
                var user: FirebaseUser? = null
                withContext(Dispatchers.IO) {
                    auth.createUserWithEmailAndPassword(email, password.createHash())
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                user = auth.currentUser
                            }else callback(AuthenticationStatus.FAILED.toString())
                        }
                }
                callback(user?.email)
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
        callback: (String?) -> Unit,
        ) {
        val job = scope.launch {
            try {
                var user: FirebaseUser? = null

                withContext(Dispatchers.IO) {
                    auth.signInWithEmailAndPassword(email, password.createHash())
                        .addOnCompleteListener { task->
                            if(task.isSuccessful) user = auth.currentUser
                            else callback(AuthenticationStatus.FAILED.toString())
                        }
                }

                callback(user?.email)

            } catch (e: CancellationException) {

                Log.e(FIREBASE_TAG, "signIn: ${e.message}")

                callback(AuthenticationStatus.ERROR.toString())
            }
        }

        job.cancelingJob()
    }

    fun signOut() = auth.signOut()


}


enum class AuthenticationStatus{
    ERROR, FAILED
}