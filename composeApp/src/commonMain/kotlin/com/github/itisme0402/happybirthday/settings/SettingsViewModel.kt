package com.github.itisme0402.happybirthday.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.itisme0402.happybirthday.domain.BirthdayInfo
import com.github.itisme0402.happybirthday.network.WebSocketClient
import io.github.aakira.napier.Napier
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SettingsViewModel(
    // Injecting WebSocketClient directly here, since additional abstraction would be redundant in this case
    private val webSocketClient: WebSocketClient,
) : ViewModel() {

    val greetingFlow: Flow<BirthdayInfo> = webSocketClient.birthdayInfoFlow

    private val _currentErrorMessage = MutableStateFlow<String?>(null)
    val currentErrorMessage: StateFlow<String?> = _currentErrorMessage

    private var currentGreetJob: Job? = null
        set(value) {
            _currentErrorMessage.value = null
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
                _currentErrorMessage.value = e.message
            }
        }
    }

    fun consumeError() {
        _currentErrorMessage.value = null
    }
}
