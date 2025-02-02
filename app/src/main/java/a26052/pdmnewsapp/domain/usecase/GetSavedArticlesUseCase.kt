package a26052.pdmnewsapp.domain.usecase

import a26052.pdmnewsapp.domain.model.Article
import a26052.pdmnewsapp.domain.repository.ArticlesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSavedArticlesUseCase @Inject constructor(
    private val repository: ArticlesRepository
) {
    fun execute(): Flow<List<Article>> {
        return repository.getSavedArticlesFlow() // ✅ Ensure this method exists in your repository
    }
}
