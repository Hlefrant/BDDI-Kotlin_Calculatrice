package adaptaters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.news.R
import models.Article


class ArticleAdapter(private val dataset: List<Article>) :
    RecyclerView.Adapter<ArticleAdapter.ViewHolder>() {
    class ViewHolder(val root: View) : RecyclerView.ViewHolder(root) {
        fun bind(item: Article) {
            val txtTitle = root.findViewById<TextView>(R.id.article_title)
            val txtDesc = root.findViewById<TextView>(R.id.article_description)
            val Image = root.findViewById<ImageView>(R.id.article_image)

            txtTitle.text = item.name
            txtDesc.text = item.description

            Glide.with(root).load(item.urlToImage).centerCrop().into(Image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val rootView = LayoutInflater.from(parent.context)
            .inflate(R.layout.article, parent, false)
        return ViewHolder(rootView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int){
        holder.bind(dataset[position])
    }


    override fun getItemCount(): Int = dataset.size
}