package com.sng.finalproject2.ui.result

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sng.finalproject2.R
import com.sng.finalproject2.model.Question
import kotlinx.android.synthetic.main.item_result.view.*
import java.lang.Exception

class ResultRvAdapter(private val mList: List<Question>, private val context: Context) :
    RecyclerView.Adapter<ResultRvAdapter.ResultVH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultVH {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_result, parent, false)
        return ResultVH(view)
    }

    override fun getItemCount(): Int {
        return mList.count()
    }

    override fun onBindViewHolder(holder: ResultVH, position: Int) {
        val question = mList[position]
        holder.tvQuestion.text = question.question
        holder.tvAnswer.text = "Correct: ${question.answer[question.correct]}"
        var choice = ""
        try {
            choice = "" + question.answer[question.selected - 1]
        } catch (e: Exception) {
            e.printStackTrace()
        }
        holder.tvUrChoice.text = "Your choice: $choice"

        if (question.correct != question.selected - 1) {
            holder.tvUrChoice.setTextColor(context.resources.getColor(R.color.red_btn_bg_color))
        } else {
            holder.tvUrChoice.setTextColor(context.resources.getColor(R.color.text_color))
        }
    }

    inner class ResultVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvQuestion = itemView.question
        val tvUrChoice = itemView.tvUrChoice
        val tvAnswer = itemView.tvAnswer
    }
}
