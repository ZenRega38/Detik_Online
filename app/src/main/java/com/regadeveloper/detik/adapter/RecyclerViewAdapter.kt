package com.regadeveloper.detik.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import coil.size.Scale
import com.regadeveloper.detik.DetailActivity
import com.regadeveloper.detik.databinding.CdvNewsHeadlineBinding
import com.regadeveloper.detik.model.ArticlesItem

class RecyclerViewAdapter : RecyclerView.Adapter<CdvNewsHeadLine>() {
    private val listNews: ArrayList<ArticlesItem> = arrayListOf<ArticlesItem>()

    fun addData(item: List<ArticlesItem>){
        listNews.clear()
        listNews.addAll(item)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CdvNewsHeadLine {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = CdvNewsHeadlineBinding.inflate(layoutInflater, parent, false)
        return CdvNewsHeadLine(binding)
    }

    override fun getItemCount(): Int {
        return listNews.size
    }

    override fun onBindViewHolder(holder: CdvNewsHeadLine, position: Int) {
        val data = listNews[position]
        holder.bind(data)
        holder.itemView.setOnClickListener {
            val intent = Intent(it.context, DetailActivity::class.java)
            intent.putExtra(DetailActivity.date, data.publishedAt)
            intent.putExtra(DetailActivity.content, data.content)
            intent.putExtra(DetailActivity.image, data.urlToImage)
            it.context.startActivity(intent)
        }

    }
}

class CdvNewsHeadLine(private val binding: CdvNewsHeadlineBinding) : RecyclerView.ViewHolder(binding.root){

    fun bind(item: ArticlesItem){
        with(binding){
            txtTitle.text = item.title
            txtSubtitle.text = item.description
            imageView.load(item.urlToImage){
                scale(Scale.FILL)
            }
        }
    }

}