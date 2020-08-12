package kr.puze.sanitary.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_middle_check.view.*
import kr.puze.sanitary.Data.EndCheckData
import kr.puze.sanitary.Data.MiddleCheckData
import kr.puze.sanitary.R

class MiddleCheckRecyclerAdapter(var items: ArrayList<MiddleCheckData>, var context: Context) : RecyclerView.Adapter<MiddleCheckRecyclerAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_middle_check, null))
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
        private var isDrop = false
        private var endArray: ArrayList<EndCheckData> = ArrayList()
        lateinit var endAdapter: EndCheckRecyclerAdapter

        fun bind(item: MiddleCheckData, position: Int) {
            itemView.text_middle_check.text = "${item.index}.${position + 1} ${item.text}"
            itemView.text_score_middle_check.text = "[배점 ${item.totalScore}점"
            itemView.check_middle_check.setOnClickListener {
                item.noApplicable = itemView.check_middle_check.isChecked
                if(item.noApplicable){
                    itemView.recycler_middle_check.visibility = View.GONE
                }else{
                    itemView.recycler_middle_check.visibility = View.VISIBLE
                }
            }
            itemView.button_middle_check.setOnClickListener {
                isDrop = !isDrop
                if(isDrop){
                    itemView.recycler_middle_check.visibility = View.VISIBLE
                    itemView.button_middle_check.setImageResource(R.drawable.ic_arrow_down)
                }else{
                    itemView.recycler_middle_check.visibility = View.GONE
                    itemView.button_middle_check.setImageResource(R.drawable.ic_arrow_right)
                }
            }
            getEndData(item.endList!!, itemView)
        }

        private fun getEndData(items: ArrayList<EndCheckData>, view: View){
            endArray.clear()
            for (item in items){
                endArray.add(EndCheckData(item.index, item.text, item.score, item.isChecked))
            }
            setRecyclerView(view, endArray)
        }

        private fun setRecyclerView(view: View, endArray: ArrayList<EndCheckData>){
            endAdapter = EndCheckRecyclerAdapter(endArray, context)
            view.recycler_middle_check.adapter = endAdapter
            (view.recycler_middle_check.adapter as EndCheckRecyclerAdapter).notifyDataSetChanged()
            endAdapter.itemClick = object : EndCheckRecyclerAdapter.ItemClick {
                override fun onItemClick(view: View?, position: Int) {
                }
            }
        }
    }

    var itemClick: ItemClick? = null

    interface ItemClick {
        fun onItemClick(view: View?, position: Int)
    }
}
