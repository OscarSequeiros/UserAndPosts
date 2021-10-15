package co.com.ceiba.mobile.pruebadeingreso.data

import co.com.ceiba.mobile.pruebadeingreso.data.local.UserDao
import co.com.ceiba.mobile.pruebadeingreso.data.mapper.UserDataMapper
import co.com.ceiba.mobile.pruebadeingreso.data.remote.UserRemoteSource
import co.com.ceiba.mobile.pruebadeingreso.domain.model.User
import co.com.ceiba.mobile.pruebadeingreso.domain.model.UserWithPosts
import co.com.ceiba.mobile.pruebadeingreso.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UserDataRepository @Inject constructor(
        private val localSource: UserDao,
        private val remoteSource: UserRemoteSource,
        private val mapper: UserDataMapper,
) : UserRepository {

    override fun getAllUsers(): Flow<List<User>> {
        return flow {

            val localUsers = localSource.getAll().ifEmpty {
                val remoteUsers = remoteSource.getAll()
                val newUsers = mapper.toRoom(remoteUsers)
                localSource.insert(newUsers)
                localSource.getAll()
            }

            val users = mapper.toDomain(localUsers)
            emit(users)
        }
    }

    override fun getUserWithPosts(userId: Long): Flow<UserWithPosts> {
        TODO("Not yet implemented")
    }

    override fun getUsersByName(name: String): Flow<List<User>> {
        TODO("Not yet implemented")
    }
}