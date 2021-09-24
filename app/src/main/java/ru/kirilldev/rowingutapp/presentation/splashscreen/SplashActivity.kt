package ru.kirilldev.rowingutapp.presentation.splashscreen

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import ru.kirilldev.rowingutapp.data.local.EntrySettings
import ru.kirilldev.rowingutapp.presentation.intro.RowingutIntroActivity
import ru.kirilldev.rowingutapp.presentation.main.MainActivity
import ru.kirilldev.rowingutapp.presentation.registration.RegistrationActivity
import ru.kirilldev.rowingutapp.viewmodels.*


class SplashActivity : AppCompatActivity() {


    private val viewModel: SplashViewModel by viewModels { SplashViewModelFactory(this) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.observeState(this){
            renderUI(it)
        }
    }

    private fun renderUI(entrySettings: EntrySettings){
        val thread = Thread{
            run{
                if (entrySettings.isFirstEntry) showIntroIfFirstEntry()
                else{
                    if(entrySettings.isSignedIn) showMainPage()
                    else showRegistration()
                }
            }
        }

        thread.start()

    }

    private fun showIntroIfFirstEntry(){
        val intent = Intent(this, RowingutIntroActivity::class.java)
            .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }

    private fun showRegistration(){
        val intent = Intent(this, RegistrationActivity::class.java)
            .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }

    private fun showMainPage(){
        val intent = Intent(this, MainActivity::class.java)
            .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }



}