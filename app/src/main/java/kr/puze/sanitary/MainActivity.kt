package kr.puze.sanitary

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import kr.puze.sanitary.Setting.PasswordChangeActivity
import kr.puze.sanitary.Store.CreateStoreActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init(){
        button_add_store_main.setOnClickListener { startActivity(Intent(this@MainActivity, CreateStoreActivity::class.java)) }
        button_setting_main.setOnClickListener { startActivity(Intent(this@MainActivity, SettingActivity::class.java)) }
        getStoreData()
    }

    private fun getStoreData(){
        setStoreRecyclerView()
    }

    private fun setStoreRecyclerView(){

    }
}
