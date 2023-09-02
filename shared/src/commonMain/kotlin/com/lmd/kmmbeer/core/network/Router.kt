package com.lmd.kmmbeer.core.network

import com.lmd.kmmbeer.core.data.Beer
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.http.contentType

class Router(
    private val client: HttpClient
) {

    suspend fun getBeers(): List<Beer> {
        return handleResponse {
            client.get(BEERS) {
                contentType(ContentType.Application.Json)
            }
        }
    }

    companion object {
        private const val BEER_URL = "https://api.punkapi.com/v2/"
        private const val BEERS = BEER_URL + "beers"
    }
}