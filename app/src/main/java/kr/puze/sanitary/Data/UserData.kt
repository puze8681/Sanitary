package kr.puze.sanitary.Data

import android.os.Parcel
import android.os.Parcelable

data class UserData(
    var uid: String? = "",
    var email: String? = "",
    var name: String? = "",
    var isAdmin: Boolean = false
)