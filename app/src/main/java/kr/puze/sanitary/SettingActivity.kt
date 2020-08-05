package kr.puze.sanitary

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_setting.*

class SettingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)
        init()
    }

    private fun init(){
        button_password_setting.setOnClickListener {  }
        button_information_setting.setOnClickListener {  }
        button_log_setting.setOnClickListener {  }
        button_logout_setting.setOnClickListener {  }
    }
}