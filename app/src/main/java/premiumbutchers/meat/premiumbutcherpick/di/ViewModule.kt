package premiumbutchers.meat.premiumbutcherpick.di

import premiumbutchers.meat.premiumbutcherpick.ui.viewmodel.AppViewModel
import premiumbutchers.meat.premiumbutcherpick.ui.viewmodel.CartViewModel
import premiumbutchers.meat.premiumbutcherpick.ui.viewmodel.CheckoutViewModel
import premiumbutchers.meat.premiumbutcherpick.ui.viewmodel.RUWCZOnboardingVM
import premiumbutchers.meat.premiumbutcherpick.ui.viewmodel.OrderViewModel
import premiumbutchers.meat.premiumbutcherpick.ui.viewmodel.ProductDetailsViewModel
import premiumbutchers.meat.premiumbutcherpick.ui.viewmodel.ProductViewModel
import premiumbutchers.meat.premiumbutcherpick.ui.viewmodel.RUWCZSplashVM
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModule = module {
    viewModel {
        AppViewModel(
            cartRepository = get()
        )
    }

    viewModel {
        RUWCZSplashVM(
            onboardingRepository = get()
        )
    }

    viewModel {
        RUWCZOnboardingVM(
            onboardingRepository = get()
        )
    }

    viewModel {
        ProductViewModel(
            productRepository = get(),
            cartRepository = get(),
        )
    }

    viewModel {
        ProductDetailsViewModel(
            productRepository = get(),
            cartRepository = get(),
        )
    }

    viewModel {
        CheckoutViewModel(
            cartRepository = get(),
            productRepository = get(),
            orderRepository = get(),
        )
    }

    viewModel {
        CartViewModel(
            cartRepository = get(),
            productRepository = get(),
        )
    }

    viewModel {
        OrderViewModel(
            orderRepository = get(),
        )
    }
}