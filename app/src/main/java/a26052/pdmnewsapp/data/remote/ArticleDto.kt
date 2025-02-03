package a26052.pdmnewsapp.data.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ArticleDto(
    @SerialName("article_id") val article_id: String?,
    @SerialName("title") val title: String?,
    @SerialName("description") val description: String?,
    @SerialName("image_url") val image_url: String?,
    @SerialName("link") val link: String?
)
