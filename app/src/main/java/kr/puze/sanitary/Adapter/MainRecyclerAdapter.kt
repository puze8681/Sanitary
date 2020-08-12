package kr.puze.sanitary.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_main.view.*
import kr.puze.sanitary.Data.StoreData
import kr.puze.sanitary.R
import kr.puze.sanitary.Setting.LogActivity
import kr.puze.sanitary.Store.CreateStoreActivity

class MainRecyclerAdapter(var items: ArrayList<StoreData>, var context: Context) : RecyclerView.Adapter<MainRecyclerAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_main, null))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position], position)
        holder.itemView.setOnClickListener {
            itemClick?.onItemClick(holder.itemView, position)
        }

        holder.itemView.button_result_main.setOnClickListener { context.startActivity(Intent(context.applicationContext, LogActivity::class.java)) }
        holder.itemView.button_check_main.setOnClickListener { context.startActivity(Intent(context.applicationContext, CreateStoreActivity::class.java).putExtra("storeId", items[position].storeId)) }
    }

    override fun getItemCount(): Int = items.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val context = itemView.context!!
        fun bind(item: StoreData, position: Int) {
            itemView.text_title_main.text = item.title
            itemView.text_address_main.text = item.address
            itemView.text_phone_main.text = item.phone
            itemView.text_date_main.text = item.date
        }
    }

    var itemClick: ItemClick? = null

    interface ItemClick {
        fun onItemClick(view: View?, position: Int)
    }
}
