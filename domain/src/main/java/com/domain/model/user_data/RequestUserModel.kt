package com.domain.model.user_data


import androidx.annotation.Keep

@Keep
data class RequestUserModel(
    var Email: String,
    var Gender: String,
    var Mobile: String,
    var Name: String
)