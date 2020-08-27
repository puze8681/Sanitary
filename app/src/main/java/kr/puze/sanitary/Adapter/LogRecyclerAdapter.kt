package kr.puze.sanitary.Adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.item_log.view.*
import kr.puze.sanitary.Data.LogData
import kr.puze.sanitary.Data.StartCheckData
import kr.puze.sanitary.R
import org.apache.poi.hssf.usermodel.HSSFWorkbook
import org.apache.poi.ss.usermodel.Row
import org.apache.poi.ss.usermodel.Sheet
import org.apache.poi.ss.usermodel.Workbook
import www.okit.co.Utils.PrefUtil
import www.okit.co.Utils.ToastUtil
import java.io.File
import java.io.FileOutputStream
import java.io.IOException


class LogRecyclerAdapter(var uid: String, var items: ArrayList<LogData>, var context: Context, var prefUtil: PrefUtil) : RecyclerView.Adapter<LogRecyclerAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_log, null))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position], position)
        holder.itemView.setOnClickListener {
            itemClick?.onItemClick(holder.itemView, position)
        }

        holder.itemView.button_delete_log.setOnClickListener {
            val database: FirebaseDatabase = FirebaseDatabase.getInstance()
            val reference: DatabaseReference = database.getReference("Logs")
            reference.child(uid).child(items[position].id!!).ref.removeValue()
            reference.child("admin").child(items[position].id!!).ref.removeValue()
        }

        holder.itemView.button_result_log.setOnClickListener {
            val database: FirebaseDatabase = FirebaseDatabase.getInstance()
            val referenceResult: DatabaseReference = database.getReference("Results")
            var key = if (prefUtil.isAdmin) "admin" else prefUtil.userUid
            var startArray = ArrayList<StartCheckData>()
            referenceResult.child(key).child(items[position].id!!).addValueEventListener(object :
                ValueEventListener {
                override fun onCancelled(p0: DatabaseError) {
                    ToastUtil(context).short("데이터 읽기 실패")
                }

                override fun onDataChange(dataSnapShot: DataSnapshot) {
                    startArray.clear()
                    dataSnapShot.children.forEach{
                        it.getValue(StartCheckData::class.java)?.let { data ->
                            startArray.add(data)
                        }
                    }
                    exportExcel(startArray)
                }
            })
        }
    }

    override fun getItemCount(): Int = items.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val context = itemView.context!!
        fun bind(item: LogData, position: Int) {
            itemView.text_title_log.text = item.title
            itemView.text_date_log.text = item.date
            var fitness = if(item.score >= 80) "적합" else "부적합"
            val grade = when (item.score){
                in 90 .. 100 -> "매우 우수"
                in 85 until 90 -> "우수"
                in 80 until 85 -> "좋음"
                else -> "부적합"
            }
            itemView.text_description_log.text = "$grade / ${item.score} / $fitness"
        }
    }

    var itemClick: ItemClick? = null

    interface ItemClick {
        fun onItemClick(view: View?, position: Int)
    }

    private fun exportExcel(startArray: ArrayList<StartCheckData>){
        val wb: Workbook = HSSFWorkbook()
        val sheet: Sheet = wb.createSheet("식품 위생 관리")
        val row: Row = sheet.createRow(0)
        val cell = row.createCell(0)
        cell.setCellValue(1.0)

        val filename = "test.xls"
        val dir: File =
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS)
        val xls = File(dir, filename)
        try {
            val os = FileOutputStream(xls)
            wb.write(os)
        } catch (e: IOException) {
            e.printStackTrace()
        }

        val uri = Uri.parse("mailto:${prefUtil.userID}")
        val excelIntent = Intent(Intent.ACTION_SENDTO, uri)
        excelIntent.putExtra(Intent.EXTRA_SUBJECT, "excel file email test")
        excelIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse("file://$dir/$filename"))
        context.startActivity(excelIntent)
    }
}
