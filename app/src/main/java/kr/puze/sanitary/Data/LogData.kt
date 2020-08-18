package kr.puze.sanitary.Data

import android.os.Parcel
import android.os.Parcelable

data class LogData(
    var title: String?,
    var date: String?,
    var score: Int,
    var id: String?
) : Parcelable {
    constructor(source: Parcel) : this(
        source.readString(),
        source.readString(),
        source.readInt(),
        source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(title)
        writeString(date)
        writeInt(score)
        writeString(id)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<LogData> = object : Parcelable.Creator<LogData> {
            override fun createFromParcel(source: Parcel): LogData = LogData(source)
            override fun newArray(size: Int): Array<LogData?> = arrayOfNulls(size)
        }
    }
}