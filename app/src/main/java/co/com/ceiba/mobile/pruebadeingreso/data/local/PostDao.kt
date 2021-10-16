package co.com.ceiba.mobile.pruebadeingreso.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import co.com.ceiba.mobile.pruebadeingreso.data.local.model.RoomPost

@Dao
interface PostDao {

    @Query("SELECT * FROM RoomPost WHERE user_id = :userId")
    suspend fun getByUserId(userId: Long): RoomPost?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(post: RoomPost)
}