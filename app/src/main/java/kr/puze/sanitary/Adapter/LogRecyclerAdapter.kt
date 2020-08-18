package kr.puze.sanitary.Adapter

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_log.view.*
import kr.puze.sanitary.Data.LogData
import kr.puze.sanitary.R

class LogRecyclerAdapter(var items: ArrayList<LogData>, var context: Context) : RecyclerView.Adapter<LogRecyclerAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_log, null))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position], position)
        holder.itemView.setOnClickListener {
            itemClick?.onItemClick(holder.itemView, position)
        }
    }

    override fun getItemCount(): Int = items.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val context = itemView.context!!
        fun bind(item: LogData, position: Int) {
            itemView.text_title_log.text = item.title
            itemView.text_date_log.text = item.date
            var fitness = if(item.score >= 80) "적합" else "부적합"
            itemView.text_description_log.text = "${item.grade} / ${item.score} / $fitness"
            itemView.button_delete_log.setOnClickListener {  }
            itemView.button_result_log.setOnClickListener {  }
        }
    }

    var itemClick: ItemClick? = null

    interface ItemClick {
        fun onItemClick(view: View?, position: Int)
    }
}
