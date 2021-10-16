package co.com.ceiba.mobile.pruebadeingreso.data.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import co.com.ceiba.mobile.pruebadeingreso.data.database.AppDataBase
import co.com.ceiba.mobile.pruebadeingreso.data.local.model.RoomUser
import kotlinx.coroutines.runBlocking
import org.junit.*
import org.junit.runner.RunWith
import kotlin.random.Random

@RunWith(AndroidJUnit4::class)
@SmallTest
class UserDaoTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: AppDataBase
    private lateinit var dao: UserDao

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
                ApplicationProvider.getApplicationContext(),
                AppDataBase::class.java
        ).allowMainThreadQueries().build()

        dao = database.userDao()
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun givenUsers_whenInsertAndGetThem_thenGetTheSameSize() = runBlocking {
        val fakeUsers = makeFakeUsers()

        dao.insert(fakeUsers)
        val users = dao.getAll()

        Assert.assertEquals(fakeUsers.size, users.size)
    }

    @Test
    fun givenUserWithSpecificId_whenInsertAndGetById_thenGetTheSameUser() = runBlocking {
        val id = 42L
        val fakeUser = makeFakeUser().copy(id = id)
        val fakeUsers = listOf(fakeUser)

        dao.insert(fakeUsers)
        val user = dao.getById(id)

        Assert.assertEquals(fakeUser, user)
    }

    private fun makeFakeUsers(size: Int = 4): List<RoomUser> {
        return (1..size).map { makeFakeUser() }
    }

    private fun makeFakeUser(): RoomUser {
        return RoomUser(
                id = Random.nextLong(),
                name = generateRandomString(6),
                email = generateRandomString(6),
                phoneNumber = generateRandomString(6),
        )
    }

    private fun generateRandomString(length: Int = 20): String {
        val allowedChars = ('A'..'Z') + ('a'..'z')
        return (1..length)
                .map { allowedChars.random() }
                .joinToString("")
    }
}