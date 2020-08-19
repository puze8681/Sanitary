package kr.puze.sanitary

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_main.*
import kr.puze.sanitary.Adapter.MainRecyclerAdapter
import kr.puze.sanitary.Data.StoreData
import kr.puze.sanitary.Store.CreateStoreActivity
import www.okit.co.Utils.PrefUtil
import www.okit.co.Utils.ToastUtil

class MainActivity : AppCompatActivity() {

    companion object{
        val mainArray = ArrayList<StoreData>()
        lateinit var mainAdapter: MainRecyclerAdapter
        lateinit var prefUtil: PrefUtil
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init(){
        prefUtil = PrefUtil(this@MainActivity)
        setCount(mainArray.size)
        button_add_store_main.setOnClickListener { startActivity(Intent(this@MainActivity, CreateStoreActivity::class.java)) }
        button_setting_main.setOnClickListener { startActivity(Intent(this@MainActivity, SettingActivity::class.java)) }
        getStoreData()
    }

    private fun setCount(count: Int){
        text_count_main.text = "전체 ${count}개의 점검내역이 있습니다."
    }

    private fun getStoreData(){
        val database: FirebaseDatabase = FirebaseDatabase.getInstance()
        val reference: DatabaseReference = database.getReference("Stores")
        reference.child(prefUtil.userUid).addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                ToastUtil(this@MainActivity).short("데이터 읽기 실패")
                mainArray.clear()
                mainArray.add(StoreData("매장 이름1", "주소", "010-xxxx-xxxx", "2020.01.01", "store001"))
                mainArray.add(StoreData("매장 이름2", "주소", "010-xxxx-xxxx", "2020.01.01", "store002"))
                mainArray.add(StoreData("매장 이름3", "주소", "010-xxxx-xxxx", "2020.01.01", "store003"))
                setStoreRecyclerView(mainArray)
            }

            override fun onDataChange(dataSnapShot: DataSnapshot) {
                mainArray.clear()
                dataSnapShot.children.forEach{
                    it.getValue(StoreData::class.java)?.let { data ->
                        mainArray.add(data)
                    }
                    setStoreRecyclerView(mainArray)
                }
            }
        })
    }

    private fun setStoreRecyclerView(mainArray: ArrayList<StoreData>){
        setCount(mainArray.size)
        mainAdapter = MainRecyclerAdapter(mainArray, this@MainActivity)
        recycler_main.adapter = mainAdapter
        (recycler_main.adapter as MainRecyclerAdapter).notifyDataSetChanged()
        mainAdapter.itemClick = object : MainRecyclerAdapter.ItemClick {
            override fun onItemClick(view: View?, position: Int) {
            }
        }
    }
}
