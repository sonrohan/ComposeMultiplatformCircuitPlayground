package com.sonrohan.composemultiplatformcircuitplayground

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.slack.circuit.backstack.rememberSaveableBackStack
import com.slack.circuit.foundation.Circuit
import com.slack.circuit.foundation.CircuitCompositionLocals
import com.slack.circuit.foundation.NavigableCircuitContent
import com.slack.circuit.foundation.rememberCircuitNavigator
import com.slack.circuit.runtime.Navigator
import com.sonrohan.composemultiplatformcircuitplayground.screens.account.AccountContent
import com.sonrohan.composemultiplatformcircuitplayground.screens.account.AccountPresenter
import com.sonrohan.composemultiplatformcircuitplayground.screens.account.AccountScreen
import com.sonrohan.composemultiplatformcircuitplayground.screens.home.HomeContent
import com.sonrohan.composemultiplatformcircuitplayground.screens.home.HomePresenter
import com.sonrohan.composemultiplatformcircuitplayground.screens.home.HomeScreen
import com.sonrohan.composemultiplatformcircuitplayground.ui.theme.ComposeMultiplatformCircuitPlaygroundTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val backStack = rememberSaveableBackStack(root = HomeScreen)
            val navigator = rememberCircuitNavigator(backStack)

            val circuit = getCircuit(
                navigator = navigator,
            )

            ComposeMultiplatformCircuitPlaygroundTheme {
                CircuitCompositionLocals(circuit) {
                    NavigableCircuitContent(navigator, backStack)
                }
            }
        }
    }

    private fun getCircuit(
        navigator: Navigator,
    ) = Circuit.Builder()
        // Home
        .addPresenter<HomeScreen, HomeScreen.State>(HomePresenter(navigator = navigator))
        .addUi<HomeScreen, HomeScreen.State> { state, modifier -> HomeContent(state, modifier) }
        // Account
        .addPresenter<AccountScreen, AccountScreen.State>(AccountPresenter(navigator = navigator))
        .addUi<AccountScreen, AccountScreen.State> { state, modifier -> AccountContent(state, modifier) }
        .build()
}
