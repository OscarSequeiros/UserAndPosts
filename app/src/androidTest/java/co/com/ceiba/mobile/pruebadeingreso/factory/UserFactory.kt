package co.com.ceiba.mobile.pruebadeingreso.factory

import co.com.ceiba.mobile.pruebadeingreso.data.local.model.RoomUser
import kotlin.random.Random

fun generateFakeUsers(size: Int = 4): List<RoomUser> {
    return (1..size).map { generateFakeUser() }
}

fun generateFakeUser(): RoomUser {
    return RoomUser(
            id = Random.nextLong(),
            name = makeRandomString(6),
            email = makeRandomString(6),
            phoneNumber = makeRandomString(6),
    )
}