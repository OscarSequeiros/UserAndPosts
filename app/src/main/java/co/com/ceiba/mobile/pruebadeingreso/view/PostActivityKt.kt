package co.com.ceiba.mobile.pruebadeingreso.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import co.com.ceiba.mobile.pruebadeingreso.R
import co.com.ceiba.mobile.pruebadeingreso.databinding.ActivityPostBinding
import co.com.ceiba.mobile.pruebadeingreso.domain.model.Post
import co.com.ceiba.mobile.pruebadeingreso.domain.model.User
import co.com.ceiba.mobile.pruebadeingreso.domain.model.UserWithPosts
import co.com.ceiba.mobile.pruebadeingreso.presentation.UserDetailsViewModel
import co.com.ceiba.mobile.pruebadeingreso.presentation.state.UserDetailsUiState
import co.com.ceiba.mobile.pruebadeingreso.presentation.state.UserDetailsUiState.*
import co.com.ceiba.mobile.pruebadeingreso.util.showToast
import co.com.ceiba.mobile.pruebadeingreso.view.adapter.PostsAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PostActivityKt : AppCompatActivity() {

    private val viewModel: UserDetailsViewModel by viewModels()

    private lateinit var binding: ActivityPostBinding

    private val userId: Long by lazy {
        intent?.getLongExtra(USER_ID_PARAM, 0L) ?: 0L
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPostBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupRecycler()
        observeUiState()
    }

    private fun observeUiState() {
        lifecycleScope.launch {
            viewModel.getDetails(userId)
                    .collect { uiState -> render(uiState) }
        }
    }

    private fun render(uiState: UserDetailsUiState) {
        when (uiState) {
            is LoadingState -> showLoading()
            is SuccessState -> showUserAndPosts(uiState.user)
            is ErrorState -> showError(uiState.error)
        }
    }

    private fun showLoading() {
        binding.contentCard.visibility = View.INVISIBLE
        binding.progressLoading.visibility = View.VISIBLE
    }

    private fun showUserAndPosts(userWitPosts: UserWithPosts) {
        showUserDetails(userWitPosts.user)
        showPosts(userWitPosts.posts)
    }

    private fun showUserDetails(user: User) {
        with(binding) {
            name.text = user.name
            phone.text = user.phoneNumber
            email.text = user.email
        }
    }

    private fun showPosts(posts: List<Post>) {
        hideLoading()
        binding.contentCard.visibility = View.VISIBLE
        setupRecycler()
        val adapter = PostsAdapter(posts)
        binding.recyclerViewPostsResults.adapter = adapter
    }

    private fun setupRecycler() {
        val mLayoutManager = LinearLayoutManager(this)
        binding.recyclerViewPostsResults.layoutManager = mLayoutManager
    }

    private fun showError(error: Throwable) {
        hideLoading()
        showToast(getString(R.string.generic_error), Toast.LENGTH_SHORT)

        // Todo: Track error, Crashlytics could be a good idea
        error.printStackTrace()
    }

    private fun hideLoading() {
        binding.progressLoading.visibility = View.GONE
    }

    companion object {

        private const val USER_ID_PARAM = "user_id_param"

        fun buildIntent(context: Context, userId: Long): Intent {
            return Intent(context, PostActivityKt::class.java).also { intent ->
                intent.putExtra(USER_ID_PARAM, userId)
            }
        }
    }
}