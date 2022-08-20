package com.david.sciflare.main.ui.example_list

import android.app.Application
import androidx.lifecycle.liveData
import com.data.database.dao.user.UserModelDao
import com.david.support.base_class.BaseViewModel
import com.david.support.utility.threading.runOnAsyncThread
import com.david.support.utility.threading.runOnMainThread
import com.domain.datasources.local.SettingsConfigurationSource
import com.domain.datasources.remote.api.RestService
import com.domain.entity.flickr.UserModelEntity
import com.domain.model.configuration.UserProfile
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class ExampleListViewModel @Inject constructor(application: Application) :
    BaseViewModel(application) {

    @Inject
    lateinit var restDataSource: RestService

    @Inject
    lateinit var userModelDao:UserModelDao

    @Inject
    lateinit var settingsConfigurationSource: SettingsConfigurationSource

    val userProfile: Flow<UserProfile> get() = settingsConfigurationSource.getUserPreference()

    val userModelLiveData get() = userModelDao.userModelLiveData /*listening updates by usermodel livedata*/

    override fun onCreate() {
    }

    /*
    * Execute and retrieve message as livedata value
    *
    * usecase:
    * retrieving and updating room db (sqlite)
    * */
    fun retrieveList() = liveData(Dispatchers.IO) {
        runOnAsyncThread {
            /*Executing in IO Thread*/
            restDataSource.getUserModel().onSuccess {
                val entityList: List<UserModelEntity> = it.map { it.asEntity() }
                userModelDao.insertAll(entityList) /*writing into room(sqlite) db*/

                runOnMainThread {
                    /*Executing in Ui/Main thread*/
                    emit("loaded")
                }
            }.onFailure {
                runOnMainThread {
                    emit(it.localizedMessage)
                }
            }
        }
    }
}