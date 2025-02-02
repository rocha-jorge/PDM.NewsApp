package a26052.pdmnewsapp.domain.usecase

import a26052.pdmnewsapp.domain.model.Article
import a26052.pdmnewsapp.domain.repository.ArticlesRepository

class GetSavedArticlesUseCase(private val repository: ArticlesRepository) {
    operator fun invoke(callback: (List<Article>) -> Unit) {
        repository.getSavedArticles(callback)
    }
}
