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
import com.sanhuzhen.openeye.bean.Message

/**
 * 对通知列表的RecyclerView的适配器
 */
class NoticeRvAdapter(private val context: Context) :
    ListAdapter<Message, NoticeRvAdapter.mViewHolder>(object : DiffUtil.ItemCallback<Message>() {
        override fun areItemsTheSame(oldItem: Message, newItem: Message): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Message, newItem: Message): Boolean {
            return oldItem == newItem
        }
    }) {
    inner class mViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val iv: ImageView = itemView.findViewById(R.id.iv_notice_type)
        private val tv1: TextView = itemView.findViewById(R.id.tv_notice_type1)
        private val tv2: TextView = itemView.findViewById(R.id.tv_notice_type2)

        fun bind(data: Message) {
//            if (data.icon.isNotEmpty()) {
//                Glide.with(itemView.context).load(data.icon)
//                    .transform(CenterCrop(), RoundedCorners(60)).into(iv)
//            }else{
                iv.setImageResource(R.drawable.img_2)
//            }
            tv1.text = data.title
            tv2.text = data.content
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): mViewHolder {
        return mViewHolder(View.inflate(context, R.layout.rv_notice_type, null))
    }

    override fun onBindViewHolder(holder: mViewHolder, position: Int) {
        val noticeList = getItem(position)
        holder.bind(noticeList)
    }
}