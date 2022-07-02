package com.example.aplicacionteniente.data

import android.content.Context
import androidx.room.Room
import com.google.android.datatransport.runtime.dagger.Module
import com.google.android.datatransport.runtime.dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

class AppModule {
    @Module
    @InstallIn(SingletonComponent::class)
    class AppModule {
        @Provides
        fun provideBookDb(
            @ApplicationContext
            context : Context
        ) = Room.databaseBuilder(
            context,
            Userdatabase::class.java,
            "user_table"
        ).build()

        @Provides
        fun userDao(
            userbd: Userdatabase
        ) = userbd.userDao()

        @Provides
        fun provideUserRepository(
            userDao: UserDao
        ): UserRepository = UserRepositoryImpl(userDao)
    }




}