package com.lmd.kmmbeer.core.network

import com.lmd.kmmbeer.core.network.exceptions.BeerException
import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse
import okio.IOException

internal suspend inline fun <reified T> handleResponse(request: suspend () -> HttpResponse): T {
    val result = try {
        val response = request()

        when (response.status.value) {
            !in (200..300) -> throw Exception(response.status.description)
            else -> response
        }
    } catch (e: Exception) {
        throw BeerException(e.message)
    }

    return result.body()
}