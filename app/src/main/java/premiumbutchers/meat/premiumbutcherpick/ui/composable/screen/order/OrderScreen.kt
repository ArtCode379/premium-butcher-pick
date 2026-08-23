package premiumbutchers.meat.premiumbutcherpick.ui.composable.screen.order

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
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
                val data = (ordersState as DataUiState.Populated).data.sortedByDescending { it.timestamp }
                LazyColumn {
                    items(data, key = { it.orderNumber }) { order ->
                        Card(Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 8.dp)) {
                            Column(Modifier.padding(16.dp)) {
                                Text("Order #${order.orderNumber}", style = MaterialTheme.typography.titleMedium)
                                Text(order.description, color = MaterialTheme.colorScheme.onSurfaceVariant)
                                Text(stringResource(R.string.ruwcz_price, order.price), color = MaterialTheme.colorScheme.primary)
                                Text("Ready for collection • held for 24 hours", color = premiumbutchers.meat.premiumbutcherpick.ui.theme.Success)
                            }
                        }
                    }
                }
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
