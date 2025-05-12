package com.github.itisme0402.happybirthday

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
