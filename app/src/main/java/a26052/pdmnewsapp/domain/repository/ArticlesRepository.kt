package a26052.pdmnewsapp.domain.repository

import a26052.pdmnewsapp.domain.model.Article
import kotlinx.coroutines.flow.Flow

interface ArticlesRepository {
    suspend fun getArticles(): List<Article> // Fetch articles from API
    suspend fun saveArticle(article: Article) // ✅ Add this
    suspend fun deleteArticle(article: Article) // ✅ Add this
    fun getSavedArticlesFlow(): Flow<List<Article>> // ✅ Add this
}
