package com.pedrogomez.develepersfinder.repository.remote


import com.pedrogomez.develepersfinder.repository.HitsProvider
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.http.*
import com.pedrogomez.develepersfinder.utils.extensions.print

class HitsApiRepository(
    private val client : HttpClient,
    private val urlBase:String
) : HitsProvider.RemoteDataSource {

    override suspend fun getHitsData(
        page:Int
    ):HitsListResponse?{
        return try{
            val requestUrl = "${urlBase}&&page=$page"
            "Ktor_request getHitsData: $requestUrl".print()
            val response = client.request<HitsListResponse>(requestUrl) {
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