package ru.kirilldev.rowingutapp.presentation.splashscreen

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import ru.kirilldev.rowingutapp.R
import ru.kirilldev.rowingutapp.data.local.EntrySettings
import ru.kirilldev.rowingutapp.presentation.intro.RowingutIntroActivity
import ru.kirilldev.rowingutapp.presentation.main.MainActivity
import ru.kirilldev.rowingutapp.presentation.registration.RegistrationActivity
import ru.kirilldev.rowingutapp.viewmodels.*


class SplashActivity : AppCompatActivity() {


    private val viewModel: SplashViewModel by viewModels { SplashViewModelFactory(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.observeState(this) {
            renderUI(it)
        }

    }

    private fun renderUI(entrySettings: EntrySettings) {
        Log.e("Splash", "renderUI: $entrySettings", )
        if (entrySettings.isFirstEntry) showIntroIfFirstEntry()
        else {
            if (entrySettings.isSignedIn) showMainPage()
            else showRegistration()
        }
    }


    private fun showIntroIfFirstEntry() {
        val intent = Intent(this, RowingutIntroActivity::class.java)
            .apply {
                addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }
        startActivity(intent)
        overridePendingTransition(0,0)
        this.finish()

    }

    private fun showRegistration() {
        val intent = Intent(this, RegistrationActivity::class.java)
            .apply {
                addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }
        startActivity(intent)
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        this.finish()


    }

    private fun showMainPage() {
        val intent = Intent(this, MainActivity::class.java)
            .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
        this.finish()


    }


}