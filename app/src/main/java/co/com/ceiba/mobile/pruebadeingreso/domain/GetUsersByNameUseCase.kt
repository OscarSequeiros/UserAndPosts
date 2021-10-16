package co.com.ceiba.mobile.pruebadeingreso.domain

import co.com.ceiba.mobile.pruebadeingreso.domain.model.User
import co.com.ceiba.mobile.pruebadeingreso.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetUsersByNameUseCase @Inject constructor(
        private val getAllUsersUseCase: GetAllUsersUseCase
) {

    // TODO: This filter could be done at data base layer also

    operator fun invoke(name: String): Flow<List<User>> {
        return getAllUsersUseCase()
                .map { users -> users
                        .filter { user ->
                            user.name.contains(name, ignoreCase = true)
                        }
                }
    }
}