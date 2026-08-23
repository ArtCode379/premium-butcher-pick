package premiumbutchers.meat.premiumbutcherpick.ui.composable.screen.cart

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import premiumbutchers.meat.premiumbutcherpick.R
import premiumbutchers.meat.premiumbutcherpick.ui.composable.shared.RUWCZContentWrapper
import premiumbutchers.meat.premiumbutcherpick.ui.composable.shared.RUWCZEmptyView
import premiumbutchers.meat.premiumbutcherpick.ui.state.CartItemUiState
import premiumbutchers.meat.premiumbutcherpick.ui.state.DataUiState
import premiumbutchers.meat.premiumbutcherpick.ui.viewmodel.CartViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun CartScreen(
    modifier: Modifier = Modifier,
    viewModel: CartViewModel = koinViewModel(),
    onNavigateToCheckoutScreen: () -> Unit,
) {
    val cartItemsState by viewModel.cartItemsState.collectAsStateWithLifecycle()
    val totalPrice by viewModel.totalPrice.collectAsStateWithLifecycle()

    val onPlusItemClick = { itemId: Int ->
        viewModel.incrementProductInCart(itemId)
    }

    val onMinusItemClick = { itemId: Int ->
        viewModel.decrementItemInCart(itemId)
    }

    CartScreenContent(
        cartItemsState = cartItemsState,
        modifier = modifier,
        totalPrice = totalPrice,
        onPlusItemClick = onPlusItemClick,
        onMinusItemClick = onMinusItemClick,
        onCompleteOrderButtonClick = onNavigateToCheckoutScreen,
    )
}

@Composable
private fun CartScreenContent(
    cartItemsState: DataUiState<List<CartItemUiState>>,
    modifier: Modifier = Modifier,
    totalPrice: Double,
    onPlusItemClick: (Int) -> Unit,
    onMinusItemClick: (Int) -> Unit,
    onCompleteOrderButtonClick: () -> Unit,
) {
    Column(modifier = modifier) {

        RUWCZContentWrapper(
            dataState = cartItemsState,

            dataPopulated = {
                val data = (cartItemsState as DataUiState.Populated).data
                LazyColumn(modifier = Modifier.weight(1f)) {
                    items(data, key = { it.productId }) { item ->
                        Card(Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 6.dp)) {
                            androidx.compose.foundation.layout.Row(
                                Modifier.padding(14.dp),
                                verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
                            ) {
                                Column(Modifier.weight(1f)) {
                                    Text(item.productTitle)
                                    Text(stringResource(R.string.ruwcz_price, item.productPrice))
                                }
                                IconButton(onClick = { onMinusItemClick(item.productId) }) {
                                    Text("−")
                                }
                                Text(item.quantity.toString())
                                IconButton(onClick = { onPlusItemClick(item.productId) }) {
                                    Text("+")
                                }
                            }
                        }
                    }
                }
                Column(Modifier.fillMaxWidth().padding(16.dp)) {
                    Text("Total: ${stringResource(R.string.ruwcz_price, totalPrice)}")
                    Button(onClick = onCompleteOrderButtonClick, modifier = Modifier.fillMaxWidth()) {
                        Text("Proceed to Checkout")
                    }
                }
            },

            dataEmpty = {
                RUWCZEmptyView(
                    primaryText = stringResource(R.string.ruwcz_cart_state_empty_primary_text),
                    modifier = Modifier.fillMaxSize(),
                )
            },
        )
    }
}
