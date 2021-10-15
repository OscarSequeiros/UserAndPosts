package co.com.ceiba.mobile.pruebadeingreso.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import co.com.ceiba.mobile.pruebadeingreso.data.local.model.RoomUser
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Query("SELECT * FROM RoomUser")
    fun getAll(): Flow<List<RoomUser>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(users: List<RoomUser>)
}