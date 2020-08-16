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
import kr.puze.sanitary.Store.CheckNormalActivity

class MiddleCheckRecyclerAdapter(var sIndex: Int, var items: ArrayList<MiddleCheckData>, var context: Context) : RecyclerView.Adapter<MiddleCheckRecyclerAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_middle_check, null))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position], position, sIndex)
        holder.itemView.setOnClickListener {
            itemClick?.onItemClick(holder.itemView, position)
        }
    }

    override fun getItemCount(): Int = items.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val context = itemView.context!!
        private var isDrop = false
        private var endArray: ArrayList<EndCheckData> = ArrayList()
        private var nullArray: ArrayList<EndCheckData> = ArrayList()
        lateinit var endAdapter: EndCheckRecyclerAdapter

        fun bind(item: MiddleCheckData, position: Int, sIndex: Int) {
            itemView.text_middle_check.text = "${item.index}\n${item.text}"
            itemView.text_score_middle_check.text = "[총점 ${item.totalScore}점]"
            itemView.check_middle_check.setOnClickListener {
                item.noApplicable = itemView.check_middle_check.isChecked
                if(item.noApplicable){
                    itemView.button_middle_check.setImageResource(R.drawable.ic_arrow_right)
                    itemView.button_middle_check.isClickable = false
                    itemView.recycler_middle_check.visibility = View.GONE
                }else{
                    itemView.button_middle_check.setImageResource(R.drawable.ic_arrow_down)
                    itemView.button_middle_check.isClickable = true
                    itemView.recycler_middle_check.visibility = View.VISIBLE
                }
                setScore(sIndex, position, item.noApplicable)
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
                setScore(sIndex, position, item.noApplicable)
            }
            getEndData(sIndex, position, item.endList!!, itemView)
        }

        private fun getEndData(sIndex: Int, mIndex: Int, items: ArrayList<EndCheckData>, view: View){
            endArray.clear()
            for (item in items){
                endArray.add(EndCheckData(item.index, item.text, item.score, item.isChecked))
            }
            setRecyclerView(sIndex, mIndex, view, endArray)
        }

        private fun setRecyclerView(sIndex: Int, mIndex: Int, view: View, endArray: ArrayList<EndCheckData>){
            endAdapter = EndCheckRecyclerAdapter(sIndex, mIndex, endArray, context)
            view.recycler_middle_check.adapter = endAdapter
            (view.recycler_middle_check.adapter as EndCheckRecyclerAdapter).notifyDataSetChanged()
            endAdapter.itemClick = object : EndCheckRecyclerAdapter.ItemClick {
                override fun onItemClick(view: View?, position: Int) {
                }
            }
            view.recycler_middle_check.visibility = View.GONE
        }

        private fun setScore(sIndex: Int, mIndex: Int, noApplicable: Boolean){
            CheckNormalActivity().changeMiddle(sIndex, mIndex, noApplicable)
        }
    }

    var itemClick: ItemClick? = null

    interface ItemClick {
        fun onItemClick(view: View?, position: Int)
    }
}
