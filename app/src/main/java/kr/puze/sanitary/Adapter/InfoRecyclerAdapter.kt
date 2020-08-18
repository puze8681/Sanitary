package kr.puze.sanitary.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_info.view.*
import kr.puze.sanitary.Data.InfoData
import kr.puze.sanitary.R

class InfoRecyclerAdapter(var items: ArrayList<InfoData>, var context: Context) : RecyclerView.Adapter<InfoRecyclerAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_info, null))
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
        fun bind(item: InfoData, position: Int) {
            itemView.text_title_info.text = item.title
            itemView.text_address_info.text = item.address
            itemView.text_name_info.text = item.name
            itemView.button_delete_info.setOnClickListener {  }
            itemView.button_result_info.setOnClickListener {  }
        }
    }

    var itemClick: ItemClick? = null

    interface ItemClick {
        fun onItemClick(view: View?, position: Int)
    }
}
