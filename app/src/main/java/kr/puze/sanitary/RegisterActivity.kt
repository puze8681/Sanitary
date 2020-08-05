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
        view_agree_register.setOnClickListener { check_register.isChecked = !check_register.isChecked }
        button_back_register.setOnClickListener { finish() }
        button_register.setOnClickListener { register(edit_email_register.text.toString(), edit_name_register.text.toString(), edit_password_register.text.toString(), edit_password_confirm_register.text.toString(), check_register.isChecked)}
    }

    private fun register(email: String, name: String, password: String, passwordConfirm: String, isAdmin: Boolean){
        startActivity(Intent(this@RegisterActivity, MainActivity::class.java))
    }
}