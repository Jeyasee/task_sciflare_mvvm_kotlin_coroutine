package com.domain.model.user_data


import androidx.annotation.Keep
import com.domain.entity.flickr.UserModelEntity
import java.util.*
import kotlin.random.Random

@Keep
data class RequestUserModel(
    var Email: String,
    var Gender: String,
    var Mobile: String,
    var Name: String
){
    fun asEntity(): UserModelEntity {
        return UserModelEntity().also {
            it.id = UUID.randomUUID().toString()
            it.email = this.Email
            it.gender = this.Gender
            it.mobile = this.Mobile
            it.name = this.Name
        }
    }
}