package com.myapplication.repository

import com.myapplication.ImageListService
import com.myapplication.ImagesModel
import com.myapplication.remote.ApiResponseCallBack
import javax.inject.Inject

class ImageListRepository @Inject constructor(private val imageService: ImageListService) {
    suspend fun getImages(page: Int, responseCallBack: ApiResponseCallBack<ImagesModel>) {
        imageService.getImages(page, responseCallBack)
    }
}