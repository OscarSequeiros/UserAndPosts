package co.com.ceiba.mobile.pruebadeingreso.presentation

import androidx.lifecycle.ViewModel
import co.com.ceiba.mobile.pruebadeingreso.domain.GetAllUsersUseCase
import co.com.ceiba.mobile.pruebadeingreso.presentation.state.UsersUiState
import co.com.ceiba.mobile.pruebadeingreso.presentation.state.UsersUiState.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class UsersViewModel @Inject constructor(
        private val getUsersUseCase: GetAllUsersUseCase
) : ViewModel() {

    private val _stateFlow = MutableStateFlow<UsersUiState>(IdleState)

    val stateFlow: StateFlow<UsersUiState>
        get() = _stateFlow

    fun getUsers() {
        getUsersUseCase()
                .onEach { users ->  _stateFlow.value = SuccessState(users) }
                .onStart { _stateFlow.value = LoadingState }
                .catch { error -> _stateFlow.value = ErrorState(error) }
    }
}