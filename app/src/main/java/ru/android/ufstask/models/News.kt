package ru.android.ufstask.models

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/*
 * Created by yasina on 2020-01-14
*/
@Parcelize
data class NewsItem(
    val id: Long?,
    @SerializedName("status")
    @Expose
    val status: String?,
    @SerializedName("totalResult")
    @Expose
    val totalResult: Long?,
    @SerializedName("articles")
    @Expose
    val articles: List<ArticleItem>?
) : Parcelable

data class NewsRes(
    @SerializedName("status")
    @Expose
    val status: String?,
    @SerializedName("totalResult")
    @Expose
    val totalResult: Long?,
    @SerializedName("articles")
    @Expose
    val articles: MutableList<ArticleRes>?
)



