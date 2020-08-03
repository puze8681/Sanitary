package kr.puze.sanitary

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        init()
    }

    private fun init(){
        button_register_login.setOnClickListener { register() }
        button_login.setOnClickListener { login(edit_email_login.text.toString(), edit_password_login.text.toString()) }
    }

    private fun register(){
        startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
    }

    private fun login(email: String, password: String){
        startActivity(Intent(this@LoginActivity, MainActivity::class.java))
    }
}