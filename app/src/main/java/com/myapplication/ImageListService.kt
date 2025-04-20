package com.myapplication

import com.myapplication.core.BaseService
import com.myapplication.remote.ApiController
import com.myapplication.remote.ApiResponseCallBack
import javax.inject.Inject

class ImageListService @Inject constructor(private val apiController: ApiController) : BaseService(), ImageListController {

    override suspend fun getImages(page: Int, responseCallback: ApiResponseCallBack<ImagesModel>) {
        val call = apiController.getImages(page)
//        makeRequest(call, responseCallback)
    }
}