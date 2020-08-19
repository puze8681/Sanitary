package kr.puze.sanitary.Store

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_create_store.*
import kr.puze.sanitary.Data.StoreData
import kr.puze.sanitary.R
import www.okit.co.Utils.PrefUtil
import www.okit.co.Utils.ToastUtil
import java.text.SimpleDateFormat
import java.util.*

class CreateStoreActivity : AppCompatActivity() {

    companion object{
        lateinit var prefUtil: PrefUtil
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_store)

        init()
    }

    private fun init(){
        prefUtil = PrefUtil(this@CreateStoreActivity)
        button_back_create.setOnClickListener { finish() }
        text_date_create.text = getDate()
        button_create.setOnClickListener { createStore(edit_name_create.text.toString(), edit_address_create.text.toString(), edit_phone_create.text.toString(), getDate()) }
    }

    private fun createStore(title: String, address: String, phone: String, date: String){
        if(title.isNotEmpty() && address.isNotEmpty() && phone.isNotEmpty()){
            var storeId = UUID.randomUUID().toString()
            val store = StoreData(title, address, phone, date, storeId)
            val database: FirebaseDatabase = FirebaseDatabase.getInstance()
            val reference: DatabaseReference = database.getReference("Stores")
            reference.child(prefUtil.userUid).child(storeId).setValue(store)
            reference.child("admin").child(storeId).setValue(store)
            finish()
        }else{
            ToastUtil(this@CreateStoreActivity).short("빈칸을 채워주세요.")
        }
    }

    private fun getDate(): String{
        return SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Calendar.getInstance().timeInMillis)
    }
}