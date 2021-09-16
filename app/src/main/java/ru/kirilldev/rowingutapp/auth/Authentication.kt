package ru.kirilldev.rowingutapp.auth

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.*
import ru.kirilldev.rowingutapp.application.RowingutApplication
import ru.kirilldev.rowingutapp.extensions.createHash

class AuthenticationFirebase {

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

    fun isSignedIn(callback: (Boolean) -> Unit) {
        callback(auth.currentUser != null)
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
                            }
                        }
                }
                callback(user?.email)
            } catch (e: CancellationException) {
                Log.e(FIREBASE_TAG, "signUp: ${e.message}")
                callback("Error with request")
            }
        }

        job.cancelingJob()
    }

    fun signIn(
        callback: (String?) -> Unit,
        email: String,
        password: String
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