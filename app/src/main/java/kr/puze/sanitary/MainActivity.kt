package kr.puze.sanitary

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.Gravity
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import coil.ImageLoader
import coil.api.load
import coil.decode.SvgDecoder
import coil.request.LoadRequest
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_drawer.view.*
import kr.puze.sanitary.Adapter.MainRecyclerAdapter
import kr.puze.sanitary.Data.StoreData
import kr.puze.sanitary.Setting.InformationActivity
import kr.puze.sanitary.Setting.TextActivity
import www.okit.co.Utils.PrefUtil
import www.okit.co.Utils.ToastUtil
import java.util.*
import kotlin.collections.ArrayList

@RequiresApi(Build.VERSION_CODES.M)
class MainActivity : AppCompatActivity() {

    companion object{
        val mainArray = ArrayList<StoreData>()
        lateinit var mainAdapter: MainRecyclerAdapter
        lateinit var prefUtil: PrefUtil
        lateinit var firebaseAuth: FirebaseAuth
        private val REQUEST_PERMISSION_CODE = 111
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init(){
        prefUtil = PrefUtil(this@MainActivity)
        firebaseAuth = FirebaseAuth.getInstance()
        checkPermission()
        button_drawer.setOnClickListener {
            drawer_main.openDrawer(Gravity.LEFT)
        }

        image_test.loadSvgOrOthers("https://balzagook.s3.ap-northeast-2.amazonaws.com/stamp/svg/1.svg")

        drawer.button_close_drawer.setOnClickListener { drawer_main.closeDrawers() }
        drawer.button_sanitary_drawer.setOnClickListener { openTextActivity(0) }
        drawer.button_company_drawer.setOnClickListener { openTextActivity(1) }
        drawer.button_consulting_drawer.setOnClickListener { openTextActivity(2) }
        button_main.setOnClickListener { startActivity(Intent(this@MainActivity, InformationActivity::class.java)) }
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

    private fun ImageView.loadSvgOrOthers(myUrl: String?) {
        myUrl?.let {
            if (it.toLowerCase(Locale.ROOT).endsWith("svg")) {
                val imageLoader = ImageLoader.Builder(this.context)
                    .componentRegistry {
                        add(SvgDecoder(this@loadSvgOrOthers.context))
                    }
                    .build()
                val request = LoadRequest.Builder(this.context)
                    .data(it)
                    .target(this)
                    .build()
                imageLoader.execute(request)
            } else {
                this.load(myUrl)
            }
        }
    }


    private fun openTextActivity(type: Int){
        startActivity(Intent(this@MainActivity, TextActivity::class.java).putExtra("type", type))
    }

    private fun logout(){
        ToastUtil(this@MainActivity).short("로그아웃")
        prefUtil.logout()
        firebaseAuth.signOut()
        finishAffinity()
        startActivity(Intent(this@MainActivity, LoginActivity::class.java))
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

    override fun onBackPressed() {
        if(drawer_main.isDrawerOpen(Gravity.LEFT)){
            drawer_main.closeDrawers()
        }else{
            super.onBackPressed()
        }
    }
}
