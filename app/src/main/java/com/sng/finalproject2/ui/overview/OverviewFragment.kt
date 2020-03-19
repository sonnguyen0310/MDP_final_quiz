package com.sng.finalproject2.ui.overview

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener
import com.github.barteksc.pdfviewer.listener.OnPageErrorListener
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle

import com.sng.finalproject2.R
import kotlinx.android.synthetic.main.activity_pdf_viewer.*


class OverviewFragment : Fragment(), OnPageChangeListener, OnLoadCompleteListener,
    OnPageErrorListener {

    var pageNumber = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_overview, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        openPdf()
    }
    private fun openPdf() {
        try {

            pdfView.fromAsset("CS473_Mobile_Device Programming_Syllabus March_2020.pdf")
                .defaultPage(pageNumber)
                .onPageChange(this)
                .enableAnnotationRendering(true)
                .onLoad(this)
                .scrollHandle(DefaultScrollHandle(context))
                .spacing(10) // in dp
                .onPageError(this)
                .load();
        } catch (e: Exception) {
            Log.e("ERROR", "" + e)
        }
    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment OverviewFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            OverviewFragment().apply {

            }
    }

    override fun onPageChanged(page: Int, pageCount: Int) {
        pageNumber = page;
    }
    override fun loadComplete(nbPages: Int) {
    }

    override fun onPageError(page: Int, t: Throwable?) {
    }
}
