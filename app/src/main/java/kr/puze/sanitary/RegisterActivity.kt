package kr.puze.sanitary

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_register.*
import kr.puze.sanitary.Data.UserData
import www.okit.co.Utils.PrefUtil
import www.okit.co.Utils.ToastUtil

class RegisterActivity : AppCompatActivity() {

    companion object{
        lateinit var prefUtil: PrefUtil
        lateinit var firebaseAuth: FirebaseAuth
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        init()
    }

    private fun init(){
        prefUtil = PrefUtil(this@RegisterActivity)
        firebaseAuth = FirebaseAuth.getInstance()
        view_agree_register.setOnClickListener { check_register.isChecked = !check_register.isChecked }
        view_privacy_register.setOnClickListener { check_privacy_register.isChecked = !check_privacy_register.isChecked }
        button_back_register.setOnClickListener { finish() }
        text_privacy_register.setOnClickListener {
            val uri: Uri = Uri.parse("https://drive.google.com/file/d/1o2_-EfQ54TWBqPnSMJrCdK_h6L6MWhWo/view?usp=sharing")
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }
        button_register.setOnClickListener { register(edit_email_register.text.toString(), edit_name_register.text.toString(), edit_password_register.text.toString(), edit_password_confirm_register.text.toString(), check_register.isChecked)}
    }

    private fun register(email: String, name: String, password: String, passwordConfirm: String, isAdmin: Boolean){
        if(email.isNotEmpty() && name.isNotEmpty() && password.isNotEmpty() && passwordConfirm.isNotEmpty()){
            if(check_privacy_register.isChecked){
                if(password == passwordConfirm){
                    firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
                        if(it.isSuccessful){
                            val user= firebaseAuth.currentUser
                            var uid = user!!.uid
                            var userData = UserData(uid, user.email!!, name, isAdmin)
                            var database: FirebaseDatabase = FirebaseDatabase.getInstance()
                            var reference: DatabaseReference = database.getReference("Users")
                            reference.child(uid).setValue(userData)
                            prefUtil.userUid = uid
                            prefUtil.userID = email
                            prefUtil.userPW = password
                            prefUtil.userName = name
                            prefUtil.isAdmin = isAdmin
                            prefUtil.isLogin = true
                            ToastUtil(this@RegisterActivity).short("회원가입 성공")
                            startActivity(Intent(this@RegisterActivity, MainActivity::class.java))
                            finishAffinity()
                        }else{
                            prefUtil.logout()
                            ToastUtil(this@RegisterActivity).short("회원가입 실패")
                            Log.d("LOGTAG/REGISTER", "${it.exception}")
                        }
                    }
                }else{
                    prefUtil.logout()
                    ToastUtil(this@RegisterActivity).short("비밀번호와 비밀번호 확인란이 일치하지 않습니다.")
                }
            }else{
                ToastUtil(this@RegisterActivity).short("개인정보동의서를 확인해주세요")
            }
        }else{
            ToastUtil(this@RegisterActivity).short("빈칸을 채워주세요")
        }
    }
}