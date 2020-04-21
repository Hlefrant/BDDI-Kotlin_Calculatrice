package com.example.list

import adapters.CategoryAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import Model.Category

class CategoryList: Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var articleAdapter: ArrayAdapter<String>
    private lateinit var spinner: Spinner
    //val planetes

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.category_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.recyclerView)
        bindRecyclerView()
    }

    private fun bindSpinner() {

    }

    private fun bindRecyclerView() {
        //créer une liste d'articles
        val categories = listOf<Category>(
            Category(title="Politique", description="Description Politique", image="https://live.mrf.io/statics/i/ps/media.marianne.net/sites/default/files/styles/mrn_article_large/public/macron-coronavirus.jpg"),
            Category(title="Economie", description="Description Economique", image="https://lvdneng.rosselcdn.net/sites/default/files/dpistyles_v2/ena_16_9_extra_big/2017/05/17/node_164344/28267072/public/2017/05/17/B9712052067Z.1_20170517222040_000%2BG0Q9368GP.2-0.jpg?itok=_aC8L5Zf1495052644"),
            Category(title="Education", description="Description Education", image="https://www.lopinion.fr/sites/nb.com/files/styles/w_838/public/styles/paysage/public/images/2018/05/jean-michel-blanquer.jpg?itok=Bq6RK5rB"),
            Category(title="Pandémie", description="Description Pandémie", image="https://www.numerama.com/content/uploads/2020/03/capture-1024x577.jpg"),
            Category(title="Sciences", description="Description Sciences", image="https://i.f1g.fr/media/eidos/805x453_crop/2018/06/21/XVM56dafe64-6d78-11e8-9dc3-e33fd227be33.jpg"),
            Category(title="Ecologie", description="Description Ecologie", image="https://cdn.futura-sciences.com/buildsv6/images/wide1920/f/b/f/fbf1ffdbee_50145424_ecologie-science.jpg")
        )
        //créer une instance de l'adapteur
        val adapterRecycler = CategoryAdapter(categories)
        //définir l'orientation des élements (vertical)
        recyclerView.layoutManager =
            LinearLayoutManager(context)
        //associer l'adapter à la recyclerview
        recyclerView.adapter = adapterRecycler
    }
}