package com.project.shaadi_assignment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.core.view.updatePadding
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.shaadi_assignment.data.model.MatchStatus
import com.project.shaadi_assignment.databinding.ActivityMainBinding
import com.project.shaadi_assignment.ui.adapter.UserProfileAdapter
import com.project.shaadi_assignment.ui.viewmodel.UserProfileViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: UserProfileViewModel by viewModels()
    private lateinit var adapter: UserProfileAdapter
    private lateinit var windowInsetsController: WindowInsetsControllerCompat
    private var isLoading = false
    private var isLastPage = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Configure window insets
        WindowCompat.setDecorFitsSystemWindows(window, false)
        windowInsetsController = WindowCompat.getInsetsController(window, window.decorView)
        windowInsetsController.isAppearanceLightStatusBars = false
        
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        setupObservers()
        setupClickListeners()
        setupInsets()
        
        // Load initial profiles
        viewModel.fetchNewProfiles()
    }

    private fun setupRecyclerView() {
        adapter = UserProfileAdapter(
            onAccept = { profile -> viewModel.updateProfileStatus(profile.id, MatchStatus.ACCEPTED) },
            onDecline = { profile -> viewModel.updateProfileStatus(profile.id, MatchStatus.DECLINED) }
        )

        binding.rvProfiles.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = this@MainActivity.adapter
        }
    }

    private fun setupClickListeners() {
        binding.btnLoadMore.setOnClickListener {
            if (!isLoading && !isLastPage) {
                viewModel.fetchNewProfiles()
            }
        }
    }

    private fun setupObservers() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.profiles.collect { profiles ->
                    // Check if we've reached the end of available profiles
                    isLastPage = profiles.size < 10
                    adapter.submitList(profiles.toList())
                    
                    // Update load more button visibility
                    binding.btnLoadMore.visibility = if (isLastPage) View.GONE else View.VISIBLE
                }
            }
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.isLoading.collect { loading ->
                    isLoading = loading
                    binding.progressBar.visibility = if (loading) View.VISIBLE else View.GONE
                    binding.btnLoadMore.isEnabled = !loading
                }
            }
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.error.collect { error ->
                    error?.let {
                        Toast.makeText(this@MainActivity, it, Toast.LENGTH_LONG).show()
                        isLoading = false
                        binding.btnLoadMore.isEnabled = true
                    }
                }
            }
        }
    }

    private fun setupInsets() {
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { view, windowInsets ->
            val insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.updatePadding(
                top = insets.top,
                bottom = insets.bottom
            )
            WindowInsetsCompat.CONSUMED
        }
    }
}