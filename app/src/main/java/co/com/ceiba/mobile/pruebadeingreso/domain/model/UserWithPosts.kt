package co.com.ceiba.mobile.pruebadeingreso.domain.model

data class UserWithPosts(
        val user: User,
        val posts: List<Post>
)