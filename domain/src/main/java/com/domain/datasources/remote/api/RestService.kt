package com.domain.datasources.remote.api

import com.domain.model.user_data.RequestUserModel
import com.domain.model.user_data.ResponseUserModel
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.PUT


interface RestService {
    /*
    * using custom path @{token}
    * using custom name for path @{sample}
    * adding value to the path @{body}
    * */
    @Headers("Content-Type: application/json")
    @PUT("api/59a94681bbf748b08543d902636bd0a6/sample")
    fun updateUser(@Body body: RequestUserModel): Result<Unit>

    /*
    * using custom path @{token}
    * using custom name for path @{sample}
    * retrieving value from path
    * */
    @GET("api/59a94681bbf748b08543d902636bd0a6/sample")
    suspend fun getUserModel(): Result<ResponseUserModel>
}