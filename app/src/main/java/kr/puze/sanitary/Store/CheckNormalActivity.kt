package kr.puze.sanitary.Store

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_check_normal.*
import kr.puze.sanitary.Adapter.StartCheckRecyclerAdapter
import kr.puze.sanitary.Data.EndCheckData
import kr.puze.sanitary.Data.MiddleCheckData
import kr.puze.sanitary.Data.StartCheckData
import kr.puze.sanitary.R

class CheckNormalActivity : AppCompatActivity() {

    companion object{
        lateinit var startAdapter: StartCheckRecyclerAdapter
        lateinit var totalTextView: TextView
        lateinit var scoreTextView: TextView
        val startArray = ArrayList<StartCheckData>()
        var totalScore = 0
        var score = 0
        var storeId = ""
        var storeTitle = ""
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check_normal)

        init()
    }

    private fun init(){
        totalTextView = text_total_score_check_normal
        scoreTextView = text_score_check_normal
        storeId = intent.getStringExtra("storeId")
        storeTitle = intent.getStringExtra("title")
        text_title_check_normal.text = storeTitle

        button_back_check_normal.setOnClickListener { finish() }

        getEndData()
    }

    private fun setRecyclerView(startArray: ArrayList<StartCheckData>){
        startAdapter = StartCheckRecyclerAdapter(startArray, this@CheckNormalActivity)
        recycler_check_normal.adapter = startAdapter
        (recycler_check_normal.adapter as StartCheckRecyclerAdapter).notifyDataSetChanged()
        startAdapter.itemClick = object : StartCheckRecyclerAdapter.ItemClick {
            override fun onItemClick(view: View?, position: Int) {
            }
        }
        button_check_normal.setOnClickListener { submit() }
        check()
    }

    private fun getEndData(){
        startArray.clear()
        val startItems = resources.getStringArray(R.array.start)
        for (i in startItems.indices){
            val middleArray = ArrayList<MiddleCheckData>()
            val middleItems = when(i+1){
                1->{ resources.getStringArray(R.array.middle1)}
                2->{ resources.getStringArray(R.array.middle2)}
                3->{ resources.getStringArray(R.array.middle3)}
                4->{ resources.getStringArray(R.array.middle4)}
                5->{ resources.getStringArray(R.array.middle5)}
                6->{ resources.getStringArray(R.array.middle6)}
                else -> { resources.getStringArray(R.array.middle1)}
            }
            val middleScoreItems = when(i+1){
                1->{ resources.getStringArray(R.array.middleScore1)}
                2->{ resources.getStringArray(R.array.middleScore2)}
                3->{ resources.getStringArray(R.array.middleScore3)}
                4->{ resources.getStringArray(R.array.middleScore4)}
                5->{ resources.getStringArray(R.array.middleScore5)}
                6->{ resources.getStringArray(R.array.middleScore6)}
                else -> { resources.getStringArray(R.array.middleScore1)}
            }
            for(j in middleItems.indices){
                val endArray = ArrayList<EndCheckData>()
                var endItems = when(i+1){
                    1->{ when(j+1){
                        1->{resources.getStringArray(R.array.end1_1)}
                        2->{resources.getStringArray(R.array.end1_2)}
                        3->{resources.getStringArray(R.array.end1_3)}
                        4->{resources.getStringArray(R.array.end1_4)}
                        5->{resources.getStringArray(R.array.end1_5)}
                        6->{resources.getStringArray(R.array.end1_6)}
                        7->{resources.getStringArray(R.array.end1_7)}
                        8->{resources.getStringArray(R.array.end1_8)}
                        9->{resources.getStringArray(R.array.end1_9)}
                        10->{resources.getStringArray(R.array.end1_10)}
                        11->{resources.getStringArray(R.array.end1_11)}
                        12->{resources.getStringArray(R.array.end1_12)}
                        13->{resources.getStringArray(R.array.end1_13)}
                        14->{resources.getStringArray(R.array.end1_14)}
                        else->{resources.getStringArray(R.array.end1_1)}
                    } }
                    2->{ when(j+1){
                        1->{resources.getStringArray(R.array.end2_1)}
                        2->{resources.getStringArray(R.array.end2_2)}
                        3->{resources.getStringArray(R.array.end2_3)}
                        4->{resources.getStringArray(R.array.end2_4)}
                        5->{resources.getStringArray(R.array.end2_5)}
                        6->{resources.getStringArray(R.array.end2_6)}
                        7->{resources.getStringArray(R.array.end2_7)}
                        8->{resources.getStringArray(R.array.end2_8)}
                        9->{resources.getStringArray(R.array.end2_9)}
                        10->{resources.getStringArray(R.array.end2_10)}
                        11->{resources.getStringArray(R.array.end2_11)}
                        12->{resources.getStringArray(R.array.end2_12)}
                        13->{resources.getStringArray(R.array.end2_13)}
                        14->{resources.getStringArray(R.array.end2_14)}
                        15->{resources.getStringArray(R.array.end2_15)}
                        16->{resources.getStringArray(R.array.end2_16)}
                        17->{resources.getStringArray(R.array.end2_17)}
                        18->{resources.getStringArray(R.array.end2_18)}
                        19->{resources.getStringArray(R.array.end2_19)}
                        else->{resources.getStringArray(R.array.end2_1)}
                    } }
                    3->{ when(j+1){
                        1->{resources.getStringArray(R.array.end3_1)}
                        2->{resources.getStringArray(R.array.end3_2)}
                        3->{resources.getStringArray(R.array.end3_3)}
                        else->{resources.getStringArray(R.array.end3_1)}
                    } }
                    4->{ when(j+1){
                        1->{resources.getStringArray(R.array.end4_1)}
                        2->{resources.getStringArray(R.array.end4_2)}
                        else->{resources.getStringArray(R.array.end4_1)}
                    } }
                    5->{ when(j+1){
                        1->{resources.getStringArray(R.array.end5_1)}
                        2->{resources.getStringArray(R.array.end5_2)}
                        else->{resources.getStringArray(R.array.end5_1)}
                    } }
                    6->{ when(j+1){
                        1->{resources.getStringArray(R.array.end6_1)}
                        2->{resources.getStringArray(R.array.end6_2)}
                        3->{resources.getStringArray(R.array.end6_3)}
                        4->{resources.getStringArray(R.array.end6_4)}
                        5->{resources.getStringArray(R.array.end6_5)}
                        6->{resources.getStringArray(R.array.end6_6)}
                        else->{resources.getStringArray(R.array.end6_1)}
                    } }
                    else -> { resources.getStringArray(R.array.end1_1)}
                }
                var endScoreItems = when(i+1){
                    1->{ when(j+1){
                        1->{resources.getStringArray(R.array.endScore1_1)}
                        2->{resources.getStringArray(R.array.endScore1_2)}
                        3->{resources.getStringArray(R.array.endScore1_3)}
                        4->{resources.getStringArray(R.array.endScore1_4)}
                        5->{resources.getStringArray(R.array.endScore1_5)}
                        6->{resources.getStringArray(R.array.endScore1_6)}
                        7->{resources.getStringArray(R.array.endScore1_7)}
                        8->{resources.getStringArray(R.array.endScore1_8)}
                        9->{resources.getStringArray(R.array.endScore1_9)}
                        10->{resources.getStringArray(R.array.endScore1_10)}
                        11->{resources.getStringArray(R.array.endScore1_11)}
                        12->{resources.getStringArray(R.array.endScore1_12)}
                        13->{resources.getStringArray(R.array.endScore1_13)}
                        14->{resources.getStringArray(R.array.endScore1_14)}
                        else->{resources.getStringArray(R.array.endScore1_1)}
                    } }
                    2->{ when(j+1){
                        1->{resources.getStringArray(R.array.endScore2_1)}
                        2->{resources.getStringArray(R.array.endScore2_2)}
                        3->{resources.getStringArray(R.array.endScore2_3)}
                        4->{resources.getStringArray(R.array.endScore2_4)}
                        5->{resources.getStringArray(R.array.endScore2_5)}
                        6->{resources.getStringArray(R.array.endScore2_6)}
                        7->{resources.getStringArray(R.array.endScore2_7)}
                        8->{resources.getStringArray(R.array.endScore2_8)}
                        9->{resources.getStringArray(R.array.endScore2_9)}
                        10->{resources.getStringArray(R.array.endScore2_10)}
                        11->{resources.getStringArray(R.array.endScore2_11)}
                        12->{resources.getStringArray(R.array.endScore2_12)}
                        13->{resources.getStringArray(R.array.endScore2_13)}
                        14->{resources.getStringArray(R.array.endScore2_14)}
                        15->{resources.getStringArray(R.array.endScore2_15)}
                        16->{resources.getStringArray(R.array.endScore2_16)}
                        17->{resources.getStringArray(R.array.endScore2_17)}
                        18->{resources.getStringArray(R.array.endScore2_18)}
                        19->{resources.getStringArray(R.array.endScore2_19)}
                        else->{resources.getStringArray(R.array.endScore2_1)}
                    } }
                    3->{ when(j+1){
                        1->{resources.getStringArray(R.array.endScore3_1)}
                        2->{resources.getStringArray(R.array.endScore3_2)}
                        3->{resources.getStringArray(R.array.endScore3_3)}
                        else->{resources.getStringArray(R.array.endScore3_1)}
                    } }
                    4->{ when(j+1){
                        1->{resources.getStringArray(R.array.endScore4_1)}
                        2->{resources.getStringArray(R.array.endScore4_2)}
                        else->{resources.getStringArray(R.array.endScore4_1)}
                    } }
                    5->{ when(j+1){
                        1->{resources.getStringArray(R.array.endScore5_1)}
                        2->{resources.getStringArray(R.array.endScore5_2)}
                        else->{resources.getStringArray(R.array.endScore5_1)}
                    } }
                    6->{ when(j+1){
                        1->{resources.getStringArray(R.array.endScore6_1)}
                        2->{resources.getStringArray(R.array.endScore6_2)}
                        3->{resources.getStringArray(R.array.endScore6_3)}
                        4->{resources.getStringArray(R.array.endScore6_4)}
                        5->{resources.getStringArray(R.array.endScore6_5)}
                        6->{resources.getStringArray(R.array.endScore6_6)}
                        else->{resources.getStringArray(R.array.endScore6_1)}
                    } }
                    else -> { resources.getStringArray(R.array.endScore1_1)}
                }
                for (k in endItems.indices){
                    endArray.add(EndCheckData("${i+1}.${j+1}.${k+1}", endItems[k], endScoreItems[k].toInt(), false))
                }
                middleArray.add(MiddleCheckData("${i+1}.${j+1}", middleItems[j], middleScoreItems[j].toInt(), false, endArray))
            }
            startArray.add(StartCheckData(startItems[i], middleArray))
        }
        setRecyclerView(startArray)
    }

    fun changeMiddle(sIndex: Int, mIndex: Int, noApplicable: Boolean){
        startArray[sIndex].middleList!![mIndex].noApplicable = noApplicable
        check()
    }

    fun changeEnd(sIndex: Int, mIndex: Int, eIndex: Int, isChecked: Boolean){
        startArray[sIndex].middleList!![mIndex].endList!![eIndex].isChecked = isChecked
        check()
    }

    private fun check() {
        totalScore = 0
        score = 0
        for (i in startArray.indices) {
            for(j in startArray[i].middleList!!.indices) {
                if(!startArray[i].middleList!![j].noApplicable){
                    totalScore += startArray[i].middleList!![j].totalScore
                    for (k in startArray[i].middleList!![j].endList!!.indices) {
                        if(startArray[i].middleList!![j].endList!![k].isChecked){
                            score += startArray[i].middleList!![j].endList!![k].score
                        }
                    }
                }
            }
        }

        totalTextView.text = "총점: $totalScore"
        scoreTextView.text = "총점: $score"
    }

    private fun submit(){
        val submitScore = ((score.toDouble() / totalScore.toDouble()) * 100)
        startActivity(
            Intent(this@CheckNormalActivity, CheckCommonActivity::class.java)
                .putExtra("storeId", storeId)
                .putExtra("title", storeTitle)
                .putExtra("score", submitScore)
                .putParcelableArrayListExtra("startArray", startArray))

    }
}