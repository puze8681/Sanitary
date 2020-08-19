package kr.puze.sanitary.Adapter

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.item_log.view.*
import kr.puze.sanitary.Data.LogData
import kr.puze.sanitary.R
import kr.puze.sanitary.Store.CheckCommonActivity
import kotlin.math.roundToInt

class LogRecyclerAdapter(var uid: String, var items: ArrayList<LogData>, var context: Context) : RecyclerView.Adapter<LogRecyclerAdapter.ViewHolder>() {
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
            itemView.button_delete_log.setOnClickListener {  }
            itemView.button_result_log.setOnClickListener {  }
        }
    }

    var itemClick: ItemClick? = null

    interface ItemClick {
        fun onItemClick(view: View?, position: Int)
    }
}
