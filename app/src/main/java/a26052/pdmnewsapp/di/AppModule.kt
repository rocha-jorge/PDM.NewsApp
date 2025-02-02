package a26052.pdmnewsapp.di

import android.app.Application
import androidx.room.Room
import a26052.pdmnewsapp.data.local.AppDatabase
import a26052.pdmnewsapp.data.local.ArticleDao
import a26052.pdmnewsapp.data.remote.ArticlesAPI
import a26052.pdmnewsapp.data.repository.ArticlesRepositoryImpl
import a26052.pdmnewsapp.domain.repository.ArticlesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(app: Application): AppDatabase {
        return Room.databaseBuilder(
            app,
            AppDatabase::class.java,
            "news_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideArticleDao(database: AppDatabase): ArticleDao {
        return database.articleDao()
    }

    @Provides
    @Singleton
    fun provideArticlesAPI(): ArticlesAPI {
        return ArticlesAPI.create() // ✅ Provide Retrofit API
    }

    @Provides
    @Singleton
    fun provideArticlesRepository(api: ArticlesAPI, dao: ArticleDao): ArticlesRepository {
        return ArticlesRepositoryImpl(api, dao) // ✅ Provide Repository with DAO
    }
}
