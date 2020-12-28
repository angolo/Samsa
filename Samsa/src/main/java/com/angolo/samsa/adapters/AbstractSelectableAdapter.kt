package com.angolo.samsa.adapters

import android.util.SparseBooleanArray

abstract class AbstractSelectableAdapter<TYPE>(
    items: ArrayList<TYPE>,
    layoutId: Int
) : AbstractAdapter<TYPE>(items, layoutId){

    private var selectedItems= SparseBooleanArray()


    open fun isSelected(position: Int): Boolean {
        return getSelectedItems()!!.contains(position)
    }

    open fun getSelectedItems(): List<Int>? {
        val items: MutableList<Int> = ArrayList(selectedItems.size())
        for (i in 0 until selectedItems.size()) {
            items.add(selectedItems.keyAt(i))
        }
        return items
    }

    open fun toggleSelection(position: Int) {
        if (selectedItems[position, false]) {
            selectedItems.delete(position)
        } else {
            selectedItems.put(position, true)
        }
        notifyItemChanged(position)
    }


    open fun clearSelection() {
        val selection = getSelectedItems()
        selectedItems.clear()
        for (i in selection!!) {
            notifyItemChanged(i)
        }
    }

    open fun getSelectedItemCount(): Int {
        return selectedItems.size()
    }

}