package kr.puze.sanitary.Data

import android.os.Parcel
import android.os.Parcelable

data class UserData(
    var uid: String?,
    var email: String?,
    var name: String?,
    var isAdmin: Boolean
) : Parcelable {
    constructor(source: Parcel) : this(
        source.readString(),
        source.readString(),
        source.readString(),
        1 == source.readInt()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(uid)
        writeString(email)
        writeString(name)
        writeInt((if (isAdmin) 1 else 0))
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<UserData> = object : Parcelable.Creator<UserData> {
            override fun createFromParcel(source: Parcel): UserData = UserData(source)
            override fun newArray(size: Int): Array<UserData?> = arrayOfNulls(size)
        }
    }
}