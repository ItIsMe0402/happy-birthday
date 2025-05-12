package com.github.itisme0402.happybirthday

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.aakira.napier.Napier
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class SettingsViewModel(
    private val webSocketClient: WebSocketClient,
) : ViewModel() {

    val greetingFlow: Flow<BirthdayInfo> = webSocketClient.birthdayInfoFlow

    private var currentGreetJob: Job? = null
        set(value) {
            field?.cancel()
            field = value
        }

    fun connectAndGreet(ip: String, port: String) {
        if (ip.isBlank() || port.isBlank() || port.toIntOrNull() == null) {
            return
        }
        currentGreetJob = viewModelScope.launch {
            try {
                webSocketClient.connectAndGreet(ip, port.toInt())
            } catch (ignored: CancellationException) {
                // ignore
            } catch (e: Exception) {
                Napier.e("Failed to connect & get greeting", e)
            }
        }
    }
}
