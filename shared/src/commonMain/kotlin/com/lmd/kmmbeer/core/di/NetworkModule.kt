package com.lmd.kmmbeer.core.di

import com.lmd.kmmbeer.core.network.Router
import com.lmd.kmmbeer.core.network.client.createHttpClient
import org.koin.core.qualifier.named
import org.koin.dsl.module

val networkModule = module {
    single(named("httpClient")) { createHttpClient() }

    single {
        Router(
            get(
                named("httpClient")
            )
        )
    }
}