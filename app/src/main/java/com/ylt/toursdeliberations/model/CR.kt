package com.ylt.toursdeliberations.model

import com.google.gson.annotations.SerializedName

class CR(
    @SerializedName("id") val id: String,
    @SerializedName("format") val format: String,
    @SerializedName("filename") val filename: String,
    @SerializedName("width") val width: String,
    @SerializedName("height") val height: String,
    @SerializedName("thumbnail") val thumbnail: String)

/*
Data example:

"cr_url":{
    "format":"pdf",
    "filename":"1596223483_cr_28374.pdf",
    "width":300,
    "id":"ef18ecbadce9bc834faf887cb06a2160",
    "height":300,
    "thumbnail":false
},
 */

