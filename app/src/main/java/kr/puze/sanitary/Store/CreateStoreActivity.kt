package kr.puze.sanitary.Store

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_create_store.*
import kr.puze.sanitary.R
import java.text.SimpleDateFormat
import java.util.*

class CreateStoreActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_store)

        init()
    }

    private fun init(){
        button_back_create.setOnClickListener { finish() }
        button_create.setOnClickListener { createStore(edit_name_create.text.toString(), edit_address_create.text.toString(), edit_phone_create.text.toString(), getDate()) }
    }

    private fun createStore(title: String, address: String, phone: String, date: String){
        finish()
    }

    private fun getDate(): String{
        return SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Calendar.getInstance().timeInMillis)
    }
}