package co.com.ceiba.mobile.pruebadeingreso.factory

import co.com.ceiba.mobile.pruebadeingreso.data.local.model.RoomPost
import kotlin.random.Random

fun generateFakePosts(size: Int = 6, userId: Long): List<RoomPost> {
    return (1..size).map { generateFakePost(userId) }
}

fun generateFakePost(userId: Long): RoomPost {
    return RoomPost(
            id = Random.nextLong(),
            userId = userId,
            title = makeRandomString(20),
            body = makeRandomString(50)
    )
}