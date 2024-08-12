package org.sonrohan.composemultiplatformcircuitplayground

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import com.slack.circuit.backstack.rememberSaveableBackStack
import com.slack.circuit.foundation.Circuit
import com.slack.circuit.foundation.CircuitCompositionLocals
import com.slack.circuit.foundation.NavigableCircuitContent
import com.slack.circuit.foundation.rememberCircuitNavigator
import com.slack.circuit.runtime.Navigator
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.sonrohan.composemultiplatformcircuitplayground.screens.account.AccountContent
import org.sonrohan.composemultiplatformcircuitplayground.screens.account.AccountPresenter
import org.sonrohan.composemultiplatformcircuitplayground.screens.account.AccountScreen
import org.sonrohan.composemultiplatformcircuitplayground.screens.home.HomeContent
import org.sonrohan.composemultiplatformcircuitplayground.screens.home.HomePresenter
import org.sonrohan.composemultiplatformcircuitplayground.screens.home.HomeScreen

//class MainActivity : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
//        setContent {
//            val backStack = rememberSaveableBackStack(root = HomeScreen)
//            val navigator = rememberCircuitNavigator(backStack)
//
//            val circuit = getCircuit(
//                navigator = navigator,
//            )
//
//            CircuitCompositionLocals(circuit) {
//                NavigableCircuitContent(navigator, backStack)
//            }
//        }
//    }
//
//    private fun getCircuit(
//        navigator: Navigator,
//    ) = Circuit.Builder()
//        // Home
//        .addPresenter<HomeScreen, HomeScreen.State>(HomePresenter(navigator = navigator))
//        .addUi<HomeScreen, HomeScreen.State> { state, modifier -> HomeContent(state, modifier) }
//        // Account
//        .addPresenter<AccountScreen, AccountScreen.State>(AccountPresenter(navigator = navigator))
//        .addUi<AccountScreen, AccountScreen.State> { state, modifier -> AccountContent(state, modifier) }
//        .build()
//}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            App()
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    App()
}