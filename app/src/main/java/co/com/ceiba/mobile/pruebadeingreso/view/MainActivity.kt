package co.com.ceiba.mobile.pruebadeingreso.view

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
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
class MainActivity : AppCompatActivity() {

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
        getAllUsers()
        observeEditText()
    }

    private fun setupRecycler() {
        val mLayoutManager = LinearLayoutManager(this)
        binding.recyclerViewSearchResults.layoutManager = mLayoutManager
        binding.recyclerViewSearchResults.adapter = adapter
    }

    private fun getAllUsers() {
        getUsersByName()
    }

    private fun observeEditText() {
        binding.editTextSearch.addTextChangedListener(
                object : TextWatcher {
                    override fun afterTextChanged(s: Editable) {
                        val value = s.toString()
                        getUsersByName(value)
                    }

                    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

                    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
                }
        )
    }

    private fun getUsersByName(name: String = "") {
        lifecycleScope.launch {
            viewModel.getUsers(name).collect { uiState -> render(uiState) }
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
        binding.textEmpty.visibility = View.GONE
        adapter.users = users
    }

    private fun navigateToDetails(userId: Long) {
        val intent = PostActivity.buildIntent(this, userId)
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
        adapter.users = emptyList()
        binding.textEmpty.visibility = View.VISIBLE
    }

    private fun hideLoading() {
        binding.progressLoading.visibility = View.GONE
    }
}