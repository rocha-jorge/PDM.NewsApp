package a26052.pdmnewsapp.data

import a26052.pdmnewsapp.data.local.ArticleEntity
import a26052.pdmnewsapp.data.remote.ArticleDto

// ✅ Convert API response (ArticleDto) to a database entity (ArticleEntity)
fun ArticleDto.toEntity(): ArticleEntity {
    return ArticleEntity(
        title = this.title ?: "No Title",
        description = this.description ?: "No Description",
        imageUrl = this.imageUrl,
        url = this.url ?: "#"  // ✅ Ensure it's not null
    )
}
