package kr.puze.sanitary.Store

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_check_common.*
import kr.puze.sanitary.Data.LogData
import kr.puze.sanitary.Data.StoreData
import kr.puze.sanitary.R
import www.okit.co.Utils.DialogUtil
import www.okit.co.Utils.PrefUtil
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.roundToInt

class CheckCommonActivity : AppCompatActivity() {

    companion object{
        var storeId = ""
        var storeTitle = ""
        var score = 0.0
        lateinit var prefUtil: PrefUtil
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check_common)
        init()
    }

    private fun init(){
        prefUtil = PrefUtil(this@CheckCommonActivity)
        storeId = intent.getStringExtra("storeId")
        storeTitle = intent.getStringExtra("title")
        score = intent.getDoubleExtra("score", 0.0)

        text_title_check_common.text = storeTitle
        button_back_check_common.setOnClickListener { finish() }

        button_check_common.setOnClickListener {
            val submitScore = (score + score()).roundToInt()
            val grade = when (submitScore){
                in 90 .. 100 -> "매우 우수"
                in 85 until 90 -> "우수"
                in 80 until 85 -> "좋음"
                else -> "부적합"
            }
            setLog(storeTitle, getDate(), submitScore, storeId)
            DialogUtil(this@CheckCommonActivity).dialogSubmit(this@CheckCommonActivity, score, score(), submitScore, grade)
        }

        check_1_1_common.setOnClickListener { check_1_2_common.isChecked = false; check_1_3_common.isChecked = false; check_1_4_common.isChecked = false; }
        check_1_2_common.setOnClickListener { check_1_1_common.isChecked = false; check_1_3_common.isChecked = false; check_1_4_common.isChecked = false; }
        check_1_3_common.setOnClickListener { check_1_2_common.isChecked = false; check_1_1_common.isChecked = false; check_1_4_common.isChecked = false; }
        check_1_4_common.setOnClickListener { check_1_2_common.isChecked = false; check_1_3_common.isChecked = false; check_1_1_common.isChecked = false; }
        check_3_1_common.setOnClickListener { check_3_2_common.isChecked = false; check_3_3_common.isChecked = false; }
        check_3_2_common.setOnClickListener { check_3_1_common.isChecked = false; check_3_3_common.isChecked = false; }
        check_3_3_common.setOnClickListener { check_3_2_common.isChecked = false; check_3_1_common.isChecked = false; }
    }

    private fun setLog(title: String, date: String, score: Int, id: String){
        val log = LogData(title, date, score, id)
        val database: FirebaseDatabase = FirebaseDatabase.getInstance()
        val reference: DatabaseReference = database.getReference("Logs")
        reference.child(prefUtil.userUid).setValue(log)
        finish()
    }

    private fun getDate(): String{
        return SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault()).format(Calendar.getInstance().timeInMillis)
    }

    private fun score(): Double{
        Log.d("LOGTAG/CHECKCOMMON", "${score1()}")
        Log.d("LOGTAG/CHECKCOMMON", "${score2()}")
        Log.d("LOGTAG/CHECKCOMMON", "${score3()}")
        Log.d("LOGTAG/CHECKCOMMON", "${score4()}")
        Log.d("LOGTAG/CHECKCOMMON", "${score5()}")
        Log.d("LOGTAG/CHECKCOMMON", "${score6()}")
        Log.d("LOGTAG/CHECKCOMMON", "${score7()}")
        Log.d("LOGTAG/CHECKCOMMON", "${score8()}")
        return score1() + score2() + score3() + score4() + score5() + score6() + score7() + score8()
    }

    private fun score1(): Double{
        return when {
            check_1_1_common.isChecked -> 2.0
            check_1_2_common.isChecked -> 1.5
            check_1_3_common.isChecked -> 1.0
            check_1_4_common.isChecked -> 0.5
            else -> 0.0
        }
    }

    private fun score2(): Double{
        return if(check_2_common.isChecked) 1.0
        else 0.0
    }

    private fun score3(): Double{
        return when {
            check_3_1_common.isChecked -> 1.0
            check_3_2_common.isChecked -> 0.8
            check_3_3_common.isChecked -> 0.4
            else -> 0.0
        }
    }

    private fun score4(): Double{
        return if(check_4_common.isChecked) 1.0
        else 0.0
    }

    private fun score5(): Double{
        return if(check_5_common.isChecked) 1.0
        else 0.0
    }

    private fun score6(): Double{
        return if(check_6_common.isChecked) 1.0
        else 0.0
    }

    private fun score7(): Double{
        return if(check_7_common.isChecked) -1.0
        else 0.0
    }

    private fun score8(): Double{
        return if(check_8_common.isChecked) -1.0
        else 0.0
    }
}