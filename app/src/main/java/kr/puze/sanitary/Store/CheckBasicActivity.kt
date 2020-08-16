package kr.puze.sanitary.Store

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_check_basic.*
import kr.puze.sanitary.R
import www.okit.co.Utils.DialogUtil

class CheckBasicActivity : AppCompatActivity() {

    companion object{
        var nowPosition = 0
        var storeId = ""
        var storeTitle = ""
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check_basic)
        init()
    }

    private fun init(){
        nowPosition = 0
        storeId = intent.getStringExtra("storeId")
        storeTitle = intent.getStringExtra("title")
        text_title_check_basic.text = storeTitle
        button_back_check_basic.setOnClickListener { finish() }

        setView()

        button_no_check_basic.setOnClickListener { no() }
        button_yes_check_basic.setOnClickListener { yes() }
    }

    private fun no(){
        DialogUtil(this@CheckBasicActivity).dialogNo(this@CheckBasicActivity)
    }

    private fun yes(){
        if(nowPosition == 9){
            startActivity(Intent(this@CheckBasicActivity, CheckNormalActivity::class.java).putExtra("storeId", storeId).putExtra("title", storeTitle))
        }else{
            nowPosition+=1
            setView()
        }
    }

    private fun setView(){
        val items = resources.getStringArray(R.array.basic)
        val descriptionItems = resources.getStringArray(R.array.basic_description)
        text_item_check_basic.text = "${items[nowPosition]} (${nowPosition+1}/${items.size})"
        text_description_check_basic.text = descriptionItems[nowPosition]
    }
}