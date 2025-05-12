package com.github.itisme0402.happybirthday.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.github.itisme0402.happybirthday.domain.BirthdayInfo
import happybirthday.composeapp.generated.resources.Res
import happybirthday.composeapp.generated.resources.connect_and_greet
import happybirthday.composeapp.generated.resources.ip_address
import happybirthday.composeapp.generated.resources.port
import happybirthday.composeapp.generated.resources.settings
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Preview
@Composable
fun SettingsScreen(
    onNavigateToGreeting: (BirthdayInfo) -> Unit = {}
) {
    if (LocalInspectionMode.current) {
        SettingsScreen(
            connectAndGreet = { _, _ ->
            },
            greetingFlow = emptyFlow(),
            onNavigateToGreeting = onNavigateToGreeting,
        )
    } else {
        val viewModel = koinViewModel<SettingsViewModel>()
        SettingsScreen(
            connectAndGreet = viewModel::connectAndGreet,
            greetingFlow = viewModel.greetingFlow,
            onNavigateToGreeting = onNavigateToGreeting,
        )
    }
}

@Composable
fun SettingsScreen(
    connectAndGreet: (ip: String, port: String) -> Unit,
    greetingFlow: Flow<BirthdayInfo>,
    onNavigateToGreeting: (BirthdayInfo) -> Unit = {},
) {
    val navigateToGreeting by rememberUpdatedState(onNavigateToGreeting)
    var ip by rememberSaveable { mutableStateOf("") }
    var port by rememberSaveable { mutableStateOf("") }

    LaunchedEffect(greetingFlow) {
        greetingFlow.collect {
            navigateToGreeting(it)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(text = stringResource(Res.string.settings), style = MaterialTheme.typography.h4)

        OutlinedTextField(
            value = ip,
            onValueChange = { ip = it },
            label = { Text(stringResource(Res.string.ip_address)) },
            singleLine = true,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = port,
            onValueChange = { port = it },
            label = { Text(stringResource(Res.string.port)) },
            singleLine = true,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = { connectAndGreet(ip, port) },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text(stringResource(Res.string.connect_and_greet))
        }
    }
}
