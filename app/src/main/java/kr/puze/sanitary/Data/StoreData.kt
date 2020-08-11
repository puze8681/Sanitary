package kr.puze.sanitary.Data

import android.os.Parcel
import android.os.Parcelable

data class StoreData(
    var title: String?,
    var address: String?,
    var phone: String?,
    var date: String?,
    var storeId: String?
) : Parcelable {
    constructor(source: Parcel) : this(
        source.readString(),
        source.readString(),
        source.readString(),
        source.readString(),
        source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(title)
        writeString(address)
        writeString(phone)
        writeString(date)
        writeString(storeId)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<StoreData> = object : Parcelable.Creator<StoreData> {
            override fun createFromParcel(source: Parcel): StoreData = StoreData(source)
            override fun newArray(size: Int): Array<StoreData?> = arrayOfNulls(size)
        }
    }
}