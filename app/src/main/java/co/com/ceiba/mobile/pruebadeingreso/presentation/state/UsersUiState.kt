package co.com.ceiba.mobile.pruebadeingreso.presentation.state

import co.com.ceiba.mobile.pruebadeingreso.domain.model.User

sealed class UsersUiState {

    object LoadingState : UsersUiState()

    data class SuccessState(val users: List<User>) : UsersUiState()

    object EmptyState : UsersUiState()

    data class ErrorState(val error: Throwable) : UsersUiState()
}