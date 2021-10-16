package co.com.ceiba.mobile.pruebadeingreso.domain

import co.com.ceiba.mobile.pruebadeingreso.domain.model.UserWithPosts
import co.com.ceiba.mobile.pruebadeingreso.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUserWithPostsUseCase @Inject constructor(
        private val repository: UserRepository
) {

    operator fun invoke(userId: Long): Flow<UserWithPosts> {
        return repository.getUserWithPosts(userId)
    }
}