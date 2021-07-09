package ru.kirilldev.rowingutapp.presentation

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import kotlinx.android.synthetic.main.intro_page_2.*
import ru.kirilldev.rowingutapp.R
import ru.kirilldev.rowingutapp.presentation.intro.RowingutIntroActivity

class RegistrationActivity : FragmentActivity(){

    companion object{
        private const val FIRST_START = "firstStart"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        val thread = Thread {
            run {
                val prefs = this.getPreferences(MODE_PRIVATE)

                val isFirstStart = prefs.getBoolean(FIRST_START, true)

                if (isFirstStart) {
                    val intent = Intent(this@RegistrationActivity, RowingutIntroActivity::class.java)
                    intent.addCategory(Intent.CATEGORY_HOME)
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                    startActivity(intent)
                    with(prefs.edit()) {
                        putBoolean(FIRST_START, false)
                        apply()
                    }
                }
            }
        }

        thread.start()

    }



    /**
     * TODO:
     * 1)Cверстать Онбординг экраны
     * 2)Реализовать архитектуру MVP
     * 3)Room, Firebase API
     *
     */
}