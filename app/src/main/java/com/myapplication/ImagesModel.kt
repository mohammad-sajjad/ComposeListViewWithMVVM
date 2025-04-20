package com.myapplication

import com.google.gson.annotations.SerializedName

data class ImagesModel(
    @SerializedName("hits")
    val hits: List<Hit>,
    @SerializedName("total")
    val total: Int, // 245873
    @SerializedName("totalHits")
    val totalHits: Int // 500
) {
    data class Hit(
        @SerializedName("collections")
        val collections: Int, // 167
        @SerializedName("comments")
        val comments: Int, // 175
        @SerializedName("downloads")
        val downloads: Int, // 138157
        @SerializedName("id")
        val id: Int, // 8089783
        @SerializedName("imageHeight")
        val imageHeight: Int, // 3168
        @SerializedName("imageSize")
        val imageSize: Int, // 4356470
        @SerializedName("imageWidth")
        val imageWidth: Int, // 4752
        @SerializedName("largeImageURL")
        val largeImageURL: String, // https://pixabay.com/get/g0cd95236c9388e3dff03d771e2057d5af6c3b2d439dc630b79be0eff481305b0059b7342448f16f03208cb1abc89636dd89c22719da75b71dd689e8d1fd78289_1280.jpg
        @SerializedName("likes")
        val likes: Int, // 336
        @SerializedName("pageURL")
        val pageURL: String, // https://pixabay.com/photos/wolf-wild-canids-carnivorous-mammal-8089783/
        @SerializedName("previewHeight")
        val previewHeight: Int, // 100
        @SerializedName("previewURL")
        val previewURL: String, // https://cdn.pixabay.com/photo/2023/06/26/13/41/wolf-8089783_150.jpg
        @SerializedName("previewWidth")
        val previewWidth: Int, // 150
        @SerializedName("tags")
        val tags: String, // wolf, wild canids, carnivorous mammal, animal, fauna, nature, animal, animal, animal, animal, animal
        @SerializedName("type")
        val type: String, // photo
        @SerializedName("user")
        val user: String, // maminounou
        @SerializedName("user_id")
        val userId: Int, // 3039313
        @SerializedName("userImageURL")
        val userImageURL: String, // https://cdn.pixabay.com/user/2023/01/16/15-11-39-343_250x250.jpg
        @SerializedName("views")
        val views: Int, // 150485
        @SerializedName("webformatHeight")
        val webformatHeight: Int, // 427
        @SerializedName("webformatURL")
        val webformatURL: String, // https://pixabay.com/get/g484d08cc201d1ba73e9297c21acffd764a3ed7ace172af4d5ee655e444d7cb1f3bfbaeb952fd927325dac55535d6dbf8a30038564131e1bcca7f41456a6b1001_640.jpg
        @SerializedName("webformatWidth")
        val webformatWidth: Int // 640
    )
}


