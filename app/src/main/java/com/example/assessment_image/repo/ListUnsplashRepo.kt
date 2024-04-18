package com.example.assessment_image.repo

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.assessment_image.model.UnsplashResponse
import com.example.assessment_image.retrofit.ApiService
import com.example.assessment_image.utils.UnsplashPagingSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


 const val PAGE_SIZE = 20

class ListPostsRepo @Inject constructor(private val postsApi: ApiService) {

    fun getData(): Flow<PagingData<UnsplashResponse>> {
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                maxSize = PAGE_SIZE + (PAGE_SIZE * 2),
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                UnsplashPagingSource(postsApi = postsApi)
            }
        ).flow
    }


}