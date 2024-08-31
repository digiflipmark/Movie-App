package com.finder.movieapp.core_feature.data.remote.dto

import com.google.gson.annotations.SerializedName

data class ErrorResponse(

	@field:SerializedName("status_message")
	val statusMessage: String,

	@field:SerializedName("status_code")
	val statusCode: Int,

	@field:SerializedName("success")
	val success: Boolean
)
