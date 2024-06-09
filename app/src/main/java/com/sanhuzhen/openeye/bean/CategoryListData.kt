package com.sanhuzhen.openeye.bean

/**
 * 对分类列表的数据类
 */
data class ListData(
    val adExist: Boolean,
    val count: Int,
    val itemList: List<Item>,
    val nextPageUrl: Any,
    val total: Int
)

data class Item(
    val adIndex: Int,
    val `data`: Data,
    val id: Int,
    val tag: Any,
    val trackingData: Any,
    val type: String
)

data class Data(
    val actionUrl: String,
    val adTrack: Any,
    val dataType: String,
    val description: String,
    val expert: Boolean,
    val follow: Follow,
    val icon: String,
    val iconType: String,
    val id: Int,
    val ifPgc: Boolean,
    val ifShowNotificationIcon: Boolean,
    val subTitle: Any,
    val title: String,
    val uid: Int
)

data class Follow(
    val followed: Boolean,
    val itemId: Int,
    val itemType: String
)