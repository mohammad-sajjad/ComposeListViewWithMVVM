package com.myapplication.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.myapplication.ImageListItem
import com.myapplication.remote.ApiController

class ImagePagingSource(
    private val apiController: ApiController
) : PagingSource<Int, ImageListItem>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ImageListItem> {
        return try {
            val page = params.key ?: 1
            val response = apiController.getImages(page)

            val data = response.hits.map {
                ImageListItem(
                    image = it.largeImageURL,
                    title = it.user,
                    details = it.tags
                )
            }

            LoadResult.Page(
                data = data,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (data.isEmpty()) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, ImageListItem>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }
}
