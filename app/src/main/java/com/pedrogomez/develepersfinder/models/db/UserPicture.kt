package com.pedrogomez.develepersfinder.models.db

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.pedrogomez.develepersfinder.models.api.Links
import com.pedrogomez.develepersfinder.models.api.LinksX
import com.pedrogomez.develepersfinder.models.api.ProfileImage
import com.pedrogomez.develepersfinder.models.api.UserCollection
import kotlinx.serialization.SerialName

@Entity(tableName = "user_picture")
data class UserPicture(
        @PrimaryKey
        @NonNull
        @ColumnInfo(name = "id")
        val id: String,
        @ColumnInfo(name = "created_at")
        val createdAt: String,
        @ColumnInfo(name = "description")
        val description: String,
        @ColumnInfo(name = "likes")
        val likes: Int,
        @ColumnInfo(name = "full")
        val full: String,
        @ColumnInfo(name = "first_name")
        val firstName: String,
        @ColumnInfo(name = "id_user")
        val idUser: String,
        @ColumnInfo(name ="instagram_username")
        val instagramUsername: String,
        @ColumnInfo(name ="last_name")
        val lastName: String,
        @ColumnInfo(name ="name")
        val name: String,
        @ColumnInfo(name = "portfolio_url")
        val portfolioUrl: String,
        @ColumnInfo(name = "profile_image")
        val profileImage: String,
        @ColumnInfo(name = "twitter_username")
        val twitterUsername: String,
        @ColumnInfo(name ="user_name")
        val username: String
)