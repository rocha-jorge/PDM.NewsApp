package a26052.pdmnewsapp.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Article(
    val id: String,
    val title: String,
    val description: String?,
    val imageUrl: String?,
    val url: String
)
