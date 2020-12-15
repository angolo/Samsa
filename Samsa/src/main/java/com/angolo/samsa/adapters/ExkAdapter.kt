package com.angolo.samsa.adapters

import android.view.View

class ExkAdapter<TYPE>(items: ArrayList<TYPE>,
                       layoutId: Int, private val bindHolder: View.(TYPE)->Unit) : AbstractAdapter<TYPE>(items,layoutId){
    //View.(TYPE)->Unit richiede una lambda nel costruttore che riceve TYPE e restituisce UNIT(cioè niente)


    //itemClick and longItemClick when no params needed
    private lateinit var itemClick: TYPE.() -> Unit
    private lateinit var longItemClick: TYPE.()->Unit

    //itemClick and longItemClick when View and Position is needed
    private lateinit var itemClickW: TYPE.(view:View,position:Int) -> Unit
    private lateinit var longItemClickW: TYPE.(view:View, position:Int)->Unit

    constructor(items: ArrayList<TYPE>,layoutId: Int,bindHolder: View.(TYPE) -> Unit,
                itemClickW: TYPE.()->Unit={},
                longItemClick: TYPE.()->Unit={}) : this(items,layoutId,bindHolder){
        this.itemClick=itemClick
        this.longItemClick=longItemClick
    }

    constructor(items: ArrayList<TYPE>,layoutId: Int,bindHolder: View.(TYPE) -> Unit,
                itemClickW: TYPE.(view:View,position:Int)->Unit={view:View,position:Int->},
                longItemClickW: TYPE.(view:View,position:Int)->Unit={ view: View, i:Int->}) : this(items,layoutId,bindHolder){
        this.itemClickW=itemClickW
        this.longItemClickW=longItemClickW
    }



    override fun onBindViewHolder(holder: Holder, position: Int) {
        //questa riga è maggica
        //passiamo alla lambda del costruttore l'oggetto della lista che si trova in position
        //così la lambda riceve il Model e si può utilizzare per settare la VIEW
        holder.itemView.bindHolder(itemList[position])

    }

    override fun onItemClick(view: View, position: Int){
        if(this::itemClickW.isInitialized)
            itemList[position].itemClickW(view,position)
        else if(this::itemClick.isInitialized)
            itemList[position].itemClick()

    }

    override fun onLongItemClick(view: View, position: Int){
        if(this::longItemClickW.isInitialized)
            itemList[position].longItemClickW(view,position)
        else if (this::longItemClick.isInitialized)
            itemList[position].longItemClick()

    }


}