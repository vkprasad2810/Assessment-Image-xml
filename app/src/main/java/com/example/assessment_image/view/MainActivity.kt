package com.example.assessment_image.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.assessment_image.adapter.LoaderAdapter
import com.example.assessment_image.adapter.UnsplashAdapter
import com.example.assessment_image.databinding.ActivityMainBinding
import com.example.assessment_image.viewmodel.UnsplashViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var model: UnsplashViewModel
    private val adapter by lazy { UnsplashAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val manager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        manager.gapStrategy = StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS
        binding.recyclerView.layoutManager =manager
        binding.recyclerView.adapter = adapter.withLoadStateHeaderAndFooter(
            header = LoaderAdapter(),
            footer = LoaderAdapter()
        )

        adapter.addLoadStateListener { loadState ->

            binding.retryButton.isVisible = loadState.source.refresh is LoadState.Error
            binding.loading.isVisible = loadState.source.refresh is LoadState.Loading

        }

        binding.retryButton.setOnClickListener {
            adapter.retry()
        }

        model = ViewModelProvider(this)[UnsplashViewModel::class.java]

        lifecycleScope.launch {
            model.imgData.collectLatest {

                adapter.submitData(it)

            }
        }


    }
}