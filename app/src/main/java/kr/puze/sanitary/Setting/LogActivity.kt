package kr.puze.sanitary.Setting

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_log.*
import kr.puze.sanitary.Adapter.LogRecyclerAdapter
import kr.puze.sanitary.Data.LogData
import kr.puze.sanitary.R
import kr.puze.sanitary.Utils.SpacesItemDecoration
import www.okit.co.Utils.PrefUtil
import www.okit.co.Utils.ToastUtil

class LogActivity : AppCompatActivity() {

    companion object{
        val logArrayList = ArrayList<LogData>()
        lateinit var logAdapter: LogRecyclerAdapter
        lateinit var prefUtil: PrefUtil
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log)

        init()
    }

    private fun init(){
        prefUtil = PrefUtil(this@LogActivity)
        if (recycler_log.itemDecorationCount > 0) { recycler_log.removeItemDecorationAt(0) }
        val deco = SpacesItemDecoration(24)
        recycler_log.addItemDecoration(deco)

        val isSingle = intent.getBooleanExtra("isSingle", false)
        val storeId = intent.getStringExtra("storeId")
        if(isSingle) getSingleLogData(storeId)
        else getLogData()
    }

    private fun getLogData(){
        val database: FirebaseDatabase = FirebaseDatabase.getInstance()
        val reference: DatabaseReference = database.getReference("Logs")
        var key = if (prefUtil.isAdmin) "admin" else prefUtil.userUid
        Log.d("LOGTAG/LOGACTIVITY", "key: $key")
        reference.child(key).addValueEventListener(object :
            ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                ToastUtil(this@LogActivity).short("데이터 읽기 실패")
                logArrayList.clear()
                logArrayList.add(LogData("매장이름 1", "2020.01.01", 100,"abc"))
                logArrayList.add(LogData("매장이름 2", "2020.02.01", 90,"bcd"))
                logArrayList.add(LogData("매장이름 3", "2020.03.01", 80,"cde"))
                logArrayList.add(LogData("매장이름 4", "2020.04.01", 70,"def"))
                logArrayList.add(LogData("매장이름 5", "2020.05.01", 60,"efg"))
                setRecyclerView(logArrayList)
            }

            override fun onDataChange(dataSnapShot: DataSnapshot) {
                logArrayList.clear()
                Log.d("LOGTAG/LOGACTIVITY", "dataSnapShot: ${dataSnapShot.value}")
                dataSnapShot.children.forEach{
                    it.getValue(LogData::class.java)?.let { data ->
                        Log.d("LOGTAG/LOGACTIVITY", "data: $data")
                        logArrayList.add(LogData(data.title, data.date, data.score, data.id))
                    }
                }
                Log.d("LOGTAG/LOGACTIVITY", "logArrayList: $logArrayList")
                setRecyclerView(logArrayList)
            }
        })
    }

    private fun getSingleLogData(storeId: String){
        val database: FirebaseDatabase = FirebaseDatabase.getInstance()
        val reference: DatabaseReference = database.getReference("Logs")
        var key = if (prefUtil.isAdmin) "admin" else prefUtil.userUid
        reference.child(key).child(storeId).addValueEventListener(object :
            ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                ToastUtil(this@LogActivity).short("데이터 읽기 실패")
                logArrayList.clear()
                logArrayList.add(LogData("매장이름 1", "2020.01.01", 100,"abc"))
                logArrayList.add(LogData("매장이름 2", "2020.02.01", 90,"bcd"))
                logArrayList.add(LogData("매장이름 3", "2020.03.01", 80,"cde"))
                logArrayList.add(LogData("매장이름 4", "2020.04.01", 70,"def"))
                logArrayList.add(LogData("매장이름 5", "2020.05.01", 60,"efg"))
                setRecyclerView(logArrayList)
            }

            override fun onDataChange(dataSnapShot: DataSnapshot) {
                logArrayList.clear()
                val data = dataSnapShot.getValue(LogData::class.java)
                if(data != null){
                    logArrayList.add(data)
                }
                setRecyclerView(logArrayList)
            }
        })
    }
    private fun setRecyclerView(logArrayList: ArrayList<LogData>){
        logAdapter = LogRecyclerAdapter(prefUtil.userUid, logArrayList, this@LogActivity, prefUtil)
        recycler_log.adapter = logAdapter
        (recycler_log.adapter as LogRecyclerAdapter).notifyDataSetChanged()
        logAdapter.itemClick = object : LogRecyclerAdapter.ItemClick {
            override fun onItemClick(view: View?, position: Int) {
            }
        }
    }
}