package kr.puze.sanitary.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_end_check.view.*
import kr.puze.sanitary.Data.EndCheckData
import kr.puze.sanitary.R
import kr.puze.sanitary.Store.CheckNormalActivity

class EndCheckRecyclerAdapter(var sIndex: Int, var mIndex: Int, var items: ArrayList<EndCheckData>, var context: Context) : RecyclerView.Adapter<EndCheckRecyclerAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_end_check, null))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position], position, sIndex, mIndex)
        holder.itemView.setOnClickListener {
            itemClick?.onItemClick(holder.itemView, position)
        }
    }

    override fun getItemCount(): Int = items.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val context = itemView.context!!

        fun bind(item: EndCheckData, position: Int, sIndex: Int, mIndex: Int) {
            itemView.text_end_check.text = "${item.index}\n${item.text}"
            itemView.check_end_check.text = "[배점 ${item.score}점]"
            itemView.check_end_check.setOnClickListener {
                item.isChecked = itemView.check_end_check.isChecked
                setScore(sIndex, mIndex, position, item.isChecked)
            }
        }

        private fun setScore(sIndex: Int, mIndex: Int, eIndex: Int, isChecked: Boolean){
            CheckNormalActivity().changeEnd(sIndex, mIndex, eIndex, isChecked)
        }
    }

    var itemClick: ItemClick? = null

    interface ItemClick {
        fun onItemClick(view: View?, position: Int)
    }
}
