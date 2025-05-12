package com.github.itisme0402.happybirthday

import androidx.core.bundle.Bundle
import androidx.core.uri.UriUtils
import androidx.navigation.NavType
import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.Json
import kotlinx.serialization.serializer

class JsonSerializableNavType<T : Any>(
    private val serializer: KSerializer<T>,
) : NavType<T>(isNullableAllowed = false) {

    override fun put(bundle: Bundle, key: String, value: T) {
        bundle.putString(key, value.encodedAsString())
    }

    override fun get(bundle: Bundle, key: String): T {
        return parseValue(bundle.getString(key)!!)
    }

    override fun serializeAsValue(value: T): String {
        return UriUtils.encode(value.encodedAsString())
    }

    override fun parseValue(value: String): T {
        return value.decodedFromString()
    }

    private inline fun T.encodedAsString(): String = Json.encodeToString(serializer, this)

    private inline fun String.decodedFromString(): T = Json.decodeFromString(serializer, this)

    companion object {
        inline fun <reified T : Any> create(): JsonSerializableNavType<T> {
            return JsonSerializableNavType(serializer<T>())
        }
    }
}
