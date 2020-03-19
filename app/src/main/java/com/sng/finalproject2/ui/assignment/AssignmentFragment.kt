package com.sng.finalproject2.ui.assignment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.sng.finalproject2.R
import com.sng.finalproject2.ui.home.LectureFragment
import com.sng.finalproject2.ui.home.MyLectureRecyclerViewAdapter
import com.sng.finalproject2.ui.home.dummy.AssignmentContent
import com.sng.finalproject2.ui.home.dummy.LectureContent
import kotlinx.android.synthetic.main.fragment_lecture_list.*

class AssignmentFragment : Fragment(), LectureFragment.OnListFragmentInteractionListener {

    private lateinit var assignmentViewModel: AssignmentViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_assignment, container, false)

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val layoutManager =
            LinearLayoutManager(context)
        list.layoutManager = layoutManager
        val assignment = AssignmentContent()
        list.adapter = MyLectureRecyclerViewAdapter(assignment.ITEMS, activity as LectureFragment.OnListFragmentInteractionListener)
    }

    override fun onListFragmentInteraction(item: LectureContent.LectureItem?) {
    }
}
