package a26052.pdmnewsapp.domain.usecase

import a26052.pdmnewsapp.domain.model.Article
import a26052.pdmnewsapp.domain.repository.ArticlesRepository

class GetArticlesUseCase(private val repository: ArticlesRepository) {
    suspend operator fun invoke(): List<Article> {
        return repository.getArticles()
    }
}
