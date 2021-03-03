package com.udacity.notepad.util

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import kotlin.properties.Delegates

class SpaceItemDecoration : ItemDecoration {
    private lateinit var context: Context
    private var dimenRes by Delegates.notNull<Int>()
    private var space by Delegates.notNull<Int>()

    constructor(context: Context, dimenRes: Int) {
        this.context = context
        this.dimenRes = dimenRes
        space = context.resources.getDimensionPixelOffset(dimenRes)
    }

    private constructor() {
        throw RuntimeException()
    }

    override fun getItemOffsets(outRect: Rect, view: View,
                                parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)
        val position = parent.getChildAdapterPosition(view)
        when (getOrientation(parent)) {
            LinearLayoutManager.VERTICAL -> if (position != 0) outRect.top = space
            LinearLayoutManager.HORIZONTAL -> if (position != 0) outRect.left = space
        }
    }

    private fun getOrientation(parent: RecyclerView): Int {
        val lm = parent.layoutManager
        return if (lm is LinearLayoutManager) {
            lm.orientation
        } else -1
    }
}