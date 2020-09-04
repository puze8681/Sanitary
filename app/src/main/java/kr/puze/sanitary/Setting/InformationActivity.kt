package kr.puze.sanitary.Setting

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_information.*
import kr.puze.sanitary.Adapter.InfoRecyclerAdapter
import kr.puze.sanitary.Data.InfoData
import kr.puze.sanitary.Data.StoreData
import kr.puze.sanitary.R
import kr.puze.sanitary.Store.CreateStoreActivity
import kr.puze.sanitary.Utils.SpacesItemDecoration
import www.okit.co.Utils.PrefUtil
import www.okit.co.Utils.ToastUtil

class InformationActivity : AppCompatActivity() {

    companion object{
        val infoArrayList = ArrayList<InfoData>()
        lateinit var infoAdapter: InfoRecyclerAdapter
        lateinit var prefUtil: PrefUtil
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_information)

        //관리자는 전체 평가 기록 확인 가능
        //나머지는 본인의 평가 기록만 확인 가능
        init()
    }

    private fun init(){
        prefUtil = PrefUtil(this@InformationActivity)
        button_create_information.setOnClickListener { startActivity(Intent(this@InformationActivity, CreateStoreActivity::class.java)) }

        if (recycler_info.itemDecorationCount > 0) { recycler_info.removeItemDecorationAt(0) }
        val deco = SpacesItemDecoration(24)
        recycler_info.addItemDecoration(deco)

        getInformationData()
    }

    private fun getInformationData(){
        val database: FirebaseDatabase = FirebaseDatabase.getInstance()
        val reference: DatabaseReference = database.getReference("Stores")
        var key = if (prefUtil.isAdmin) "admin" else prefUtil.userUid
        reference.child(key).addValueEventListener(object :
            ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                ToastUtil(this@InformationActivity).short("데이터 읽기 실패")
                infoArrayList.clear()
                infoArrayList.add(InfoData("매장이름 1", "주소 1", "대표자1", "abc"))
                infoArrayList.add(InfoData("매장이름 2", "주소 2", "대표자2", "bcd"))
                infoArrayList.add(InfoData("매장이름 3", "주소 3", "대표자3", "cde"))
                infoArrayList.add(InfoData("매장이름 4", "주소 4", "대표자4", "def"))
                infoArrayList.add(InfoData("매장이름 5", "주소 5", "대표자5", "efg"))
                setRecyclerView(infoArrayList)
            }

            override fun onDataChange(dataSnapShot: DataSnapshot) {
                infoArrayList.clear()
                dataSnapShot.children.forEach{
                    it.getValue(StoreData::class.java)?.let { data ->
                        infoArrayList.add(InfoData(data.title, data.address, data.name, data.storeId))
                    }
                    setRecyclerView(infoArrayList)
                }
            }
        })
    }
    private fun setRecyclerView(infoArray: ArrayList<InfoData>){
        infoAdapter = InfoRecyclerAdapter(prefUtil.userUid, infoArray, this@InformationActivity)
        recycler_info.adapter = infoAdapter
        (recycler_info.adapter as InfoRecyclerAdapter).notifyDataSetChanged()
        infoAdapter.itemClick = object : InfoRecyclerAdapter.ItemClick {
            override fun onItemClick(view: View?, position: Int) {
            }
        }
    }
}