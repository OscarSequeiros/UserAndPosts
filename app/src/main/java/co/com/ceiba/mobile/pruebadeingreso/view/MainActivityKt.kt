package co.com.ceiba.mobile.pruebadeingreso.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import co.com.ceiba.mobile.pruebadeingreso.R
import co.com.ceiba.mobile.pruebadeingreso.databinding.ActivityMainBinding
import co.com.ceiba.mobile.pruebadeingreso.domain.model.User
import co.com.ceiba.mobile.pruebadeingreso.presentation.UsersViewModel
import co.com.ceiba.mobile.pruebadeingreso.presentation.state.UsersUiState
import co.com.ceiba.mobile.pruebadeingreso.util.showToast
import co.com.ceiba.mobile.pruebadeingreso.view.adapter.UsersAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivityKt : AppCompatActivity() {

    private val viewModel: UsersViewModel by viewModels()

    private lateinit var binding: ActivityMainBinding

    private val adapter = UsersAdapter { userId ->
        navigateToDetails(userId)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupRecycler()
        observeUiState()
    }

    private fun setupRecycler() {
        val mLayoutManager = LinearLayoutManager(this)
        binding.recyclerViewSearchResults.layoutManager = mLayoutManager
        binding.recyclerViewSearchResults.adapter = adapter
    }

    private fun observeUiState() {
        lifecycleScope.launch {
            viewModel.getUsers().collect { uiState -> render(uiState) }
        }
    }

    private fun render(uiState: UsersUiState) {
        when (uiState) {
            is UsersUiState.LoadingState -> showLoading()
            is UsersUiState.SuccessState -> showElements(uiState.users)
            is UsersUiState.ErrorState -> showError(uiState.error)
            is UsersUiState.EmptyState -> showEmpty()
        }
    }

    private fun showLoading() {
        binding.progressLoading.visibility = View.VISIBLE
    }

    private fun showElements(users: List<User>) {
        hideLoading()
        adapter.users = users
    }

    private fun navigateToDetails(userId: Long) {
        val intent = PostActivityKt.buildIntent(this, userId)
        startActivity(intent)
    }

    private fun showError(error: Throwable) {
        hideLoading()
        showToast(getString(R.string.generic_error), Toast.LENGTH_SHORT)

        // Todo: Track error, Crashlytics could be a good idea
        error.printStackTrace()
    }

    private fun showEmpty() {
        hideLoading()
        showToast(getString(R.string.list_is_empty), Toast.LENGTH_LONG)
    }

    private fun hideLoading() {
        binding.progressLoading.visibility = View.GONE
    }
}