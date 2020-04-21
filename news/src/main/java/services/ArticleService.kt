package services

import androidx.core.app.NotificationCompat.getCategory
import models.ArticleResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


interface ArticleService {
    @GET("/v2/top-headlines?country=fr&apiKey=a640f1b9a0b545258494deff3893da16")
     fun list(): Call<ArticleResult>
}
