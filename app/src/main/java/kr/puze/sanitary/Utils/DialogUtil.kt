package www.okit.co.Utils

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Dialog
import android.app.ProgressDialog
import android.content.Context
import android.view.Window
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
    fun dialogInfo(info: String) {
        val dialog = Dialog(context)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.activity_splash)
//        dialog.text_dialog_edit_info.text = "$info 개의\n발자국이 확인 되었습니다."
//        dialog.show()
//
//        dialog.button_left_dialog_info.setOnClickListener {
//            dialog.dismiss()
//        }
//        dialog.button_right_dialog_info.setOnClickListener {
//            dialog.dismiss()
//        }
    }
}