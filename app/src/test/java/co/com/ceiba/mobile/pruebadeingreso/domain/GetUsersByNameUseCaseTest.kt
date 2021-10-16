package co.com.ceiba.mobile.pruebadeingreso.domain

import co.com.ceiba.mobile.pruebadeingreso.domain.model.User
import co.com.ceiba.mobile.pruebadeingreso.factory.makeFakeUsersWithName
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Test

class GetUsersByNameUseCaseTest {

    private val getAllUsersUseCase = mockk<GetAllUsersUseCase>()
    private val getUsersByNameUseCase = GetUsersByNameUseCase(getAllUsersUseCase = getAllUsersUseCase)

    @Test
    fun `given user from repository, when invoke, then get the same list`() = runBlocking {
        val fakeUsers = makeFakeUsersWithName(10, 6, "Leanne")
        val searchName = "Lean"
        stubUsersInRepository(fakeUsers)

        val usersFlow = getUsersByNameUseCase(searchName)

        usersFlow.collect { users ->
            assert(users.size == 6)
            users.all { it.name.contains("Lean", false) }
        }
    }

    private fun stubUsersInRepository(users: List<User>) {
        coEvery { getAllUsersUseCase() } coAnswers { flowOf(users) }
    }
}