package www.okit.co.Utils

import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.app.ProgressDialog
import android.content.Context
import android.view.Window
import kotlinx.android.synthetic.main.dialog_no.*
import kr.puze.sanitary.R

@Suppress("DEPRECATION")
class DialogUtil(context: Context) {
    var context: Context = context
    var prefUtil: PrefUtil = PrefUtil(context)
    var toastUtil: ToastUtil

    init {
        prefUtil = PrefUtil(context)
        toastUtil = ToastUtil(context)
    }

    @SuppressLint("SetTextI18n")
    fun dialogNo(activity: Activity) {
        val dialog = Dialog(context)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.dialog_no)
        dialog.button_cancel_no.setOnClickListener { dialog.dismiss() }
        dialog.button_check_no.setOnClickListener {
            dialog.dismiss()
            activity.finish()
        }
        dialog.show()
    }
}