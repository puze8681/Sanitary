package kr.puze.sanitary

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_setting.*
import kr.puze.sanitary.Setting.InformationActivity
import kr.puze.sanitary.Setting.LogActivity
import kr.puze.sanitary.Setting.PasswordChangeActivity
import kr.puze.sanitary.Setting.TextActivity
import www.okit.co.Utils.PrefUtil
import www.okit.co.Utils.ToastUtil

class SettingActivity : AppCompatActivity() {

    companion object{
        lateinit var prefUtil: PrefUtil
        lateinit var firebaseAuth: FirebaseAuth
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)
        init()
    }

    private fun init(){
        prefUtil = PrefUtil(this@SettingActivity)
        firebaseAuth = FirebaseAuth.getInstance()

        button_password_setting.setOnClickListener { startActivity(Intent(this@SettingActivity, PasswordChangeActivity::class.java)) }
        button_information_setting.setOnClickListener { startActivity(Intent(this@SettingActivity, InformationActivity::class.java)) }
        button_log_setting.setOnClickListener { startActivity(Intent(this@SettingActivity, LogActivity::class.java)) }
        button_logout_setting.setOnClickListener { logout() }
    }

    private fun openTextActivity(type: Int){
        startActivity(Intent(this@SettingActivity, TextActivity::class.java).putExtra("type", type))
    }

    private fun logout(){
        ToastUtil(this@SettingActivity).short("로그아웃")
        prefUtil.logout()
        firebaseAuth.signOut()
        finishAffinity()
        startActivity(Intent(this@SettingActivity, LoginActivity::class.java))
    }
}