package com.lmd.kmmbeer

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform