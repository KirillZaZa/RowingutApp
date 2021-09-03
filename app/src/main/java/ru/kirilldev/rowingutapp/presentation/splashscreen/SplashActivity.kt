package ru.kirilldev.rowingutapp.presentation.splashscreen

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.kirilldev.rowingutapp.data.local.RowerUser
import ru.kirilldev.rowingutapp.presentation.intro.RowingutIntroActivity
import ru.kirilldev.rowingutapp.presentation.registration.RegistrationActivity
import ru.kirilldev.rowingutapp.repository.holder.FirebaseDataHolder
import ru.kirilldev.rowingutapp.ui.main.CheckableImageView
import ru.kirilldev.rowingutapp.viewmodels.TrainingViewModel
import ru.kirilldev.rowingutapp.viewmodels.delegates.ViewModelDelegate

class SplashActivity : AppCompatActivity() {

    // создаем ViewModel посредством нашего делегата


    companion object {
        private const val FIRST_START = "firstStart"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        val thread = Thread{
            run{
                val prefs = this.getPreferences(MODE_PRIVATE)
                val isFirstStart = prefs.getBoolean(FIRST_START, true)

                if (isFirstStart){
                    val intent = Intent(this, RowingutIntroActivity::class.java)

                    with(prefs.edit()){
                        putBoolean(FIRST_START, false)
                        apply()
                    }

                    startActivity(intent)
                    finish()
                }else{
                    val intent = Intent(this, RegistrationActivity::class.java)
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
                    startActivity(intent)
                    finish()
                }
            }

        }
        thread.start()

    }


}