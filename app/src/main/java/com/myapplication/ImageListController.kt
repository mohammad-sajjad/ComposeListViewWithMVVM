package com.myapplication

import com.myapplication.remote.ApiResponseCallBack

interface ImageListController {
    suspend fun getImages(page: Int, responseCallback: ApiResponseCallBack<ImagesModel>)
}