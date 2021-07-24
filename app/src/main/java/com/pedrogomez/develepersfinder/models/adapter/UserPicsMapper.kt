package com.pedrogomez.develepersfinder.models.adapter

import com.pedrogomez.develepersfinder.models.api.CandidateItem
import com.pedrogomez.develepersfinder.models.db.UserPicture

class UserPicsMapper {

    fun fromRemoteToLocal(candidateItem: CandidateItem):UserPicture{
        return UserPicture(
            id = candidateItem.id,
            createdAt = candidateItem.createdAt,
            description = candidateItem.description,
            likes = candidateItem.likes,
            fullImage = candidateItem.urls.full,
            firstName = candidateItem.user.firstName,
            idUser = candidateItem.user.id,
            instagramUsername = candidateItem.user.instagramUsername,
            lastName = candidateItem.user.lastName,
            name = candidateItem.user.name,
            portfolioUrl = candidateItem.user.portfolioUrl,
            profileImage = candidateItem.user.profileImage.medium,
            twitterUsername = candidateItem.user.twitterUsername,
            username = candidateItem.user.username
        )
    }
}