package co.com.ceiba.mobile.pruebadeingreso.factory

import co.com.ceiba.mobile.pruebadeingreso.domain.model.User
import kotlin.random.Random

fun makeFakeUsers(size: Int = 4): List<User> {
    return (1..size).map { makeFakeUser() }
}

fun makeFakeUser(): User {
    return User(
            id = Random.nextLong(),
            name = generateRandomString(6),
            email = generateRandomString(6),
            phoneNumber = generateRandomString(6),
    )
}