package kr.puze.sanitary.Setting

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kr.puze.sanitary.R

class InformationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_information)

        //관리자는 전체 평가 기록 확인 가능
        //나머지는 본인의 평가 기록만 확인 가능
    }
}