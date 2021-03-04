package com.ylt.toursdeliberations.model

import com.google.gson.annotations.SerializedName

class Record(@SerializedName("datasetid") val datasetid: String,
             @SerializedName("recordid") val recordid: String,
             @SerializedName("fields") val deliberation: Deliberation,
             @SerializedName("record_timestamp") val record_timestamp: String){}