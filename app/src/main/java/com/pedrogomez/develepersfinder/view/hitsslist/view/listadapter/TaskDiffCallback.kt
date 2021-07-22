package com.pedrogomez.develepersfinder.view.hitsslist.view.listadapter

import androidx.recyclerview.widget.DiffUtil
import com.pedrogomez.develepersfinder.models.db.UserPicture

class TaskDiffCallback : DiffUtil.ItemCallback<UserPicture>() {
    override fun areItemsTheSame(oldItem: UserPicture, newItem: UserPicture): Boolean {
        return oldItem.objectID == newItem.objectID
    }

    override fun areContentsTheSame(oldItem: UserPicture, newItem: UserPicture): Boolean {
        return oldItem == newItem
    }
}