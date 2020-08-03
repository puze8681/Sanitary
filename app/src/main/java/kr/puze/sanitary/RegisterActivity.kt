package kr.puze.sanitary

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        init()
    }

    private fun init(){
        button_back_register.setOnClickListener { finish() }
        button_register.setOnClickListener { register(edit_email_register.text.toString(), edit_name_register.text.toString(), edit_password_register.text.toString())}
    }

    private fun register(email: String, name: String, password: String){
        startActivity(Intent(this@RegisterActivity, MainActivity::class.java))
    }
}