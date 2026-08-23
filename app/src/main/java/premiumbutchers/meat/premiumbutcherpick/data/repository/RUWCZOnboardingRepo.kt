package premiumbutchers.meat.premiumbutcherpick.data.repository

import premiumbutchers.meat.premiumbutcherpick.data.datastore.RUWCZOnboardingPrefs
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class RUWCZOnboardingRepo(
    private val ruwczOnboardingStoreManager: RUWCZOnboardingPrefs,
    private val coroutineDispatcher: CoroutineDispatcher,
) {

    fun observeOnboardingState(): Flow<Boolean?> {
        return ruwczOnboardingStoreManager.onboardedStateFlow
    }

    suspend fun setOnboardingState(state: Boolean) {
        withContext(coroutineDispatcher) {
            ruwczOnboardingStoreManager.setOnboardedState(state)
        }
    }
}