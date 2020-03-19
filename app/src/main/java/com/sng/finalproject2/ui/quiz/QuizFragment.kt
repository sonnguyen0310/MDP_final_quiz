package com.sng.finalproject2.ui.quiz

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import androidx.fragment.app.Fragment
import cn.pedant.SweetAlert.SweetAlertDialog
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.sng.finalproject2.R
import com.sng.finalproject2.model.Question
import com.sng.finalproject2.ui.result.ResultActivity
import kotlinx.android.synthetic.main.fragment_quiz.*

class QuizFragment : Fragment() {

    private lateinit var quizViewModel: QuizViewModel
    private lateinit var database: DatabaseReference
    private var quizs = arrayListOf<Question>()
    private var currentQuestion = 0
    private var pDialog: SweetAlertDialog? = null
    private val dataListener = object : ValueEventListener {
        override fun onCancelled(p0: DatabaseError) {
            pDialog?.dismissWithAnimation()
        }

        override fun onDataChange(p0: DataSnapshot) {
            currentQuestion = 0
            loadQuestion()
            pDialog?.dismissWithAnimation()
        }

    }
    private val childEventListener = object : ChildEventListener {
        override fun onCancelled(p0: DatabaseError) {
        }

        override fun onChildMoved(p0: DataSnapshot, p1: String?) {
        }

        override fun onChildChanged(p0: DataSnapshot, p1: String?) {
        }

        override fun onChildAdded(dataSnapshot: DataSnapshot, p1: String?) {
            val question = dataSnapshot.getValue(Question::class.java) ?: return
            quizs.add(question)
        }

        override fun onChildRemoved(p0: DataSnapshot) {
        }

    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_quiz, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        database = Firebase.database.reference.child("quiz")


    }

    override fun onStart() {
        super.onStart()
        pDialog = SweetAlertDialog(context, SweetAlertDialog.PROGRESS_TYPE)
        pDialog?.progressHelper?.barColor = Color.parseColor("#A5DC86");
        pDialog?.titleText = "Loading Quiz"
        pDialog?.setCancelable(false)
        pDialog?.show()
        database.addValueEventListener(dataListener)
        database.addChildEventListener(childEventListener)
        setupButton()
        btnPrev.isEnabled = currentQuestion == 0
        btnNext.setOnClickListener {
            nextClick()
        }

        btnPrev.setOnClickListener {
            prevClick()
        }

        btnReset.setOnClickListener {
            resetQuiz()
        }
    }

    override fun onStop() {
        super.onStop()
        database.removeEventListener(dataListener)
        database.removeEventListener(childEventListener)
        resetQuiz()
    }

    fun setCheckedListener(clear: Boolean) {
        if (clear) {
            radios.setOnCheckedChangeListener(null)
            return
        }
        radios.setOnCheckedChangeListener { p0, p1 ->
            if (quizs.size > currentQuestion) {
                val ques = quizs[currentQuestion]
                when (p1) {
                    R.id.radioButton -> ques.selected = 1
                    R.id.radioButton2 -> ques.selected = 2
                    R.id.radioButton3 -> ques.selected = 3
                    R.id.radioButton4 -> ques.selected = 4
                }
            }
        }
    }

    fun resetQuiz() {
        currentQuestion = 0
        quizs.forEach {
            it.selected = 0
        }
        setupButton()
        loadQuestion()
    }

    fun setupButton() {
        btnPrev.isEnabled = currentQuestion > -1

    }

    private fun loadQuestion() {
        if (quizs.size <= currentQuestion) {
            return
        }
        title.text = ""
        setCheckedListener(true)
        radios.clearCheck()
        setCheckedListener(false)
        if (currentQuestion > -1 && currentQuestion < quizs.size) {
            var q = quizs[currentQuestion]
            if (q.selected > 0) {
                when (q.selected) {
                    1 -> radioButton.isChecked = true
                    2 -> radioButton2.isChecked = true
                    3 -> radioButton3.isChecked = true
                    4 -> radioButton4.isChecked = true
                    else -> radios.clearCheck()
                }
            }
        }
        var question = quizs[currentQuestion]
        title.text = question.question
        radioButton.text = question.answer[0]
        radioButton2.text = question.answer[1]
        radioButton3.text = question.answer[2]
        radioButton4.text = question.answer[3]

    }

    private fun prevClick() {
        setupButton()
        if (currentQuestion > 0) {
            currentQuestion -= 1
        }
        loadQuestion()
    }

    private fun nextClick() {
        if (currentQuestion >= quizs.size - 1) {
            showResult()
            return
        }
        setupButton()
        currentQuestion += 1
        loadQuestion()
        if (currentQuestion == quizs.size - 1) {
            btnNext.text = getString(R.string.lb_finish)
        } else {
            btnNext.text = getString(R.string.lb_next)
        }
    }

    private fun showResult() {
        var score = 0
        quizs.forEach {
            if (it.selected - 1 == it.correct) {
                score += 1
            }
        }
        val message = "Your score is ${score}/${quizs.size}"
        SweetAlertDialog(context, SweetAlertDialog.SUCCESS_TYPE)
            .setTitleText("Congratulation!")
            .setContentText(message)
            .setConfirmText("See the result")
            .setCancelText("Reset")
            .setConfirmClickListener {
                it.dismissWithAnimation()

                val intent = Intent(context, ResultActivity::class.java)
                intent.putExtra("DATA", quizs)
                intent.putExtra("MESS", message)
                startActivity(intent)
                resetQuiz()
            }
            .setCancelClickListener {
                resetQuiz()
                it.dismissWithAnimation()
            }
            .show()
    }
}
