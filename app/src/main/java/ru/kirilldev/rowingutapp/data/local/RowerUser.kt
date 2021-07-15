package ru.kirilldev.rowingutapp.data.local

data class RowerUser(
    val name: String,
    val hash: String,
    val salt: String,
    val email: String,
    val age: String,
    val weight: String,
    val height: String
){

}


