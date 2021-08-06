package ru.kirilldev.rowingutapp.data.local

import java.lang.ref.WeakReference


data class RowerUser(
    val name: String,
    val hash: String,
    val salt: String,
    val email: String,
    val age: String,
    val weight: String,
    val height: String
)

class User(name: String, age: Int){

    private var _name: String? = name.lowercase()
    private var _age: Int = age
    private var list = ArrayList<String>().apply {
        ensureCapacity(10)
    }

    fun test(){
        list.apply {

        }
    }

}


