package ru.kirilldev.rowingutapp.extensions

import android.util.Log


fun Exception.printError(TAG: String){
    Log.e(TAG, "${this.message}")
}