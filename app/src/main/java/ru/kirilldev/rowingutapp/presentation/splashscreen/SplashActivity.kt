package ru.kirilldev.rowingutapp.presentation.splashscreen

import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity


class SplashActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val thread = Thread {
            run {

            }

        }
        thread.start()

    }

    private fun firstAppearance(startPrefs: SharedPreferences){

    }

    private fun entryToApp(entryPrefs: SharedPreferences){

    }


}