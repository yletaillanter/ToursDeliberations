package com.ylt.toursdeliberations.model

import com.google.gson.annotations.SerializedName

class Delib(
    @SerializedName("id") val id: String,
    @SerializedName("format") val format: String,
    @SerializedName("filename") val filename: String,
    @SerializedName("width") val width: String,
    @SerializedName("height") val height: String,
    @SerializedName("thumbnail") val thumbnail: String)

/*
Data example:

"delib_url":{
    "format":"pdf",
    "filename":"1596223483_28377.pdf",
    "width":300,
    "id":"88809a876368ff53eb78a940722cc3cc",
    "height":300,
    "thumbnail":false
}*/