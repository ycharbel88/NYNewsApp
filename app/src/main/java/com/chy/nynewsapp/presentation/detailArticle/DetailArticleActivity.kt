package com.chy.nynewsapp.presentation.detailArticle

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.chy.nynewsapp.R
import com.chy.nynewsapp.databinding.ActivityDetailArticleBinding
import com.chy.nynewsapp.domain.model.Article
import kotlinx.android.synthetic.main.activity_detail_article.*

class DetailArticleActivity : AppCompatActivity() {
    lateinit var toolbar: Toolbar
    private lateinit var article: Article

    companion object {
        private const val ARG_Article = "arg_article"
        fun createInstance(context: Context, article: Article): Intent {
            return Intent(context, DetailArticleActivity::class.java).putExtras(
                Bundle().apply {
                    putSerializable(ARG_Article, article)
                }
            )
        }
    }

    private lateinit var activityDetailArticleBinding: ActivityDetailArticleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityDetailArticleBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_detail_article)

        intent.extras.let {
            article = it?.getSerializable(ARG_Article) as Article
            activityDetailArticleBinding.article = article
            drawLayout()
        }

    }

    private fun drawLayout() {
        toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        if (article.media.isNullOrEmpty())
            return

        var mediaCount = article.media[0].mediaMetadata.size
        Glide.with(this)
            .load(article.media[0].mediaMetadata[mediaCount - 1].url)
            .into(articleImageView)

        shareImageButton.setOnClickListener { shareContent(article) }
    }

    protected fun shareContent(content: Article) {
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TITLE, content.title)
            putExtra(Intent.EXTRA_TEXT, content.url)
            type = "text/plain"
        }

        val shareIntent = Intent.createChooser(sendIntent, null)
        startActivity(shareIntent)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }

            else -> return super.onOptionsItemSelected(item)
        }
    }
}
