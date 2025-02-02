package a26052.pdmnewsapp.data.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ArticleDto(
    @SerialName("title") val title: String,
    @SerialName("description") val description: String?,
    @SerialName("urlToImage") val imageUrl: String?,
    @SerialName("url") val url: String
)
