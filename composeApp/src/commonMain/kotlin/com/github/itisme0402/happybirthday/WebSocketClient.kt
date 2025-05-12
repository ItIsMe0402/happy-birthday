package com.github.itisme0402.happybirthday

import io.ktor.client.HttpClient
import io.ktor.client.plugins.websocket.receiveDeserialized
import io.ktor.client.plugins.websocket.webSocket
import io.ktor.client.request.url
import io.ktor.websocket.send
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.isActive
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

class WebSocketClient(
    private val httpClient: HttpClient,
) {
    private val _birthdayInfoFlow = MutableSharedFlow<BirthdayInfo>()
    val birthdayInfoFlow: SharedFlow<BirthdayInfo> = _birthdayInfoFlow.asSharedFlow()

    suspend fun connectAndGreet(
        ip: String,
        port: Int,
    ) {
        httpClient.webSocket(
            request = {
                url("ws", ip, port, PATH)
            },
        ) {
            send("HappyBirthday")
            var remoteBirthdayInfo: RemoteBirthdayInfo?
            do {
                remoteBirthdayInfo = receiveDeserialized<RemoteBirthdayInfo?>()
            } while (remoteBirthdayInfo == null && isActive)
            if (!isActive) throw CancellationException("Cancelled while waiting for birthday info")
            remoteBirthdayInfo!!
            val birthdayInfo = BirthdayInfo(
                name = remoteBirthdayInfo.name,
                date = Instant.fromEpochMilliseconds(
                    remoteBirthdayInfo.dateUtc,
                )
                    .toLocalDateTime(TimeZone.currentSystemDefault())
                    .date,
                theme = remoteBirthdayInfo.theme,
            )
            _birthdayInfoFlow.emit(birthdayInfo)
        }
    }

    private companion object {
        const val PATH = "/nanit"
    }
}
