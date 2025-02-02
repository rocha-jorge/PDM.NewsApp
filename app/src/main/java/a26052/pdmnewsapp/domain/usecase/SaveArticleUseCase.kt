package a26052.pdmnewsapp.domain.usecase

import a26052.pdmnewsapp.domain.model.Article
import a26052.pdmnewsapp.domain.repository.ArticlesRepository

class SaveArticleUseCase(private val repository: ArticlesRepository) {
    suspend operator fun invoke(article: Article) {
        repository.saveArticle(article)
    }
}
