package co.com.ceiba.mobile.pruebadeingreso.factory

fun makeRandomString(length: Int = 20): String {
    val allowedChars = ('A'..'Z') + ('a'..'z')
    return (1..length)
            .map { allowedChars.random() }
            .joinToString("")
}