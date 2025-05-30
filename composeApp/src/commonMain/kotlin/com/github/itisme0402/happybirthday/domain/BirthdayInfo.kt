package com.github.itisme0402.happybirthday.domain

import kotlinx.datetime.LocalDate
import kotlinx.serialization.Serializable

@Serializable
data class BirthdayInfo(
    val name: String,
    val date: LocalDate,
    val theme: BirthdayGreetingTheme,
)
