package com.pedrogomez.develepersfinder.repository.remote


import com.pedrogomez.develepersfinder.contracts.RepoContracts
import com.pedrogomez.develepersfinder.models.api.CandidatesResponse
import com.pedrogomez.develepersfinder.repository.DataBaseManager
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.http.*
import com.pedrogomez.develepersfinder.utils.extensions.print
/**
 * List photos

    Get a single page from the list of all photos.

    GET /photos

    Note: See the note on hotlinking.

    Parameters

    param 	Description

    page 	Page number to retrieve. (Optional; default: 1)
    per_page 	Number of items per page. (Optional; default: 10)
    order_by 	How to sort the photos. Optional. (Valid values: latest, oldest, popular; default: latest)
 *
 * **/
class ApiRepository(
    private val client : HttpClient,
    private val urlBase:String
) : RepoContracts.RemoteDataSource {

    override suspend fun loadPhotos(
        page:Int
    ):CandidatesResponse?{
        return try{
            val requestUrl = "${urlBase}photos?page=1$page"
            "Ktor_request getHitsData: $requestUrl".print()
            val response = client.request<CandidatesResponse>(requestUrl) {
                method = HttpMethod.Get
            }
            "Ktor_request getHitsData: $response".print()
            response
        }catch (e : Exception){
            "Ktor_request Exception: ${e.message}".print()
            "Ktor_request Exception: ${e.printStackTrace()}".print()
            "Ktor_request Exception: ${e.cause}".print()
            null
        }
    }

}