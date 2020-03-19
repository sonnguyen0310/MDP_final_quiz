package com.sng.finalproject2.ui.result

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.sng.finalproject2.R
import com.sng.finalproject2.model.Question
import kotlinx.android.synthetic.main.activity_result.*

class ResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        title = "Result"

        var data = intent.getSerializableExtra("DATA")
        tvTitle.text = intent.getStringExtra("MESS")
        if (data is List<*>) {
            val list = arrayListOf<Question>()

            data.forEach {
                if (it is Question) {
                    list.add(it)
                }
            }
            recycler.layoutManager = LinearLayoutManager(this)
            recycler.adapter = ResultRvAdapter(list, this)
        }
    }
}
