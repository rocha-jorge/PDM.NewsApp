package a26052.pdmnewsapp.domain.repository

import a26052.pdmnewsapp.domain.model.Article
import kotlinx.coroutines.flow.Flow

interface ArticlesRepository {
    fun getSavedArticlesFlow(): Flow<List<Article>>
    suspend fun saveArticle(article: Article)
    suspend fun deleteArticle(article: Article)
    suspend fun getArticles(): List<Article> // ✅ Ensure this function is here
}
