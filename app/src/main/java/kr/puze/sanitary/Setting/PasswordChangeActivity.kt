package kr.puze.sanitary.Setting

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_password_change.*
import kr.puze.sanitary.R

class PasswordChangeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_password_change)

        init()
    }

    private fun init(){
        button_password_change.setOnClickListener {
            changePassword(edit_original_password_change.text.toString(), edit_new_password_change.text.toString(), edit_re_password_change.text.toString())
        }
    }

    private fun changePassword(original: String, new: String, re: String){
        if(originalCorrect(original) && reCorrect(new, re)){
            //비밀번호 변경
        }
    }

    private fun originalCorrect(original: String): Boolean{
        //현재 비밀번호 맞는지 확인
        return true
    }

    private fun reCorrect(new: String, re: String): Boolean{
        return new == re
    }
}