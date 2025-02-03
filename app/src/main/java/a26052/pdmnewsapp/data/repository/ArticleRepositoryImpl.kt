package a26052.pdmnewsapp.data.repository

import a26052.pdmnewsapp.data.local.ArticleDao
import a26052.pdmnewsapp.data.mappers.toDomain
import a26052.pdmnewsapp.data.mappers.toEntity
import a26052.pdmnewsapp.data.remote.ArticlesAPI
import a26052.pdmnewsapp.domain.model.Article
import a26052.pdmnewsapp.domain.repository.ArticlesRepository
import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ArticleRepositoryImpl @Inject constructor(
    private val api: ArticlesAPI,
    private val articleDao: ArticleDao
) : ArticlesRepository {


    override suspend fun saveArticle(article: Article) {
        Log.d("BOOKMARK_DEBUG", "Saving article: ${article.title}, ID: ${article.id}")
        articleDao.insertArticle(article.toEntity())
    }


    override suspend fun deleteArticle(article: Article) {
        articleDao.deleteArticle(article.toEntity())
    }

    override suspend fun getArticles(): List<Article> {
        val response = api.fetchArticles()
        return response.results?.map { it.toDomain() } ?: emptyList()
    }

    override fun getBookmarkedArticles(): Flow<List<Article>> {
        return articleDao.getBookmarkedArticles().map { entities ->
            val articles = entities.map { it.toDomain() }
            Log.d("BOOKMARK_DEBUG", "Retrieved ${articles.size} bookmarked articles")
            articles
        }
    }

}
