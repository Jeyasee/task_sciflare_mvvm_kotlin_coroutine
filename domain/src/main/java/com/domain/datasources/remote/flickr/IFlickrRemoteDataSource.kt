package com.domain.datasources.remote.flickr

import androidx.paging.PagingData
import com.domain.entity.flickr.SampleEntity
import kotlinx.coroutines.flow.Flow

interface IFlickrRemoteDataSource {
    suspend fun getFlickrDataSync(page: Int): Map<String, Any>
    fun provideFlickrPagination(): Flow<PagingData<SampleEntity>>
}