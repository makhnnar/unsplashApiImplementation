package com.pedrogomez.develepersfinder.util

import androidx.lifecycle.liveData
import com.pedrogomez.develepersfinder.models.api.HitResponse
import com.pedrogomez.develepersfinder.models.db.UserPicture

class DataHelper {

    companion object{
        val HITTABLE = UserPicture(
            "objectId",
            "author",
            1000,
            "story_title",
            "story_url",
            "title",
            "url"
        )
        val HITSRESPONSE = HitsListResponse(
            listOf(
                HitResponse(
                    "objectId",
                    "author",
                    1000,
                    "story_title",
                    "story_url",
                    "title",
                    "url"
                ),
                HitResponse(
                    "objectId1",
                    "author1",
                    10001,
                    "story_title1",
                    "story_url1",
                    "title1",
                    "url1"
                )
            )
        )
        val HITSLIST = listOf(
            HITTABLE
        )
        val LIVEHISTDATA = liveData<List<UserPicture>> { HITSLIST }
        val EMPTYHISTS : List<UserPicture> = emptyList()
    }

}