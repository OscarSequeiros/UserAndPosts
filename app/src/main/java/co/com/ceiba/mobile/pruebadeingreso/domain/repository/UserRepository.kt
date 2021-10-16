package co.com.ceiba.mobile.pruebadeingreso.domain.repository

import co.com.ceiba.mobile.pruebadeingreso.domain.model.User
import co.com.ceiba.mobile.pruebadeingreso.domain.model.UserWithPosts
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    fun getAllUsers(): Flow<List<User>>

    fun getUserWithPosts(userId: Long): Flow<UserWithPosts>
}