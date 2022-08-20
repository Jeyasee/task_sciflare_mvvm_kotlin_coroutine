package com.david.sciflare.main.ui.example_list

import android.app.Application
import androidx.lifecycle.liveData
import com.data.database.dao.user.UserModelDao
import com.david.support.base_class.BaseViewModel
import com.david.support.utility.threading.runOnAsyncThread
import com.domain.datasources.local.SettingsConfigurationSource
import com.domain.datasources.remote.api.RestService
import com.domain.entity.flickr.UserModelEntity
import com.domain.model.configuration.UserProfile
import com.domain.model.example_list.ExampleApiModel
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

    override fun onCreate() {
    }

    fun retrieveList() = liveData(Dispatchers.IO){
        runOnAsyncThread {
            restDataSource.getUserModel().onSuccess {
                val entityList: List<UserModelEntity> = it.map { it.asEntity() }
                userModelDao.insertAll(entityList)
                emit("loaded")
            }.onFailure {
                emit(it.localizedMessage)
            }
        }
    }

    fun retrieveExampleList(
        callback: (boolean: Boolean, ExampleApiModel?, error: String?) -> Unit, //todo: replace with specific type
    ) {

        /*runOnNewThread {
            showProgressDialog("Loading..")
            try {
                val exampleList = restDataSource.getExampleList()
                runOnUiThread {
                    if (!exampleList.isNullOrEmpty()) {
                        hideProgressDialog()
                        callback(true, exampleList, null) //todo
                    } else {
                        throw IllegalStateException("invalid result")
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
                runOnUiThread {
                    hideProgressDialog()
                    callback(false, null, "retrieving failed ${e.localizedMessage}")
                }
            }
        }*/
    }
}