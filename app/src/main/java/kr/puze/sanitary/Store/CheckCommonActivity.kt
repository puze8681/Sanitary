package kr.puze.sanitary.Store

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_check_common.*
import kr.puze.sanitary.Data.LogData
import kr.puze.sanitary.Data.StartCheckData
import kr.puze.sanitary.R
import www.okit.co.Utils.DialogUtil
import www.okit.co.Utils.PrefUtil
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.roundToInt

class CheckCommonActivity : AppCompatActivity() {

    companion object{
        var storeId = ""
        var storeTitle = ""
        var score = 0.0
        var startArray = ArrayList<StartCheckData>()
        var addArray = ArrayList<String>()
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
        startArray = intent.getParcelableArrayListExtra("startArray")

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
            setLog(storeTitle, getDate(), submitScore, storeId, startArray)
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

    private fun setLog(title: String, date: String, score: Int, storeId: String, startArray: ArrayList<StartCheckData>){
        val log = LogData(title, date, score, storeId)
        addScore()
        val database: FirebaseDatabase = FirebaseDatabase.getInstance()
        val reference: DatabaseReference = database.getReference("Logs")
        val referenceResult: DatabaseReference = database.getReference("Results")
        val referenceAdd: DatabaseReference = database.getReference("Adds")
        reference.child(prefUtil.userUid).child(storeId).setValue(log)
        referenceResult.child(prefUtil.userUid).child(storeId).setValue(startArray)
        referenceAdd.child(prefUtil.userUid).child(storeId).setValue(addArray)
        reference.child("admin").child(storeId).setValue(log)
        referenceResult.child("admin").child(storeId).setValue(startArray)
        referenceAdd.child("admin").child(storeId).setValue(addArray)
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

    private fun addScore(){
        addArray.clear()
        addScore1()
        addScore2()
        addScore3()
        addScore4()
        addScore5()
        addScore6()
        addScore7()
        addScore8()
        addArray.add("가산점 총점 : ${score()}")
        val submitScore = (score + score()).roundToInt()
        val grade = when (submitScore){
            in 90 .. 100 -> "매우 우수"
            in 85 until 90 -> "우수"
            in 80 until 85 -> "좋음"
            else -> "부적합"
        }
        addArray.add("전체 총점 : $submitScore")
        addArray.add("전체 등급 : $grade")
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

    private fun addScore1(){
        when {
            check_1_1_common.isChecked -> addArray.add(check_1_1_common.text.toString())
            check_1_2_common.isChecked -> addArray.add(check_1_2_common.text.toString())
            check_1_3_common.isChecked -> addArray.add(check_1_3_common.text.toString())
            check_1_4_common.isChecked -> addArray.add(check_1_4_common.text.toString())
        }
    }

    private fun score2(): Double{
        return if(check_2_common.isChecked) 1.0
        else 0.0
    }

    private fun addScore2(){
        when {
            check_2_common.isChecked -> addArray.add(check_2_common.text.toString())
        }
    }

    private fun score3(): Double{
        return when {
            check_3_1_common.isChecked -> 1.0
            check_3_2_common.isChecked -> 0.8
            check_3_3_common.isChecked -> 0.4
            else -> 0.0
        }
    }

    private fun addScore3(){
        when {
            check_3_1_common.isChecked -> addArray.add(check_3_1_common.text.toString())
            check_3_2_common.isChecked -> addArray.add(check_3_2_common.text.toString())
            check_3_3_common.isChecked -> addArray.add(check_3_3_common.text.toString())
        }
    }

    private fun score4(): Double{
        return if(check_4_common.isChecked) 1.0
        else 0.0
    }

    private fun addScore4(){
        when {
            check_4_common.isChecked -> addArray.add(check_4_common.text.toString())
        }
    }

    private fun score5(): Double{
        return if(check_5_common.isChecked) 1.0
        else 0.0
    }

    private fun addScore5(){
        when {
            check_5_common.isChecked -> addArray.add(check_5_common.text.toString())
        }
    }

    private fun score6(): Double{
        return if(check_6_common.isChecked) 1.0
        else 0.0
    }

    private fun addScore6(){
        when {
            check_6_common.isChecked -> addArray.add(check_6_common.text.toString())
        }
    }

    private fun score7(): Double{
        return if(check_7_common.isChecked) -1.0
        else 0.0
    }

    private fun addScore7(){
        when {
            check_7_common.isChecked -> addArray.add(check_7_common.text.toString())
        }
    }

    private fun score8(): Double{
        return if(check_8_common.isChecked) -1.0
        else 0.0
    }

    private fun addScore8(){
        when {
            check_8_common.isChecked -> addArray.add(check_8_common.text.toString())
        }
    }
}