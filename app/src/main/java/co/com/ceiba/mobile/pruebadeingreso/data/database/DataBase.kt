package co.com.ceiba.mobile.pruebadeingreso.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import co.com.ceiba.mobile.pruebadeingreso.data.local.UserDao
import co.com.ceiba.mobile.pruebadeingreso.data.local.model.RoomUser

@Database(
        entities = [
            RoomUser::class
        ],
        version = 1,
        exportSchema = false
)
abstract class DataBase : RoomDatabase() {

    abstract fun userDao(): UserDao
}