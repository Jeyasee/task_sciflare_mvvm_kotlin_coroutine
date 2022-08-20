package com.david.sciflare.main.ui.navigation

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
import com.domain.model.user_data.RequestUserModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class NavigationViewModel @Inject constructor(application: Application) :
    BaseViewModel(application) {

    @Inject
    lateinit var restDataSource: RestService

    @Inject
    lateinit var settingsConfigurationSource: SettingsConfigurationSource

    override fun onCreate() {
    }
}