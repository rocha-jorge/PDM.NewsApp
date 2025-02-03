package a26052.pdmnewsapp.data.repository

import a26052.pdmnewsapp.data.local.ArticleDao
import a26052.pdmnewsapp.data.mappers.toDomain
import a26052.pdmnewsapp.data.mappers.toEntity
import a26052.pdmnewsapp.data.remote.ArticlesAPI
import a26052.pdmnewsapp.domain.model.Article
import a26052.pdmnewsapp.domain.repository.ArticlesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ArticleRepositoryImpl @Inject constructor(
    private val api: ArticlesAPI,
    private val articleDao: ArticleDao
) : ArticlesRepository {

    override fun getSavedArticlesFlow(): Flow<List<Article>> {
        return articleDao.getSavedArticles().map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override suspend fun saveArticle(article: Article) {
        articleDao.insertArticle(article.toEntity())
    }

    override suspend fun deleteArticle(article: Article) {
        articleDao.deleteArticle(article.toEntity())
    }

    override suspend fun getArticles(): List<Article> {
        val response = api.fetchArticles()
        return response.results?.map { it.toDomain() } ?: emptyList()
    }
}
