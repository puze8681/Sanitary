package kr.puze.sanitary.Data

import android.os.Parcel
import android.os.Parcelable

data class StartCheckData(
    var text: String?,
    var middleList: ArrayList<MiddleCheckData>?
) : Parcelable {
    constructor(source: Parcel) : this(
        source.readString(),
        source.createTypedArrayList(MiddleCheckData.CREATOR)
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(text)
        writeTypedList(middleList)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<StartCheckData> =
            object : Parcelable.Creator<StartCheckData> {
                override fun createFromParcel(source: Parcel): StartCheckData =
                    StartCheckData(source)

                override fun newArray(size: Int): Array<StartCheckData?> = arrayOfNulls(size)
            }
    }
}