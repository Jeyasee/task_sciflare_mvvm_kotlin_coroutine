package com.domain.model.user_data


import androidx.annotation.Keep
import com.domain.entity.flickr.UserModelEntity

class ResponseUserModel : ArrayList<ResponseUserModel.UserModelItem>(){
    @Keep
    data class UserModelItem(
        var Email: String,
        var Gender: String,
        var Mobile: String,
        var Name: String,
        var _id: String
    ){
        fun asEntity(): UserModelEntity {
            return UserModelEntity().also {
                it.id = this._id
                it.email = this.Email
                it.gender = this.Gender
                it.mobile = this.Mobile
                it.name = this.Name
            }
        }
    }
}