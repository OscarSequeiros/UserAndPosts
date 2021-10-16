package co.com.ceiba.mobile.pruebadeingreso.data.database.di

import android.content.Context
import androidx.room.Room
import co.com.ceiba.mobile.pruebadeingreso.data.database.AppDataBase
import co.com.ceiba.mobile.pruebadeingreso.data.local.PostDao
import co.com.ceiba.mobile.pruebadeingreso.data.local.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Provides
    fun provideUserDao(appDatabase: AppDataBase): UserDao {
        return appDatabase.userDao()
    }

    @Provides
    fun providePostDao(appDatabase: AppDataBase): PostDao {
        return appDatabase.postDao()
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDataBase {
        return Room.databaseBuilder(
                appContext,
                AppDataBase::class.java,
                DATABASE_NAME
        ).build()

    }

    companion object {
        const val DATABASE_NAME = "app_database"
    }
}