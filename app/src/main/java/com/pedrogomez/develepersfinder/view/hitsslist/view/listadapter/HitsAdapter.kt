package com.pedrogomez.develepersfinder.view.hitsslist.view.listadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.pedrogomez.develepersfinder.models.db.UserPicture
import com.pedrogomez.develepersfinder.view.hitsslist.view.swipecontroler.SwipeController

class HitsAdapter(
    private val onClickItemListener: HitViewHolder.OnClickItemListener,
    private val buttonsActions: SwipeController.SwipeControllerActions
) : ListAdapter<UserPicture,HitViewHolder>(TaskDiffCallback()) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HitViewHolder {
        val inflater = LayoutInflater.from(
            parent.context
        )
        return HitViewHolder(
            inflater,
            parent
        )
    }

    override fun onBindViewHolder(
        holder: HitViewHolder,
        position: Int
    ) {
        val item = getItem(position)
        holder.setData(
            item,
            onClickItemListener,
            buttonsActions
        )
    }
}