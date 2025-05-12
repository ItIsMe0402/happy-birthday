package com.github.itisme0402.happybirthday

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RemoteBirthdayInfo(
    val name: String,
    @SerialName("dob")
    val dateUtc: Long,
    val theme: String,
)
