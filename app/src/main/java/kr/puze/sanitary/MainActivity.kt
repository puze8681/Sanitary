package kr.puze.sanitary

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import kr.puze.sanitary.Adapter.MainRecyclerAdapter
import kr.puze.sanitary.Data.StoreData
import kr.puze.sanitary.Store.CheckActivity
import kr.puze.sanitary.Store.CreateStoreActivity

class MainActivity : AppCompatActivity() {

    companion object{
        val mainArray = ArrayList<StoreData>()
        lateinit var mainAdapter: MainRecyclerAdapter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init(){
        button_add_store_main.setOnClickListener { startActivity(Intent(this@MainActivity, CreateStoreActivity::class.java)) }
        button_setting_main.setOnClickListener { startActivity(Intent(this@MainActivity, SettingActivity::class.java)) }
        getStoreData()
    }

    private fun getStoreData(){
        mainArray.clear()
        mainArray.add(StoreData("매장 이름1", "주소", "010-xxxx-xxxx", "2020.01.01", "store001"))
        mainArray.add(StoreData("매장 이름2", "주소", "010-xxxx-xxxx", "2020.01.01", "store002"))
        mainArray.add(StoreData("매장 이름3", "주소", "010-xxxx-xxxx", "2020.01.01", "store003"))
        setStoreRecyclerView(mainArray)
    }

    private fun setStoreRecyclerView(mainArray: ArrayList<StoreData>){
        mainAdapter = MainRecyclerAdapter(mainArray, this@MainActivity)
        recycler_main.adapter = mainAdapter
        (recycler_main.adapter as MainRecyclerAdapter).notifyDataSetChanged()
        mainAdapter.itemClick = object : MainRecyclerAdapter.ItemClick {
            override fun onItemClick(view: View?, position: Int) {
                startActivity(Intent(this@MainActivity, CheckActivity::class.java).putExtra("title", mainArray[position].title))
            }
        }
    }
}
