package com.pedrogomez.develepersfinder.models.db

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "hit_table")
data class HitTable(
        @PrimaryKey
        @NonNull
        @ColumnInfo(name = "objectID")
        val objectID: String,
        @NonNull
        @ColumnInfo(name = "author")
        val author: String,
        @NonNull
        @ColumnInfo(name = "created_at_i")
        val created_at_i: Long,
        @ColumnInfo(name = "story_title")
        val story_title: String?,
        @ColumnInfo(name = "story_url")
        val story_url: String?,
        @ColumnInfo(name = "title")
        val title: String?,
        @ColumnInfo(name = "url")
        val url: String?,
        @ColumnInfo(name = "isDeleted")
        var isDeleted: Boolean = false
)

/*fun HitTable.toPresentationModel() : Hit {
        return Hit(
                product_id,
                likes,
                like_user,
                address,
                price,
                currency,
                p_condition,
                category,
                title,
                description,
                attachment.url,
                attachment.thumbnail,
                created,
                owner
        )
}*/