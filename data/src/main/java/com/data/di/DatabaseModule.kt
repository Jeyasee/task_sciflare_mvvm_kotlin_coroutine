package com.data.di

import android.content.Context
import com.data.database.RoomManager
import com.data.database.dao.flickr.SampleItemDao
import com.data.database.dao.user.UserModelDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    fun provideSampleDao(appDatabase: RoomManager): SampleItemDao {
        return appDatabase.sampleItemDao
    }

    @Provides
    fun provideUserModelDao(appDatabase: RoomManager): UserModelDao {
        return appDatabase.userModelDao
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): RoomManager {
        return RoomManager.getInstance(appContext)
    }
}