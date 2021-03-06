package kr.puze.sanitary.Adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.item_info.view.*
import kr.puze.sanitary.Data.InfoData
import kr.puze.sanitary.R
import kr.puze.sanitary.Setting.InformationActivity
import kr.puze.sanitary.Store.CheckBasicActivity

class InfoRecyclerAdapter(var uid: String, var items: ArrayList<InfoData>, var context: Context) : RecyclerView.Adapter<InfoRecyclerAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_info, null))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position], position)
        holder.itemView.setOnClickListener {
            itemClick?.onItemClick(holder.itemView, position)
        }

        holder.itemView.text_phone_info.setOnClickListener {
            if(items[position].phone != ""){
                var uri: Uri = Uri.parse("tel:${items[position].phone}")
                var intent = Intent(Intent.ACTION_DIAL, uri)
                context.startActivity(intent)
            }
        }

        holder.itemView.button_delete_info.setOnClickListener {
            val database: FirebaseDatabase = FirebaseDatabase.getInstance()
            val reference: DatabaseReference = database.getReference("Stores")
            reference.child(uid).child(items[position].id!!).ref.removeValue()
        }
        holder.itemView.button_result_info.setOnClickListener {
            context.startActivity(Intent(context.applicationContext, CheckBasicActivity::class.java).putExtra("storeId", items[position].id).putExtra("title", items[position].title))
        }
    }

    override fun getItemCount(): Int = items.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val context = itemView.context!!
        fun bind(item: InfoData, position: Int) {
            itemView.text_title_info.text = item.title
            itemView.text_address_info.text = item.address
            itemView.text_name_info.text = item.name
            itemView.button_delete_info.setOnClickListener {
            }
            itemView.button_result_info.setOnClickListener {  }
        }
    }

    var itemClick: ItemClick? = null

    interface ItemClick {
        fun onItemClick(view: View?, position: Int)
    }
}
