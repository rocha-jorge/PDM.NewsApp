package a26052.pdmnewsapp.data.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NewsResponse(
    @SerialName("results") val results: List<ArticleDto>? // ✅ Correct key name from API response
)

@Serializable
data class ArticleDto(
    @SerialName("article_id") val id: String?,
    @SerialName("title") val title: String?,
    @SerialName("description") val description: String?,
    @SerialName("image_url") val imageUrl: String?,
    @SerialName("link") val url: String?
)
