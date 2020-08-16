package kr.puze.sanitary.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_start_check.view.*
import kr.puze.sanitary.Data.EndCheckData
import kr.puze.sanitary.Data.MiddleCheckData
import kr.puze.sanitary.Data.StartCheckData
import kr.puze.sanitary.R

class StartCheckRecyclerAdapter(var items: ArrayList<StartCheckData>, var context: Context) : RecyclerView.Adapter<StartCheckRecyclerAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_start_check, null))
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
        private var middleArray: ArrayList<MiddleCheckData> = ArrayList()
        lateinit var middleAdapter: MiddleCheckRecyclerAdapter

        fun bind(item: StartCheckData, position: Int) {
            itemView.text_start_check.text = "${position + 1}. ${item.text}"
            itemView.button_start_check.setOnClickListener {
                isDrop = !isDrop
                if(isDrop){
                    itemView.recycler_start_check.visibility = View.VISIBLE
                    itemView.button_start_check.setImageResource(R.drawable.ic_arrow_down)
                }else{
                    itemView.recycler_start_check.visibility = View.GONE
                    itemView.button_start_check.setImageResource(R.drawable.ic_arrow_right)
                }
            }
            getMiddleData(position, item.middleList!!, itemView)
        }

        private fun getMiddleData(position: Int, items: ArrayList<MiddleCheckData>, view: View){
            middleArray.clear()
            for (item in items){
                middleArray.add(MiddleCheckData(item.index, item.text, item.totalScore, item.noApplicable, item.endList))
            }
            setRecyclerView(position, view, middleArray)
        }

        private fun setRecyclerView(position: Int, view: View, middleArray: ArrayList<MiddleCheckData>){
            middleAdapter = MiddleCheckRecyclerAdapter(position, middleArray, context)
            view.recycler_start_check.adapter = middleAdapter
            (view.recycler_start_check.adapter as MiddleCheckRecyclerAdapter).notifyDataSetChanged()
            middleAdapter.itemClick = object : MiddleCheckRecyclerAdapter.ItemClick {
                override fun onItemClick(view: View?, position: Int) {
                }
            }
            view.recycler_start_check.visibility = View.GONE
        }
    }

    var itemClick: ItemClick? = null

    interface ItemClick {
        fun onItemClick(view: View?, position: Int)
    }
}
