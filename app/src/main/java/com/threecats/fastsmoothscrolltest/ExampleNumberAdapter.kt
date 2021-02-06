package com.threecats.fastsmoothscrolltest

import android.graphics.Color
import android.graphics.Typeface
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ExampleNumberAdapter(
    private val size: Int
): RecyclerView.Adapter<ExampleNumberAdapter.CrapHolder>() {

    init {
        setHasStableIds(true)
    }

    class CrapHolder(v: View) : RecyclerView.ViewHolder(v) {
        val textView: TextView = v.findViewById(R.id.number)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CrapHolder {
        Log.d("adapter", "Create!")
        val v = LayoutInflater.from(parent.context).inflate(R.layout.crap_layout, parent, false)
        return CrapHolder(v)
    }

    override fun onBindViewHolder(holder: CrapHolder, position: Int) {
        Log.d("adapter", "Bind: $position")

        if (selectedPosition == position) {
            holder.textView.text = "Selected Item: $position"
            holder.textView.setTextColor(Color.WHITE)
            holder.textView.setBackgroundColor(Color.BLACK)
            holder.textView.setTypeface(null, Typeface.BOLD)
        } else {
            holder.textView.text = "Item: $position"
            holder.textView.setTextColor(Color.BLACK)
            val r = (position / 4) % 2 * 128 + 127
            val g = (position / 2) % 2 * 128 + 127
            val b = (position / 1) % 2 * 128 + 127
            holder.textView.setBackgroundColor(Color.rgb(r, g, b))
            holder.textView.setTypeface(null, Typeface.NORMAL)
        }
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemCount(): Int {
        return size
    }

    var selectedPosition: Int = 0
        set(value) {
            if (field != value) {
                notifyItemChanged(field)
                notifyItemChanged(value)
                field = value
            }
        }
}
