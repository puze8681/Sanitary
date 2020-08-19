package kr.puze.sanitary.Setting

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_password_change.*
import kr.puze.sanitary.R
import www.okit.co.Utils.PrefUtil
import www.okit.co.Utils.ToastUtil

class PasswordChangeActivity : AppCompatActivity() {

    companion object {
        lateinit var prefUtil: PrefUtil
        lateinit var firebaseAuth: FirebaseAuth
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_password_change)

        init()
    }

    private fun init(){
        prefUtil = PrefUtil(this@PasswordChangeActivity)
        firebaseAuth = FirebaseAuth.getInstance()
        button_password_change.setOnClickListener {
            changePassword(edit_original_password_change.text.toString(), edit_new_password_change.text.toString(), edit_re_password_change.text.toString())
        }
    }

    private fun changePassword(original: String, new: String, re: String){
        if(originalCorrect(original) && reCorrect(new, re)){
            //비밀번호 변경
            val user = firebaseAuth.currentUser
            user?.updatePassword(new)?.addOnCompleteListener {
                if(it.isSuccessful){
                    val uid = user.uid
                    var hashMap: HashMap<Any, Any> = HashMap()
                    hashMap.put("uid", uid)
                    hashMap.put("email", user.email!!)
                    hashMap.put("name", prefUtil.userName)
                    hashMap.put("isAdmin", prefUtil.isAdmin)
                    var database: FirebaseDatabase = FirebaseDatabase.getInstance()
                    var reference: DatabaseReference = database.getReference("Users")
                    reference.child(uid).setValue(hashMap)
                    prefUtil.userPW = new
                    prefUtil.userUid = uid
                    ToastUtil(this@PasswordChangeActivity).short("비밀번호 변경 성공")
                }else{
                    ToastUtil(this@PasswordChangeActivity).short("비밀번호 변경 실패")
                }
            }
        }
    }

    private fun originalCorrect(original: String): Boolean{
        return original == prefUtil.userPW
    }

    private fun reCorrect(new: String, re: String): Boolean{
        return new == re
    }
}