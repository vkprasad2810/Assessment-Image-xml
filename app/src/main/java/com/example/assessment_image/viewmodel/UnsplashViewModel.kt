package com.example.assessment_image.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.assessment_image.repo.ListPostsRepo
import com.example.assessment_image.model.UnsplashResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class UnsplashViewModel @Inject constructor(repository: ListPostsRepo) : ViewModel() {

    val imgData: Flow<PagingData<UnsplashResponse>> = repository.getData().cachedIn(viewModelScope)

}