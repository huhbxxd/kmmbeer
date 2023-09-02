package com.lmd.kmmbeer.core.di

import org.koin.core.context.startKoin

fun appModule() = listOf(
    networkModule
)

fun initKoin() {
    startKoin {
        modules(appModule())
    }
}