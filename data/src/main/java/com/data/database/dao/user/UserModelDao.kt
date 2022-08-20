package com.data.database.dao.user

import androidx.lifecycle.LiveData
import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import com.domain.entity.flickr.SampleEntity
import com.david.support.base_class.room.BaseDao
import com.domain.entity.flickr.UserModelEntity

@Dao
abstract class UserModelDao : BaseDao<UserModelEntity>() {

    @get:Query("SELECT * FROM " + UserModelEntity.TABLE_NAME)
    abstract val userModelLiveData: LiveData<List<UserModelEntity>> /*retrieve database changes as callback*/

    @get:Query("SELECT COUNT(id) FROM " + UserModelEntity.TABLE_NAME)
    abstract val getCountOfItems: Long

    @Query("SELECT * FROM tbl_sample")
    abstract fun getPaging(): PagingSource<Int, UserModelEntity>

    @Query("DELETE FROM tbl_sample")
    abstract fun clearAll()
}
