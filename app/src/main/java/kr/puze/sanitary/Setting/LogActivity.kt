package kr.puze.sanitary.Setting

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_log.*
import kr.puze.sanitary.Adapter.LogRecyclerAdapter
import kr.puze.sanitary.Data.LogData
import kr.puze.sanitary.R
import kr.puze.sanitary.Utils.SpacesItemDecoration

class LogActivity : AppCompatActivity() {

    companion object{
        val logArrayList = ArrayList<LogData>()
        lateinit var logAdapter: LogRecyclerAdapter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log)

        init()
    }

    private fun init(){
        if (recycler_log.itemDecorationCount > 0) { recycler_log.removeItemDecorationAt(0) }
        val deco = SpacesItemDecoration(24)
        recycler_log.addItemDecoration(deco)

        getLogData()
    }

    private fun getLogData(){
        logArrayList.clear()
        logArrayList.add(LogData("매장이름 1", "2020.01.01", 100,"abc"))
        logArrayList.add(LogData("매장이름 2", "2020.02.01", 90,"bcd"))
        logArrayList.add(LogData("매장이름 3", "2020.03.01", 80,"cde"))
        logArrayList.add(LogData("매장이름 4", "2020.04.01", 70,"def"))
        logArrayList.add(LogData("매장이름 5", "2020.05.01", 60,"efg"))
        setRecyclerView(logArrayList)
    }
    private fun setRecyclerView(logArrayList: ArrayList<LogData>){
        logAdapter = LogRecyclerAdapter(logArrayList, this@LogActivity)
        recycler_log.adapter = logAdapter
        (recycler_log.adapter as LogRecyclerAdapter).notifyDataSetChanged()
        logAdapter.itemClick = object : LogRecyclerAdapter.ItemClick {
            override fun onItemClick(view: View?, position: Int) {
            }
        }
    }
}