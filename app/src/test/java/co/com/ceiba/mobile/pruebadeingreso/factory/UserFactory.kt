package co.com.ceiba.mobile.pruebadeingreso.factory

import co.com.ceiba.mobile.pruebadeingreso.domain.model.Post
import co.com.ceiba.mobile.pruebadeingreso.domain.model.User
import co.com.ceiba.mobile.pruebadeingreso.domain.model.UserWithPosts
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

fun makeFakeUserWithPosts(postsSize: Int = 5): UserWithPosts {
    val user = makeFakeUser()
    val posts = (1..postsSize).map { makeFakePost() }

    return UserWithPosts(
            user = user,
            posts = posts
    )
}

private fun makeFakePost(): Post {
    return Post(
            id = Random.nextLong(),
            title = generateRandomString(22),
            body = generateRandomString(40)
    )
}