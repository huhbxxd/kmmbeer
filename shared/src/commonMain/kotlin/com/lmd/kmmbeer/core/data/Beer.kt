package com.lmd.kmmbeer.core.data

import kotlinx.serialization.Serializable

@Serializable
data class Beer(
    val id: Long,
    val name: String? = null
)