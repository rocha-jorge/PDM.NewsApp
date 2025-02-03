package a26052.pdmnewsapp.data.mappers

import a26052.pdmnewsapp.data.local.ArticleEntity
import a26052.pdmnewsapp.data.remote.ArticleDto
import a26052.pdmnewsapp.domain.model.Article

fun ArticleDto.toDomain(): Article {
    return Article(
        article_id= this.article_id ?: "",
        title = this.title ?: "No Title",
        description = this.description ?: "No Description",
        image_url = this.image_url,
        link = this.link ?: "#"
    )
}

fun Article.toEntity(): ArticleEntity {
    return ArticleEntity(
        article_id = this.article_id.ifEmpty { link }, // ✅ Use URL as fallback if ID is empty
        title = this.title,
        description = this.description ?: "No description available",
        image_url = this.image_url,
        link = this.link
    )
}


fun ArticleEntity.toDomain(): Article {
    return Article(
        article_id = this.article_id,
        title = this.title,
        description = this.description,
        image_url = this.image_url,
        link = this.link
    )
}
