package co.com.ceiba.mobile.pruebadeingreso.presentation

import co.com.ceiba.mobile.pruebadeingreso.domain.GetUserWithPostsUseCase
import co.com.ceiba.mobile.pruebadeingreso.presentation.state.UserDetailsUiState
import co.com.ceiba.mobile.pruebadeingreso.presentation.state.UserDetailsUiState.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class UserDetailsViewModel @Inject constructor(
        private val getUserWithPostsUseCase: GetUserWithPostsUseCase
) {

    fun getDetails(userId: Long): Flow<UserDetailsUiState> {
        return getUserWithPostsUseCase(userId)
                .map { user -> SuccessState(user) as UserDetailsUiState }
                .onStart { emit(LoadingState) }
                .catch { error -> emit(ErrorState(error)) }
                .flowOn(Dispatchers.IO)
    }
}