package com.myapplication.provideer

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.myapplication.ImageListItem
import com.myapplication.models.SliderItems

class ImagePreviewParameterProvider : PreviewParameterProvider<ImageListItem> {
    override val values = sequenceOf(
        ImageListItem(
            image = "https://pixabay.com/get/g0cd95236c9388e3dff03d771e2057d5af6c3b2d439dc630b79be0eff481305b0059b7342448f16f03208cb1abc89636dd89c22719da75b71dd689e8d1fd78289_1280.jpg",
            title = "John Doe",
            details = "Nature"
        ),ImageListItem(
            image = "https://pixabay.com/get/g0cd95236c9388e3dff03d771e2057d5af6c3b2d439dc630b79be0eff481305b0059b7342448f16f03208cb1abc89636dd89c22719da75b71dd689e8d1fd78289_1280.jpg",
            title = "John Doe",
            details = "Nature"
        ),
    )
}

class SliderPreviewParameterProvider : PreviewParameterProvider<SliderItems> {
    override val values = sequenceOf(
        SliderItems(
            image = "https://pixabay.com/get/g0cd95236c9388e3dff03d771e2057d5af6c3b2d439dc630b79be0eff481305b0059b7342448f16f03208cb1abc89636dd89c22719da75b71dd689e8d1fd78289_1280.jpg",
            title = "John Doe",
        ),SliderItems(
            image = "https://pixabay.com/get/g0cd95236c9388e3dff03d771e2057d5af6c3b2d439dc630b79be0eff481305b0059b7342448f16f03208cb1abc89636dd89c22719da75b71dd689e8d1fd78289_1280.jpg",
            title = "John Doe",
        ),
    )
}


