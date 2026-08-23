package premiumbutchers.meat.premiumbutcherpick.ui.composable.screen.order

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import premiumbutchers.meat.premiumbutcherpick.R
import premiumbutchers.meat.premiumbutcherpick.data.entity.OrderEntity
import premiumbutchers.meat.premiumbutcherpick.ui.composable.shared.RUWCZContentWrapper
import premiumbutchers.meat.premiumbutcherpick.ui.composable.shared.RUWCZEmptyView
import premiumbutchers.meat.premiumbutcherpick.ui.state.DataUiState
import premiumbutchers.meat.premiumbutcherpick.ui.viewmodel.OrderViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun OrdersScreen(
    modifier: Modifier = Modifier,
    viewModel: OrderViewModel = koinViewModel(),
) {
    val ordersState by viewModel.ordersState.collectAsState()

    OrdersContent(
        ordersState = ordersState,
        modifier = modifier,
    )
}

@Composable
private fun OrdersContent(
    ordersState: DataUiState<List<OrderEntity>>,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {

        RUWCZContentWrapper(
            dataState = ordersState,

            dataPopulated = {
                val data = (ordersState as DataUiState.Populated).data

            },

            dataEmpty = {
                RUWCZEmptyView(
                    primaryText = stringResource(R.string.ruwcz_orders_state_empty_primary_text),
                    modifier = Modifier.fillMaxSize(),
                )
            },
        )
    }
}