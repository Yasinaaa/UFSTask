package ru.android.ufstask.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/*
 * Created by yasina on 2020-01-16
*/
data class ErrorResponse(
    @SerializedName("status")
    @Expose
    val status: String?,
    @SerializedName("code")
    @Expose
    val code: String?,
    @SerializedName("message")
    @Expose
    val message: String?
)