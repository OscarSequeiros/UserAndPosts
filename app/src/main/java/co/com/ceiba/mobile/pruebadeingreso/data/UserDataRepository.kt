package co.com.ceiba.mobile.pruebadeingreso.data

import co.com.ceiba.mobile.pruebadeingreso.data.local.PostDao
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
        private val userLocalSource: UserDao,
        private val postLocalSource: PostDao,
        private val remoteSource: UserRemoteSource,
        private val mapper: UserDataMapper,
) : UserRepository {

    override fun getAllUsers(): Flow<List<User>> {
        return flow {

            val localUsers = userLocalSource.getAll().ifEmpty {
                val remoteUsers = remoteSource.getAll()
                val newUsers = mapper.toRoom(remoteUsers)
                userLocalSource.insert(newUsers)
                userLocalSource.getAll()
            }

            val users = mapper.toDomain(localUsers)
            emit(users)
        }
    }

    override fun getUserWithPosts(userId: Long): Flow<UserWithPosts> {
        return flow {

            val user = userLocalSource.getById(userId)
            val posts = postLocalSource.getByUserId(userId).ifEmpty {
                val remotePosts = remoteSource.getPost(userId)
                val newPost = mapper.toRoom(remotePosts)
                postLocalSource.insert(newPost)
                postLocalSource.getByUserId(userId)
            }

            val userWithPosts = mapper.combineUserAndPosts(user, posts)
            emit(userWithPosts)
        }
    }

    override fun getUsersByName(name: String): Flow<List<User>> {
        TODO("Not yet implemented")
    }
}