package com.github.itisme0402.happybirthday.navigation

import com.github.itisme0402.happybirthday.domain.BirthdayInfo
import kotlinx.serialization.Serializable

@Serializable
sealed class Destination {

    @Serializable
    data object Settings : Destination()

    @Serializable
    data class Greeting(
        val birthdayInfo: BirthdayInfo,
    ) : Destination()
}
