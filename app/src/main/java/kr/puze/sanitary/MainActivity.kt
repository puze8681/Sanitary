package kr.puze.sanitary

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_main.*
import kr.puze.sanitary.Adapter.MainRecyclerAdapter
import kr.puze.sanitary.Data.StoreData
import kr.puze.sanitary.Setting.InformationActivity
import kr.puze.sanitary.Store.CreateStoreActivity
import www.okit.co.Utils.PrefUtil
import www.okit.co.Utils.ToastUtil

@RequiresApi(Build.VERSION_CODES.M)
class MainActivity : AppCompatActivity() {

    companion object{
        val mainArray = ArrayList<StoreData>()
        lateinit var mainAdapter: MainRecyclerAdapter
        lateinit var prefUtil: PrefUtil
        private val REQUEST_PERMISSION_CODE = 111
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init(){
        prefUtil = PrefUtil(this@MainActivity)
        checkPermission()
        button_main.setOnClickListener { startActivity(Intent(this@MainActivity, InformationActivity::class.java)) }
        button_add_store_main.setOnClickListener { startActivity(Intent(this@MainActivity, CreateStoreActivity::class.java)) }
        button_setting_main.setOnClickListener { startActivity(Intent(this@MainActivity, SettingActivity::class.java)) }
        button_link_main.setOnClickListener {
            val uri: Uri = Uri.parse("https://blog.naver.com/hws4389")
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }
        button_phone_main.setOnClickListener {
            var uri: Uri = Uri.parse("tel:0269490226")
            var intent = Intent(Intent.ACTION_DIAL, uri)
            startActivity(intent)
        }
    }

    private fun checkPermission() {
        if ( checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            if (shouldShowRequestPermissionRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE)) { Toast.makeText(this@MainActivity, "엑셀 파일을 추출하기 위해 권한을 허용해주세요.", Toast.LENGTH_SHORT).show() }
            requestPermissions(arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                REQUEST_PERMISSION_CODE
            )
        }
    }

    //카메라 촬영, 촬영한 이미지 저장 기능은 주석처리함
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            REQUEST_PERMISSION_CODE -> if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            } else {
                Toast.makeText(this@MainActivity, "엑셀 파일을 추출하기 위해 권한을 허용해주세요.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
