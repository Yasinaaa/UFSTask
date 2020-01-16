package ru.android.ufstask.models

import android.os.Parcelable
import androidx.room.*
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import io.reactivex.annotations.NonNull
import kotlinx.android.parcel.Parcelize
import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/*
 * Created by yasina on 2020-01-15
*/
@Entity(tableName = "article_table")
data class Article(

    @ColumnInfo(name = "author")
    val author: String?,

    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "title")
    val title: String = "",

    @ColumnInfo(name = "description")
    val description: String?,

    @ColumnInfo(name = "url")
    val url: String? = null,

    @ColumnInfo(name = "urlToImage")
    val urlToImage: String? = null,

    @ColumnInfo(name = "publishedAt")
    val publishedAt: Date? = null
)

@Parcelize
data class ArticleItem(
    val author: String?,
    val title: String?,
    val description: String?,
    val url: String?,
    val urlToImage: String?,
    val publishedAt: String?
): Parcelable

data class ArticleRes(
    @SerializedName("author")
    @Expose
    val author: String?,
    @SerializedName("title")
    @Expose
    val title: String?,
    @SerializedName("description")
    @Expose
    val description: String?,
    @SerializedName("url")
    @Expose
    val url: String?,
    @SerializedName("urlToImage")
    @Expose
    val urlToImage: String?,
    @SerializedName("publishedAt")
    @Expose
    val publishedAt: String?,
    @SerializedName("content")
    @Expose
    val content: String?
)

fun ArticleRes.transformToArticle() =
    Article(
        author = this.author,
        title = this.title!!,
        description = this.description,
        url = this.url,
        urlToImage = this.urlToImage,
        publishedAt = this.publishedAt.convertToDate()
    )

fun MutableList<ArticleRes>.transformToArticleList() : MutableList<Article>{
    var list : MutableList<Article> = mutableListOf()
    this.forEach {
        list.add(it.transformToArticle())
    }
    return list
}

fun Article.transformToArticle() =
    ArticleItem(
        author = this.author,
        title = this.title,
        description = this.description,
        url = this.url,
        urlToImage = this.urlToImage,
        publishedAt = this.publishedAt!!.toParsedString()
    )

fun Date?.toParsedString() : String {
    return SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(this!!)
}

fun Date?.toDatabaseString() : String {
    return standardDateFormat.format(this!!)
}

fun String?.convertToDate(): Date? {
    return if (this != null) {
        try {
            return standardDateFormat.parse(this)
        } catch (e: ParseException) {
            e.printStackTrace()
        } catch (e: ArrayIndexOutOfBoundsException){
            e.printStackTrace()
        }
        null
    } else {
        null
    }
}


private var standardDateFormat: DateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")





