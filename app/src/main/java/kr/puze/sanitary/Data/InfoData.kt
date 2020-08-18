package kr.puze.sanitary.Data

import android.os.Parcel
import android.os.Parcelable

data class InfoData(
    var title: String?,
    var address: String?,
    var name: String?,
    var id: String?
) : Parcelable {
    constructor(source: Parcel) : this(
        source.readString(),
        source.readString(),
        source.readString(),
        source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(title)
        writeString(address)
        writeString(name)
        writeString(id)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<InfoData> = object : Parcelable.Creator<InfoData> {
            override fun createFromParcel(source: Parcel): InfoData = InfoData(source)
            override fun newArray(size: Int): Array<InfoData?> = arrayOfNulls(size)
        }
    }
}