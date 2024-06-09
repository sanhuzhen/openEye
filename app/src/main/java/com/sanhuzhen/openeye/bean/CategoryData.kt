package com.sanhuzhen.openeye.bean

/**
 * 对分类列表详情的数据类
 */
data class CategoryData(
    val tabInfo: TabInfo
)

data class TabInfo(
    val defaultIdx: Int,
    val tabList: List<Tab>
)

data class Tab(
    val adTrack: Any,
    val apiUrl: String,
    val id: Int,
    val name: String,
    val nameType: Int,
    val tabType: Int
)