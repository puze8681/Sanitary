package kr.puze.sanitary.Utils

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by Suleiman on 26-07-2015.
 */
class SpacesItemDecoration(private val mSpace: Int) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        if(parent.getChildAdapterPosition(view) != 0){
            outRect.top = mSpace
        }
    }
}