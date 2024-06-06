package com.sanhuzhen.openeye.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.sanhuzhen.openeye.R
import com.sanhuzhen.openeye.bean.Data

/**
 * 为分类列表的RecyclerView的适配器
 */
class CategoryListRvAdapter(private val context: Context) : ListAdapter<Data, CategoryListRvAdapter.mViewHolder>(object :
    DiffUtil.ItemCallback<Data>() {
    override fun areItemsTheSame(oldItem: Data, newItem: Data): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Data, newItem: Data): Boolean {
        return oldItem == newItem
    }

}) {
    inner class mViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val iv: ImageView = itemView.findViewById(R.id.categoryList_iv)
        private val tv1: TextView = itemView.findViewById(R.id.categoryList_title)
        private val tv2: TextView = itemView.findViewById(R.id.categoryList_desc)

        fun bind(data: Data) {
            if (data.icon.isNotEmpty()) {
                Glide.with(itemView.context).load(data.icon)
                    .transform(CenterCrop(), RoundedCorners(60)).into(iv)
            } else {
                iv.setImageResource(R.drawable.img_2)
            }
            tv1.text = data.title
            tv2.text = data.description
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): mViewHolder {
        val view = View.inflate(parent.context, R.layout.categorylist_type, null)
        return mViewHolder(view)
    }

    override fun onBindViewHolder(holder: mViewHolder, position: Int) {
        val categoryList = getItem(position)
        holder.bind(categoryList)
    }
}