package kr.puze.sanitary.Setting

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_text.*
import kr.puze.sanitary.R

class TextActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_text)

        init()
    }

    private fun init(){
        var type = intent.getIntExtra("type", -1)
        when(type){
            0->{
                offView()
                view_sanitary_text.visibility = View.VISIBLE
                text_title_text.text = "위생등급제 소개"
            }
            1->{
                offView()
                view_company_text.visibility = View.VISIBLE
                text_title_text.text = "회사 소개"
            }
            2->{
                offView()
                view_consulting_text.visibility = View.VISIBLE
                text_title_text.text = "위생등급제 컨설팅"
            }
            else->{
                finish()
            }
        }
    }

    private fun offView(){
        view_sanitary_text.visibility = View.GONE
        view_company_text.visibility = View.GONE
        view_consulting_text.visibility = View.GONE
    }
}