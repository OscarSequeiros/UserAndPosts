package co.com.ceiba.mobile.pruebadeingreso.data.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import co.com.ceiba.mobile.pruebadeingreso.data.database.AppDataBase
import co.com.ceiba.mobile.pruebadeingreso.factory.generateFakeUser
import co.com.ceiba.mobile.pruebadeingreso.factory.generateFakeUsers
import kotlinx.coroutines.runBlocking
import org.junit.*
import org.junit.runner.RunWith

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
        val fakeUsers = generateFakeUsers()

        dao.insert(fakeUsers)
        val users = dao.getAll()

        Assert.assertEquals(fakeUsers.size, users.size)
        users.forEach { user ->
            assert(fakeUsers.contains(user))
        }
    }

    @Test
    fun givenUserWithSpecificId_whenInsertAndGetById_thenGetTheSameUser() = runBlocking {
        val id = 42L
        val fakeUser = generateFakeUser().copy(id = id)
        val fakeUsers = listOf(fakeUser)

        dao.insert(fakeUsers)
        val user = dao.getById(id)

        Assert.assertEquals(fakeUser, user)
    }
}