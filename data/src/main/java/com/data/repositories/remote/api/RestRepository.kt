package com.data.repositories.remote.api

import com.domain.datasources.remote.api.RestService
import com.domain.model.example_list.ExampleApiModel
import com.domain.model.update_profile.UpdateProfileModel
import com.domain.model.user_data.RequestUserModel
import com.domain.model.user_data.ResponseAddModel
import com.domain.model.user_data.ResponseUserModel
import javax.inject.Inject

class RestRepository @Inject constructor(private val restService: RestService) :RestService {
    override fun updateUser(body: RequestUserModel): Result<ResponseAddModel> {
        return restService.updateUser(body)
    }

    override suspend fun getUserModel(): Result<ResponseUserModel> {
        return restService.getUserModel()
    }
}