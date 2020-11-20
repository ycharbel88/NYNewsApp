package com.chy.nynewsapp.presentation.articles

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.chy.nynewsapp.R
import com.chy.nynewsapp.databinding.HolderArticleBinding
import com.chy.nynewsapp.domain.model.Article
import kotlinx.android.synthetic.main.activity_detail_article.*
import kotlinx.android.synthetic.main.holder_article.view.*
import kotlin.properties.Delegates

class ArticlesAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var mListener: Listener? = null

    fun setListener(listener: Listener) {
        mListener = listener
    }

    var mArticleList: List<Article> by Delegates.observable(emptyList()) { _, _, _ ->
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val holderArticleBinding = DataBindingUtil.inflate<ViewDataBinding>(
            LayoutInflater.from(parent.context), R.layout.holder_article, parent, false
        )
        return ArticleViewHolder(holderArticleBinding)
    }

    override fun getItemCount(): Int {
        return if (mArticleList.isNullOrEmpty()) 0 else mArticleList.size
    }

    private fun getItem(position: Int): Article {
        return mArticleList[position]
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ArticleViewHolder).onBind(getItem(position))
    }

    inner class ArticleViewHolder(private val viewDataBinding: ViewDataBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root) {

        fun onBind(article: Article) {
            (viewDataBinding as HolderArticleBinding).article = article
            itemView.globalLayout.setOnClickListener {
                mListener?.onArticleClicked(article)
            }

            Glide.with(itemView.imageView)
                .load(article.media[0].mediaMetadata[0].url).circleCrop()
                .into(itemView.imageView)
        }
    }

    interface Listener {
        fun onArticleClicked(article: Article)
    }

    companion object {
        private val TAG = ArticlesAdapter::class.java.simpleName
    }
}