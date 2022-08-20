package com.domain.model.user_data


import androidx.annotation.Keep

class ResponseUserModel : ArrayList<ResponseUserModel.UserModelItem>(){
    @Keep
    data class UserModelItem(
        var Email: String,
        var Gender: String,
        var Mobile: String,
        var Name: String,
        var _id: String
    )
}