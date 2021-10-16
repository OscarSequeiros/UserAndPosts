package co.com.ceiba.mobile.pruebadeingreso.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import co.com.ceiba.mobile.pruebadeingreso.data.local.PostDao
import co.com.ceiba.mobile.pruebadeingreso.data.local.UserDao
import co.com.ceiba.mobile.pruebadeingreso.data.local.model.RoomPost
import co.com.ceiba.mobile.pruebadeingreso.data.local.model.RoomUser

@Database(
        entities = [
            RoomUser::class,
            RoomPost::class,
        ],
        version = 1,
        exportSchema = false
)
abstract class AppDataBase : RoomDatabase() {

    abstract fun userDao(): UserDao

    abstract fun postDao(): PostDao
}