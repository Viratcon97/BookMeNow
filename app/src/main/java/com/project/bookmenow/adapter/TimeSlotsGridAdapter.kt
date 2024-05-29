package com.project.bookmenow.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.BaseAdapter
import android.widget.Button
import com.project.bookmenow.R


class TimeSlotsGridAdapter(private var context : Context,
                           private var itemCount : ArrayList<String>,
                           private val listener: OnItemClickListener
) : BaseAdapter() {

    private var selectedPosition: Int = -1

    override fun getCount(): Int {
        return itemCount.size
    }

    override fun getItem(position: Int): Any {
        return 0
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {

        var view = convertView
        if (view == null) {
            val inflater =
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            view = inflater.inflate(R.layout.layout_time, parent, false)
        }
        val timeValueButton = view?.findViewById<Button>(R.id.buttonTime)
        timeValueButton?.text = itemCount[position]

        timeValueButton?.setBackgroundColor(
            if (position == selectedPosition) context.getColor(R.color.green)
            else context.getColor(R.color.white)
        )
        timeValueButton?.setTextColor(
            if(position == selectedPosition) context.getColor(R.color.white)
            else context.getColor(R.color.black)
        )
        timeValueButton?.setOnClickListener {
            selectedPosition = position
            listener.onItemClick(itemCount[position])
            notifyDataSetChanged()
        }
        return view
    }
    interface OnItemClickListener {
        fun onItemClick(selectedValue: String)
    }
}
