package com.sanhuzhen.openeye.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sanhuzhen.openeye.R


class HotWordRvAdapter :
    ListAdapter<List<String>, HotWordRvAdapter.HotWordViewHolder>(object :
        DiffUtil.ItemCallback<List<String>>() {
        override fun areItemsTheSame(oldItem: List<String>, newItem: List<String>): Boolean {
            return oldItem == newItem
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: List<String>, newItem: List<String>): Boolean {
            return oldItem == newItem
        }

    }) {
    private var HotWords: List<String>? = null
    override fun onBindViewHolder(holder: HotWordViewHolder, position: Int) {
        holder.bind(position)
    }

    fun setData(hotWords: List<String>) {
        HotWords = hotWords
    }

    override fun onCreateViewHolder(
        parent: android.view.ViewGroup,
        viewType: Int
    ): HotWordViewHolder {
        return HotWordViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.rv_search_normal_type, parent, false)
        )
    }

    inner class HotWordViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tv: TextView = itemView.findViewById(R.id.rv_search_tv)
        fun bind(position: Int) {
            tv.text = HotWords!![position]
        }
    }
}