package com.example.TakeMeWithYou.adapter

import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter

class SettingListViewAdapter(
    private val mainItem: Array<String>,
    private val childItem: Map<String, Array<String>>
) : BaseExpandableListAdapter() {
    override fun getGroupCount(): Int = mainItem.size

    override fun getChildrenCount(groupPosition: Int) =
        childItem[mainItem[groupPosition]]?.size ?: 0

    override fun getGroup(groupPosition: Int): Any = mainItem[groupPosition]

    override fun getChild(groupPosition: Int, childPosition: Int): Any =
        childItem[mainItem[groupPosition]]?.get(childPosition) ?: ""

    override fun getGroupId(groupPosition: Int): Long {
        TODO("Not yet implemented")
    }

    override fun getChildId(groupPosition: Int, childPosition: Int): Long {
        TODO("Not yet implemented")
    }

    override fun hasStableIds(): Boolean {
        TODO("Not yet implemented")
    }

    override fun getGroupView(
        groupPosition: Int,
        isExpanded: Boolean,
        convertView: View?,
        parent: ViewGroup?
    ): View {
        TODO("Not yet implemented")
    }

    override fun getChildView(
        groupPosition: Int,
        childPosition: Int,
        isLastChild: Boolean,
        convertView: View?,
        parent: ViewGroup?
    ): View {
        TODO("Not yet implemented")
    }

    override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean {
        TODO("Not yet implemented")
    }
}