package ru.kirilldev.rowingutapp.extensions

import java.security.MessageDigest


//email validation
fun String.isEmailValid(email: String): Boolean{
    val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
    return email.matches(emailPattern.toRegex())
}

fun String.checkPasswordLength(password: String): Boolean{
    if(password.length < 6) return false
    return true
}

fun String.isPasswordValid(password: String): Boolean{
    val passwordPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#\$%^&+=])(?=\\\\S+\$).{4,}\$"
    return password.matches(passwordPattern.toRegex())
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





