package com.myapplication.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.myapplication.ImageListItem
import com.myapplication.ImagesModel
import com.myapplication.models.SliderItems
import com.myapplication.remote.ApiController
import com.myapplication.remote.ApiResponseCallBack
import com.myapplication.remote.DataFetchState
import com.myapplication.remote.createStateMachine
import com.myapplication.repository.ImageListRepository
import com.myapplication.repository.ImagePagingSource
import com.satta.d_matka.error.StandardError
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val apiController: ApiController): ViewModel() {
    val sliderImages = mutableListOf<SliderItems>()


    init {
        prepareSliderItems()
    }


    val pagerFlow = Pager(
        config = PagingConfig(
            pageSize = 20, // should match API per_page
            enablePlaceholders = false
        ),
        pagingSourceFactory = {
            ImagePagingSource(apiController)
        }
    ).flow.cachedIn(viewModelScope)


    var searchQuery = mutableStateOf("")
        private set

    fun onSearchChange(query: String) {
        searchQuery.value = query
    }


    private fun transformDataToImageListItem(data: ImagesModel): MutableList<ImageListItem> {
        return data.hits.map { hit ->
            ImageListItem(hit.largeImageURL, hit.user, hit.tags)
        }.toMutableList()
    }

    private fun prepareSliderItems() {
        sliderImages.add(SliderItems("https://pixabay.com/get/geb4677e2de3334f0b60f68941395efba69a4f54f04d8702fdf58a76249a99e3b8c5b4a94a06103fc86d5037de372fcfac3544b41fb4a4fe8c8ce7c4a98e5600b_1280.jpg", "ignartonosbg"))
        sliderImages.add(SliderItems("https://pixabay.com/get/g18c297412bf855633462e338fa7cf8d8ed8418f9678b77183c5dcc30ce6a88f7de4eb3df322c7dcea47e214b64f5c7cc3ddb90b6bbedd59805f0ed0c59a966c7_1280.jpg", "Pixamio"))
        sliderImages.add(SliderItems("https://pixabay.com/get/g4c0cb2abe84fbff78eaabad0c1a439cc7d501731da924feb7f3773ae9189be10b9b8640ddd4d51645e6f24d845c9fa62c00c82bf140b560ff83b33396e7c5def_1280.jpg", "Couleur"))
        }
}