package repositories

import models.Article
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import services.ArticleService

class ArticleRepository {
    private val service: ArticleService
    init {
        val retrofit = Retrofit.Builder().apply {
            baseUrl("https://newsapi.org")
        }.addConverterFactory(GsonConverterFactory.create())
            .build()
        service = retrofit.create(ArticleService::class.java)
    }
    fun list(): List<Article> {

        val response = service.list().execute()
        return response.body()?.articles ?: emptyList()
    }
}