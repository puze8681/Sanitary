package kr.puze.sanitary.Store

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_check.*
import kr.puze.sanitary.Adapter.StartCheckRecyclerAdapter
import kr.puze.sanitary.Data.EndCheckData
import kr.puze.sanitary.Data.MiddleCheckData
import kr.puze.sanitary.Data.StartCheckData
import kr.puze.sanitary.R

class CheckActivity : AppCompatActivity() {

    companion object{
        lateinit var startAdapter: StartCheckRecyclerAdapter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check)

        init()
    }

    private fun init(){
        title = intent.getStringExtra("title")
        text_title_check.text = title
    }
    private fun getEndData(){
        //구조적으로 생각을 해봐야함
        //포문의 시작이 startArray 이고 포문의 심부에 endArray 가 있어야 함
//        var endArray = ArrayList<EndCheckData>()
//        var middleArray = ArrayList<MiddleCheckData>()
        var startArray = ArrayList<StartCheckData>()
//        for (startItem in startArray){
//            startArray.add(startItem)
//        }
//        middleArray.add(MiddleCheckData(item.index, item.text, item.totalScore, item.endList))
        setRecyclerView(startArray)
    }

    private fun setRecyclerView(startArray: ArrayList<StartCheckData>){
        startAdapter = StartCheckRecyclerAdapter(startArray, this@CheckActivity)
        recycler_check.adapter = startAdapter
        (recycler_check.adapter as StartCheckRecyclerAdapter).notifyDataSetChanged()
        startAdapter.itemClick = object : StartCheckRecyclerAdapter.ItemClick {
            override fun onItemClick(view: View?, position: Int) {
            }
        }
    }
}