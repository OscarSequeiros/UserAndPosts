package co.com.ceiba.mobile.pruebadeingreso.presentation

import androidx.lifecycle.ViewModel
import co.com.ceiba.mobile.pruebadeingreso.domain.GetAllUsersUseCase
import co.com.ceiba.mobile.pruebadeingreso.domain.GetUsersByNameUseCase
import co.com.ceiba.mobile.pruebadeingreso.presentation.state.UsersUiState
import co.com.ceiba.mobile.pruebadeingreso.presentation.state.UsersUiState.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class UsersViewModel @Inject constructor(
        private val getUsersByNameUseCase: GetUsersByNameUseCase,
) : ViewModel() {

    fun getUsers(name: String): Flow<UsersUiState> {
        return getUsersByNameUseCase(name)
                .map { users -> SuccessState(users) as UsersUiState }
                .onStart { emit(LoadingState) }
                .catch { error -> emit(ErrorState(error)) }
                .flowOn(Dispatchers.IO)
    }
}