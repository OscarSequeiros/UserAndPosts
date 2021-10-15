package co.com.ceiba.mobile.pruebadeingreso.data.database.di

import co.com.ceiba.mobile.pruebadeingreso.data.database.AppDataBase
import co.com.ceiba.mobile.pruebadeingreso.data.local.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Provides
    fun provideUserDao(appDatabase: AppDataBase): UserDao {
        return appDatabase.userDao()
    }
}