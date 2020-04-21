package fragment

import adaptaters.ArticleAdapter
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.news.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import models.Article
import models.Source
import repositories.ArticleRepository

class ListArticleFragment : Fragment(){

    private val repository = ArticleRepository()
    private lateinit var recyclerView: RecyclerView
    val category: String by lazy {
        arguments?.getString(ARGS_CATEGORY, "Politique") ?: "Politique"
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.article_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.recyclerViewArticle)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        lifecycleScope.launch {
            getData()
        }
    }
    private suspend fun getData() {
        withContext(Dispatchers.IO) {
            val result = repository.list()
            bindData(result)
        }
    }
    //S'execute sur le thread principal
    private suspend fun bindData(result: List<Article>) {
        withContext(Dispatchers.Main) {
            //afficher les données dans le recycler
            Log.d("Articles", result.toString())

            val articles = result
            //créer une instance de l'adapteur
            val adapterRecycler = ArticleAdapter(articles)
            //définir l'orientation des élements (vertical)
            recyclerView.layoutManager =
                LinearLayoutManager(context)
            //associer l'adapter à la recyclerview
            recyclerView.adapter = adapterRecycler
        }
    }

    companion object {
        const val ARGS_CATEGORY = "ARGS_CATEGORY"
        fun newInstance(operation: String):ListArticleFragment {
            return ListArticleFragment().apply {
                //On sauvegarde l’opération dans les arguments,
                //Si le fragment se recrée, la valeur sera restaurée
                arguments = bundleOf(ARGS_CATEGORY to operation)
            }
        }
    }

}