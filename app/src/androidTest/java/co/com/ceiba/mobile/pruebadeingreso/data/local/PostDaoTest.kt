package co.com.ceiba.mobile.pruebadeingreso.data.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import co.com.ceiba.mobile.pruebadeingreso.data.database.AppDataBase
import co.com.ceiba.mobile.pruebadeingreso.factory.generateFakePosts
import co.com.ceiba.mobile.pruebadeingreso.factory.generateFakeUser
import kotlinx.coroutines.runBlocking
import org.junit.*
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@SmallTest
class PostDaoTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: AppDataBase
    private lateinit var postDao: PostDao
    private lateinit var userDao: UserDao

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
                ApplicationProvider.getApplicationContext(),
                AppDataBase::class.java
        ).allowMainThreadQueries().build()

        postDao = database.postDao()
        userDao = database.userDao()
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun givenPosts_whenInsertAndGetThem_thenGetTheSame() = runBlocking {
        val userId = 42L
        // We need to insert a user before inserting post because of foreign key
        val fakeUser = generateFakeUser().copy(id = userId)
        userDao.insert(listOf(fakeUser))

        val fakePosts = generateFakePosts(userId = userId)

        postDao.insert(fakePosts)
        val posts = postDao.getByUserId(userId)

        posts.forEach { post ->
            assert(fakePosts.contains(post))
        }
    }
}