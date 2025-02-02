package a26052.pdmnewsapp.domain.model

data class Article(
    val id: Int? = null, // Nullable for unsaved articles
    val title: String,
    val description: String?,
    val imageUrl: String?,
    val url: String
)
