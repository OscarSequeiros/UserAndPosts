package co.com.ceiba.mobile.pruebadeingreso.data

import co.com.ceiba.mobile.pruebadeingreso.data.local.UserDao
import co.com.ceiba.mobile.pruebadeingreso.data.mapper.UserDataMapper
import co.com.ceiba.mobile.pruebadeingreso.data.remote.UserRemoteSource
import co.com.ceiba.mobile.pruebadeingreso.domain.User
import co.com.ceiba.mobile.pruebadeingreso.domain.UserWithPosts
import co.com.ceiba.mobile.pruebadeingreso.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow

class UserDataRepository(
        private val localSource: UserDao,
        private val remoteSource: UserRemoteSource,
        private val mapper: UserDataMapper,
) : UserRepository {

    override fun getAllUsers(): Flow<List<User>> {
        TODO("Not yet implemented")
    }

    override fun getUserWithPosts(userId: Long): Flow<UserWithPosts> {
        TODO("Not yet implemented")
    }

    override fun getUsersByName(name: String): Flow<List<User>> {
        TODO("Not yet implemented")
    }
}