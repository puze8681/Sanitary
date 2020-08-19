package kr.puze.sanitary

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_login.*
import kr.puze.sanitary.Data.UserData
import www.okit.co.Utils.PrefUtil
import www.okit.co.Utils.ToastUtil

class LoginActivity : AppCompatActivity() {

    companion object {
        lateinit var prefUtil: PrefUtil
        lateinit var firebaseAuth: FirebaseAuth
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        init()
    }

    private fun init() {
        prefUtil = PrefUtil(this@LoginActivity)
        firebaseAuth = FirebaseAuth.getInstance()
        button_register_login.setOnClickListener { register() }
        button_login.setOnClickListener {
            login(
                edit_email_login.text.toString(),
                edit_password_login.text.toString()
            )
        }

        if(prefUtil.isLogin) login(prefUtil.userID, prefUtil.userPW)
    }

    private fun register() {
        startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
    }

    private fun login(email: String, password: String) {
        if(email.isNotEmpty() && password.isNotEmpty()){
            firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
                if (it.isSuccessful) {
                    val user = firebaseAuth.currentUser
                    val uid = user!!.uid
                    val database: FirebaseDatabase = FirebaseDatabase.getInstance()
                    val reference: DatabaseReference = database.getReference("Users")
                    reference.child(uid).addValueEventListener(object : ValueEventListener {
                        override fun onCancelled(databaseError: DatabaseError) {
                            Log.d("LOGTAG/LOGIN", "$databaseError")
                            ToastUtil(this@LoginActivity).short("데이터 읽기 실패")
                            prefUtil.logout()
                            firebaseAuth.signOut()
                        }

                        override fun onDataChange(dataSnapShot: DataSnapshot) {
                            val value = dataSnapShot.getValue(UserData::class.java)
                            if(value != null){
                                val name = value.name!!
                                val isAdmin = value.isAdmin
                                prefUtil.userUid = uid
                                prefUtil.userID = email
                                prefUtil.userPW = password
                                prefUtil.userName = name
                                prefUtil.isAdmin = isAdmin
                                prefUtil.isLogin = true

                                ToastUtil(this@LoginActivity).short("로그인 성공")
                                startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                                finishAffinity()
                            }
                        }
                    })
                } else {
                    prefUtil.logout()
                    Log.d("LOGTAG/REGISTER", "${it.exception}")
                    ToastUtil(this@LoginActivity).short("로그인 실패")
                }
            }
        }else{
            ToastUtil(this@LoginActivity).short("빈칸을 채워주세요")
        }
    }
}