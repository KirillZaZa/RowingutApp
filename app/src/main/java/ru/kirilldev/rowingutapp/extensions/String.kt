package ru.kirilldev.rowingutapp.extensions

import java.security.MessageDigest


//email validation
fun String.checkEmail(email: String): Boolean{
    return false
}


//name validation
fun String.checkName(name: String): Boolean{
    return false
}


fun String.createHash(): String{
    val bytes = this.toByteArray()
    val md = MessageDigest.getInstance("SHA-512")
    val digest = md.digest(bytes)

    return digest.fold("", { str, it -> str + "%02x".format(it) })
}





