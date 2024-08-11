package com.sonrohan.composemultiplatformcircuitplayground.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.slack.circuit.runtime.CircuitUiEvent
import com.slack.circuit.runtime.CircuitUiState
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.presenter.Presenter
import com.slack.circuit.runtime.screen.Screen
import com.sonrohan.composemultiplatformcircuitplayground.screens.account.AccountScreen
import com.valentinilk.shimmer.shimmer
import kotlinx.coroutines.delay
import kotlinx.parcelize.Parcelize

@Parcelize
data object HomeScreen : Screen {
    data class State(
        val name: String?,
        val showAccountButton: Boolean,
        val eventSink: (Event) -> Unit,
    ) : CircuitUiState

    sealed class Event : CircuitUiEvent {
        data object Account: Event()
    }
}

@Composable
fun HomeContent(
    state: HomeScreen.State,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier,
        content = { innerPadding ->
            Column (
                modifier = Modifier
                    .padding(innerPadding),
            ) {
                if (state.name != null) {
                    Text(
                        text = "Welcome, ${state.name}"
                    )
                } else {
                    Text(
                        modifier = Modifier
                            .shimmer(),
                        text = "Welcome, ..."
                    )
                }

                if (state.showAccountButton) {
                    Button(onClick = {
                        state.eventSink(HomeScreen.Event.Account)
                    }) {
                        Text("Go to account screen")
                    }
                }
            }
        }
    )
}

class HomePresenter(
    private val navigator: Navigator,
) : Presenter<HomeScreen.State> {
    @Composable
    override fun present(): HomeScreen.State {
        var name: String? by remember {
            mutableStateOf(null)
        }

        val showAccountButton = name != null

        LaunchedEffect(Unit) {
            // Simulate loading.
            delay(5000)

            name = "Rohan"
        }

        return HomeScreen.State(
            name = name,
            showAccountButton = showAccountButton,
            eventSink = { event ->
                when (event) {
                    HomeScreen.Event.Account -> {
                        navigator.goTo(AccountScreen)
                    }
                }
            },
        )
    }
}