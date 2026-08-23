package premiumbutchers.meat.premiumbutcherpick.di

import premiumbutchers.meat.premiumbutcherpick.data.datastore.RUWCZOnboardingPrefs
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataStoreModule = module {
    single { RUWCZOnboardingPrefs(androidContext()) }
}