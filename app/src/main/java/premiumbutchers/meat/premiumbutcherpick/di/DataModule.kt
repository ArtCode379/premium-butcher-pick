package premiumbutchers.meat.premiumbutcherpick.di

import premiumbutchers.meat.premiumbutcherpick.data.repository.CartRepository
import premiumbutchers.meat.premiumbutcherpick.data.repository.RUWCZOnboardingRepo
import premiumbutchers.meat.premiumbutcherpick.data.repository.OrderRepository
import premiumbutchers.meat.premiumbutcherpick.data.repository.ProductRepository

import org.koin.core.qualifier.named
import org.koin.dsl.module

val dataModule = module {
    includes(databaseModule, dataStoreModule)

    single {
        RUWCZOnboardingRepo(
            ruwczOnboardingStoreManager = get(),
            coroutineDispatcher = get(named("IO"))
        )
    }

    single { ProductRepository() }

    single {
        CartRepository(
            cartItemDao = get(),
            coroutineDispatcher = get(named("IO"))
        )
    }

    single {
        OrderRepository(
            orderDao = get(),
            coroutineDispatcher = get(named("IO"))
        )
    }
}