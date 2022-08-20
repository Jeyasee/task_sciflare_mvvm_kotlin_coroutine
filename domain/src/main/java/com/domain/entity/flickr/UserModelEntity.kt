package com.domain.entity.flickr

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.david.support.base_class.room.BaseEntity

/*
* [Please maintain table name, column format]
* TableName = "tbl_title"
* ColumnName = "title_etc"
*/
@Entity(tableName = SampleEntity.TABLE_NAME)
open class UserModelEntity : BaseEntity() {

    companion object {
        const val TABLE_NAME = "tbl_user"
    }

    object Fields {
        const val ID = "_Id"
        const val NAME = "Name"
        const val EMAIL = "Email"
        const val MOBILE = "Mobile"
        const val GENDER = "Gender"
    }

    @PrimaryKey
    @SerializedName(Fields.ID)
    @ColumnInfo(name = Fields.ID)
    var id: String = ""

    @SerializedName(Fields.NAME)
    @ColumnInfo(name = Fields.NAME)
    var name: String = ""

    @SerializedName(Fields.EMAIL)
    @ColumnInfo(name = Fields.EMAIL)
    var email: String = ""

    @SerializedName(Fields.MOBILE)
    @ColumnInfo(name = Fields.MOBILE)
    var mobile: String = ""

    @SerializedName(Fields.GENDER)
    @ColumnInfo(name = Fields.GENDER)
    var gender: String = ""
}