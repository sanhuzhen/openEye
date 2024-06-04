package com.sanhuzhen.openeye.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sanhuzhen.openeye.R

class HotWordRvAdapter(context: Context):
    RecyclerView.Adapter<HotWordRvAdapter.HotWordViewHolder>() {

        private var hotWords: List<String> = listOf()

    fun setData(data: List<String>){
        hotWords = data
        notifyDataSetChanged()
    }
    inner class HotWordViewHolder(itemView: android.view.View) : RecyclerView.ViewHolder(itemView) {
        val tv: TextView = itemView.findViewById(R.id.rv_search_tv)
        fun bind(position: Int) {
            tv.text = hotWords[position]
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HotWordViewHolder {
        return HotWordViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.rv_search_normal_type, parent, false)
        )
    }

    override fun getItemCount(): Int = hotWords.size

    override fun onBindViewHolder(holder: HotWordViewHolder, position: Int) {
        holder.bind(position)
    }

}