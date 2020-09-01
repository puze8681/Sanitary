package www.okit.co.Utils

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.Window
import androidx.core.content.ContextCompat.startActivity
import kotlinx.android.synthetic.main.dialog_no.*
import kotlinx.android.synthetic.main.dialog_submit.*
import kr.puze.sanitary.MainActivity
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

    fun dialogSubmit(activity: Activity, normal: Double, common: Double, submit: Int, grade: String) {
        val dialog = Dialog(context)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.dialog_submit)
        val convertNormal: Double = String.format("%.1f", normal).toDouble()
        dialog.text_submit.text = "일반분야 점수: ${convertNormal}점\n공통분야 점수: ${common}점\n점수합계: ${submit}점\n위생등급: $grade"
        dialog.button_submit.setOnClickListener {
            dialog.dismiss()
            activity.finishAffinity()
            activity.startActivity(Intent(activity, MainActivity::class.java))
        }
        dialog.image_consulting_submit.setOnClickListener {
            val uri: Uri = Uri.parse("https://form.office.naver.com/form/responseView.cmd?formkey=ZTFhZTIwMDItNWUxZS00OTcwLWI3ZTAtMzBmZDM0MmNmMWY3&sourceId=mail&sf=f")
            val intent = Intent(Intent.ACTION_VIEW, uri)
            activity.startActivity(intent)
        }
        dialog.show()
    }
}