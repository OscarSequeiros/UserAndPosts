package co.com.ceiba.mobile.pruebadeingreso.domain

import co.com.ceiba.mobile.pruebadeingreso.domain.model.UserWithPosts
import co.com.ceiba.mobile.pruebadeingreso.domain.repository.UserRepository
import co.com.ceiba.mobile.pruebadeingreso.factory.makeFakeUserWithPosts
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class GetUserWithPostsUseCaseTest {

    private val repository = mockk<UserRepository>()
    private val useCase = GetUserWithPostsUseCase(repository = repository)

    @Test
    fun `given user and posts from repository, when invoke, then get the same elements`() = runBlocking {
        val fakeUserWithPosts = makeFakeUserWithPosts(3)
        stubUsersInRepository(fakeUserWithPosts)
        val userId = 42L

        val userFlow = useCase(userId)

        userFlow.collect { userWithPosts -> assertEquals(fakeUserWithPosts, userWithPosts) }
    }

    private fun stubUsersInRepository(user: UserWithPosts) {
        coEvery { repository.getUserWithPosts(any()) } coAnswers { flowOf(user) }
    }
}