package co.com.ceiba.mobile.pruebadeingreso.presentation.state

import co.com.ceiba.mobile.pruebadeingreso.domain.model.UserWithPosts

sealed class UserDetailsUiState {

    object LoadingState : UserDetailsUiState()

    data class SuccessState(val user: UserWithPosts) : UserDetailsUiState()

    data class ErrorState(val error: Throwable) : UserDetailsUiState()
}