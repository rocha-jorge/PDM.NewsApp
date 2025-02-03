package a26052.pdmnewsapp.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Article(
    val article_id: String,
    val title: String,
    val description: String?,
    val image_url: String?,
    val link: String
)
