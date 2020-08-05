package kr.puze.sanitary

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init(){
        button_add_store_main.setOnClickListener {  }
        button_setting_main.setOnClickListener { startActivity(Intent(this@MainActivity, SettingActivity::class.java)) }
    }
}
