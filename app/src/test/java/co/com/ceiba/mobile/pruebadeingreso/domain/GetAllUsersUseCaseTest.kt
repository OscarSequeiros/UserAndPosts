package co.com.ceiba.mobile.pruebadeingreso.domain

import co.com.ceiba.mobile.pruebadeingreso.domain.model.User
import co.com.ceiba.mobile.pruebadeingreso.domain.repository.UserRepository
import co.com.ceiba.mobile.pruebadeingreso.factory.makeFakeUsers
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class GetAllUsersUseCaseTest {

    private val repository = mockk<UserRepository>()
    private val useCase = GetAllUsersUseCase(repository = repository)

    @Test
    fun `given user from repository, when invoke, then get the same list`() = runBlocking {
        val users = makeFakeUsers(5)
        stubUsersInRepository(users)

        val usersFlow = useCase()

        usersFlow.collect { usersFromUseCase -> assertEquals(users, usersFromUseCase) }
    }

    private fun stubUsersInRepository(users: List<User>) {
        coEvery { repository.getAllUsers() } coAnswers { flowOf(users) }
    }
}