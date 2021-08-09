package com.ylt.toursdeliberations.model

import com.google.gson.annotations.SerializedName

class DeliberationsResponse(
  @SerializedName("nhits") val nhits: Int,
  @SerializedName("records") val records: Array<Record>)