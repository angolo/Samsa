package com.angolo.samsa

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.angolo.samsa.adapters.ExkAdapter

fun <TYPE> RecyclerView.setUp(
    items: ArrayList<TYPE>,
    layoutResId: Int,
    manager:RecyclerView.LayoutManager= LinearLayoutManager(this.context),
    bindHolder: View.(TYPE) -> Unit): ExkAdapter<TYPE> {

    return ExkAdapter(items,layoutResId,{bindHolder(it)}).apply {
        layoutManager=manager
        adapter=this
    }

}


fun <TYPE> RecyclerView.setUpWithListeners(items: ArrayList<TYPE>,
                                           layoutResId: Int, manager: RecyclerView.LayoutManager = LinearLayoutManager(this.context),
                                           bindHolder: View.(TYPE) -> Unit,
                                           itemClick: TYPE.(view:View, position:Int)->Unit={view:View, pos:Int->{}},
                                           longItemClick: TYPE.(view:View, position:Int) -> Unit ={view:View, pos:Int->{}}): ExkAdapter<TYPE> {

    return ExkAdapter(items,layoutResId,{bindHolder(it)},itemClick,longItemClick).apply {
        layoutManager=manager
        adapter=this
    }


}