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
import models.Category


class CategoryAdapter(private val dataset: List<Category>, val itemClickListener: OnItemClickListener) :
    RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {
    class ViewHolder(val root: View) : RecyclerView.ViewHolder(root) {
        fun bind(item: Category, clickListener: OnItemClickListener) {
            val txtTitle = root.findViewById<TextView>(R.id.category_title)
            val txtDesc = root.findViewById<TextView>(R.id.category_description)
            val Image = root.findViewById<ImageView>(R.id.category_image)

            txtTitle.text = item.title
            txtDesc.text = item.description

            Glide.with(root).load(item.image).centerCrop().into(Image)

            root.setOnClickListener {
            clickListener.onItemClicked(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val rootView = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)
        return ViewHolder(rootView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int){
        holder.bind(dataset[position], itemClickListener)
    }


    override fun getItemCount(): Int = dataset.size
}

interface OnItemClickListener{
    fun onItemClicked(category: Category)
}