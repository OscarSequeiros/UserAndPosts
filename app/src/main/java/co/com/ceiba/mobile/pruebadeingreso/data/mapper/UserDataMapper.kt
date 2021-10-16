package co.com.ceiba.mobile.pruebadeingreso.data.mapper

import co.com.ceiba.mobile.pruebadeingreso.data.local.model.RoomPost
import co.com.ceiba.mobile.pruebadeingreso.data.local.model.RoomUser
import co.com.ceiba.mobile.pruebadeingreso.data.remote.model.RemotePost
import co.com.ceiba.mobile.pruebadeingreso.data.remote.model.RemoteUser
import co.com.ceiba.mobile.pruebadeingreso.domain.model.Post
import co.com.ceiba.mobile.pruebadeingreso.domain.model.User
import co.com.ceiba.mobile.pruebadeingreso.domain.model.UserWithPosts
import javax.inject.Inject

class UserDataMapper @Inject constructor() {

    fun toRoom(users: List<RemoteUser>): List<RoomUser> {
        return users.map { user -> user.toRoom() }
    }

    private fun RemoteUser.toRoom(): RoomUser {
        return RoomUser(
                id = id,
                name = name,
                email = email,
                phoneNumber = phone
        )
    }

    fun toDomain(users: List<RoomUser>): List<User> {
        return users.map { user -> user.toDomain() }
    }

    private fun RoomUser.toDomain(): User {
        return User(
                id = id,
                name = name,
                email = email,
                phoneNumber = phoneNumber
        )
    }

    @JvmName("postsToRoom")
    fun toRoom(posts: List<RemotePost>): List<RoomPost> {
        return posts.map { post -> post.toRoom() }
    }

    private fun RemotePost.toRoom(): RoomPost {
        return RoomPost(
                id = id,
                userId = userId,
                title = title,
                body = body
        )
    }

    fun combineUserAndPosts(user: RoomUser, posts: List<RoomPost>): UserWithPosts {
        return UserWithPosts(
                user = user.toDomain(),
                posts = toDomain(posts)
        )
    }

    @JvmName("postsToDomain")
    fun toDomain(posts: List<RoomPost>): List<Post> {
        return posts.map { post -> post.toDomain() }
    }

    private fun RoomPost.toDomain(): Post {
        return Post(
                id = id,
                title = title,
                body = body
        )
    }
}