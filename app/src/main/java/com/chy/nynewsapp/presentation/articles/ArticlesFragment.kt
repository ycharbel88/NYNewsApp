package com.chy.nynewsapp.presentation.articles

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.chy.nynewsapp.R
import com.chy.nynewsapp.databinding.FragmentArticlesBinding
import com.chy.nynewsapp.domain.model.Article
import com.chy.nynewsapp.presentation.detailArticle.DetailArticleActivity
import com.chy.nynewsapp.utils.Constants
import com.chy.nynewsapp.utils.isNetworkAvailable
import kotlinx.android.synthetic.main.fragment_articles.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.android.viewmodel.ext.android.viewModel

class ArticlesFragment : Fragment(), ArticlesAdapter.Listener {
    private lateinit var articlesDataBinding: FragmentArticlesBinding
    private var mAdapter: ArticlesAdapter? = null
    private val articleViewModel: ArticleViewModel by viewModel()

    @ExperimentalCoroutinesApi
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        articlesDataBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_articles, container, false)
        mAdapter = ArticlesAdapter()
        mAdapter!!.setListener(this)
        articlesDataBinding.recyclerView.adapter = mAdapter
        return articlesDataBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if (isNetworkAvailable(requireContext())) {
            articleViewModel.getMostViewedArticle(Constants.ApiKey)
        } else {
            Toast.makeText(
                context,
                getString(R.string.no_internet_connection),
                Toast.LENGTH_SHORT
            ).show()
        }

        with(articleViewModel) {
            mostViewedArticle.observe(viewLifecycleOwner, Observer {
                articlesDataBinding.progressBar.visibility = View.GONE
                mAdapter?.mArticleList = it
            })

            messageData.observe(viewLifecycleOwner, Observer {
                Toast.makeText(context, it, Toast.LENGTH_LONG).show()
            })

            showProgressbar.observe(viewLifecycleOwner, Observer { isVisible ->
                progressBar.visibility = if (isVisible) View.VISIBLE else View.GONE
            })
        }
    }


    companion object {
        private val TAG = DetailArticleActivity::class.java.name
    }

    override fun onArticleClicked(article: Article) {

        startActivity(context?.let { it1 -> DetailArticleActivity.createInstance(context = it1, article = article) })

    }
}