package co.com.ceiba.mobile.pruebadeingreso.domain

import co.com.ceiba.mobile.pruebadeingreso.domain.model.User
import co.com.ceiba.mobile.pruebadeingreso.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllUsersUseCase @Inject constructor(
        private val repository: UserRepository
) {

    operator fun invoke(): Flow<List<User>> {
        return repository.getAllUsers()
    }
}