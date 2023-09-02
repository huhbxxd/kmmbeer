package com.lmd.kmmbeer.core.network.client

import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp

actual fun getPlatformHttpClient() : HttpClient {
    return HttpClient(OkHttp)
}