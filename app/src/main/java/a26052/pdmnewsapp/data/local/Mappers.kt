package a26052.pdmnewsapp.data.mappers

import a26052.pdmnewsapp.data.local.ArticleEntity
import a26052.pdmnewsapp.data.remote.ArticleDto
import a26052.pdmnewsapp.domain.model.Article

fun ArticleDto.toDomain(): Article {
    return Article(
        id = this.id ?: "",
        title = this.title ?: "No Title",
        description = this.description ?: "No Description",
        imageUrl = this.imageUrl,
        url = this.url ?: "#"
    )
}

fun Article.toEntity(): ArticleEntity {
    return ArticleEntity(
        id = this.id ?: "",
        title = this.title,
        description = this.description ?: "No description",
        imageUrl = this.imageUrl ?: "",
        url = this.url ?: "#"
    )
}


fun ArticleEntity.toDomain(): Article {
    return Article(
        id = this.id,
        title = this.title,
        description = this.description,
        imageUrl = this.imageUrl,
        url = this.url
    )
}
