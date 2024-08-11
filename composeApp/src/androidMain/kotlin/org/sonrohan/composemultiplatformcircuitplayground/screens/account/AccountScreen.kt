package org.sonrohan.composemultiplatformcircuitplayground.screens.account

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
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
import com.valentinilk.shimmer.shimmer
import kotlinx.coroutines.delay
import kotlinx.parcelize.Parcelize

@Parcelize
data object AccountScreen : Screen {
    data class State(
        val firstName: String?,
        val lastName: String?,
        val email: String?,
        val eventSink: (Event) -> Unit,
    ) : CircuitUiState

    sealed class Event : CircuitUiEvent {
        data object Back : Event()
    }
}

@Composable
fun AccountContent(
    state: AccountScreen.State,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = {
                    Text("Account")
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            state.eventSink(
                                AccountScreen.Event.Back,
                            )
                    }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Go Back",
                        )
                    }
                }
            )
        },
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding),
            ) {
                if (state.firstName != null) {
                    Text(
                        text = "First Name: ${state.firstName}"
                    )
                } else {
                    Text(
                        modifier = Modifier
                            .shimmer(),
                        text = "First Name: ..."
                    )
                }

                if (state.lastName != null) {
                    Text(
                        text = "Last Name: ${state.lastName}"
                    )
                } else {
                    Text(
                        modifier = Modifier
                            .shimmer(),
                        text = "Last Name: ..."
                    )
                }

                if (state.email != null) {
                    Text(
                        text = "Email: ${state.email }"
                    )
                } else {
                    Text(
                        modifier = Modifier
                            .shimmer(),
                        text = "Email: ..."
                    )
                }
            }
        }
    )
}

class AccountPresenter(
    private val navigator: Navigator,
) : Presenter<AccountScreen.State> {
    @Composable
    override fun present(): AccountScreen.State {
        var firstName: String? by remember {
            mutableStateOf(null)
        }

        var lastName: String? by remember {
            mutableStateOf(null)
        }

        var email: String? by remember {
            mutableStateOf(null)
        }

        LaunchedEffect(Unit) {
            // Simulate loading.
            delay(5000)

            firstName = "Rohan"
            lastName = "Harrison"
            email = "rohan@rohan.com"
        }

        return AccountScreen.State(
            firstName = firstName,
            lastName = lastName,
            email = email,
            eventSink = { event ->
                when (event) {
                    AccountScreen.Event.Back -> {
                        navigator.pop()
                    }
                }
            },
        )
    }
}