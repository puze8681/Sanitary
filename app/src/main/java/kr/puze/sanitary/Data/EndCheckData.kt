package kr.puze.sanitary.Data

import android.os.Parcel
import android.os.Parcelable

data class EndCheckData(
    var index: String?,
    var text: String?,
    var score: Int,
    var isChecked: Boolean
) : Parcelable {
    constructor(source: Parcel) : this(
        source.readString(),
        source.readString(),
        source.readInt(),
        1 == source.readInt()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(index)
        writeString(text)
        writeInt(score)
        writeInt((if (isChecked) 1 else 0))
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<EndCheckData> = object : Parcelable.Creator<EndCheckData> {
            override fun createFromParcel(source: Parcel): EndCheckData = EndCheckData(source)
            override fun newArray(size: Int): Array<EndCheckData?> = arrayOfNulls(size)
        }
    }
}