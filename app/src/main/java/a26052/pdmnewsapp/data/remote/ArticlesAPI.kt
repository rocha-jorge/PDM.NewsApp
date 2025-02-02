package a26052.pdmnewsapp.data.remote


import a26052.pdmnewsapp.data.remote.NewsResponse
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Url
import android.util.Log

interface ArticlesAPI {

    companion object {
        private const val FULL_URL = "https://newsdata.io/api/1/latest?apikey=pub_67564d3016ba0b01637fb7ea501c9bd4ada1a&language=en&size=10"

        fun create(): ArticlesAPI {
            val loggingInterceptor = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
            val client = OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .addInterceptor { chain ->
                    val request: Request = chain.request().newBuilder()
                        .url(FULL_URL) // ✅ Manually set the full URL
                        .build()

                    Log.d("API_REQUEST", "🔗 Requesting data from: ${request.url}") // ✅ Log actual URL
                    chain.proceed(request)
                }
                .build()

            return Retrofit.Builder()
                .baseUrl("https://newsdata.io/api/") // ✅ Needed, but ignored
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
                .create(ArticlesAPI::class.java)
        }
    }

    @GET
    suspend fun fetchArticles(@Url url: String = FULL_URL): NewsResponse // ✅ Uses the FULL_URL
}
