package co.com.ceiba.mobile.pruebadeingreso.domain

data class UserWithPosts(
        val user: User,
        val posts: List<Post>
)