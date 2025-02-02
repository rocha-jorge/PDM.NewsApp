package a26052.pdmnewsapp.data.remote

import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import a26052.pdmnewsapp.domain.model.Article

@Serializable
data class NewsResponse(
    @SerialName("status") val status: String,
    @Contextual @SerialName("results") val articles: List<Article>? // ✅ Fix serialization issue
)
