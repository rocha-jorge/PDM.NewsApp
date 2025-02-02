package a26052.pdmnewsapp.di

import android.content.Context
import androidx.room.Room
import a26052.pdmnewsapp.data.local.AppDatabase
import a26052.pdmnewsapp.data.local.ArticleDao
import a26052.pdmnewsapp.data.remote.ArticlesAPI
import a26052.pdmnewsapp.data.repository.ArticlesRepositoryImpl
import a26052.pdmnewsapp.domain.repository.ArticlesRepository
import a26052.pdmnewsapp.domain.usecase.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    // Provide Room Database
    @Provides
    @Singleton
    fun provideDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "articles_db"
        ).build()
    }

    // Provide DAO
    @Provides
    fun provideArticleDao(database: AppDatabase): ArticleDao {
        return database.articleDao()
    }

    // Provide API Service
    @Provides
    @Singleton
    fun provideArticlesAPI(): ArticlesAPI {
        return ArticlesAPI()
    }

    // Provide Repository Implementation
    @Provides
    @Singleton
    fun provideArticlesRepository(api: ArticlesAPI, dao: ArticleDao): ArticlesRepository {
        return ArticlesRepositoryImpl(api, dao)
    }

    // Provide Use Cases
    @Provides
    fun provideGetArticlesUseCase(repository: ArticlesRepository): GetArticlesUseCase {
        return GetArticlesUseCase(repository)
    }

    @Provides
    fun provideGetSavedArticlesUseCase(repository: ArticlesRepository): GetSavedArticlesUseCase {
        return GetSavedArticlesUseCase(repository)
    }

    @Provides
    fun provideSaveArticleUseCase(repository: ArticlesRepository): SaveArticleUseCase {
        return SaveArticleUseCase(repository)
    }

    @Provides
    fun provideDeleteArticleUseCase(repository: ArticlesRepository): DeleteArticleUseCase {
        return DeleteArticleUseCase(repository)
    }
}
