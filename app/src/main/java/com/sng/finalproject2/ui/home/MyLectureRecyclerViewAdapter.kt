package com.sng.finalproject2.ui.home

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.sng.finalproject2.R


import com.sng.finalproject2.ui.home.LectureFragment.OnListFragmentInteractionListener
import com.sng.finalproject2.ui.home.dummy.LectureContent.LectureItem

import kotlinx.android.synthetic.main.fragment_lecture.view.*

/**
 * [RecyclerView.Adapter] that can display a [LectureItem] and makes a call to the
 * specified [OnListFragmentInteractionListener].
 * TODO: Replace the implementation with code for your data type.
 */
class MyLectureRecyclerViewAdapter(
    private val mValues: List<LectureItem>,
    private val mListener: OnListFragmentInteractionListener?
) : RecyclerView.Adapter<MyLectureRecyclerViewAdapter.ViewHolder>() {

    private val mOnClickListener: View.OnClickListener

    init {
        mOnClickListener = View.OnClickListener { v ->
            val item = v.tag as LectureItem
            // Notify the active callbacks interface (the activity, if the fragment is attached to
            // one) that an item has been selected.
            mListener?.onListFragmentInteraction(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_lecture, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mValues[position]
        holder.mIdView.text = item.id
        holder.mContentView.text = item.content
        if (mListener is Context) {
            holder.imageView.setImageResource(R.drawable.pdf)
        }
        with(holder.mView) {
            tag = item
            setOnClickListener(mOnClickListener)
        }
    }

    override fun getItemCount(): Int = mValues.size

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val mIdView: TextView = mView.item_number
        val mContentView: TextView = mView.content
        val imageView: ImageView = mView.image
        override fun toString(): String {
            return super.toString() + " '" + mContentView.text + "'"
        }
    }
}
