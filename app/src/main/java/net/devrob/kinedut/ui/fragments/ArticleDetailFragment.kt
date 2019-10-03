package net.devrob.kinedut.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_article_detail.*
import net.devrob.kinedut.R
import net.devrob.kinedut.models.Article
import net.devrob.kinedut.viewModels.ArticleViewModel
import net.devrob.kinedut.viewModels.BaseViewModel

class ArticleDetailFragment : BaseFragment() {
    private lateinit var articleViewModel: ArticleViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_article_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        articleViewModel = baseViewModel as ArticleViewModel
        if (arguments?.containsKey("article")!!) {
            articleViewModel.updateIfNecesaryArticle(arguments?.getSerializable("article") as Article)
        }

        articleViewModel.infoArticle.observe(this, Observer { article ->
            Picasso
                .with(mainActivity.applicationContext)
                .load(article.picture)
                .fit()
                .centerCrop()
                .into(ivBanner)
            tvTitle.text = article.title
            html_text.setHtml(article.body!!)

            ivShare.setOnClickListener {
                val sharingIntent = Intent(Intent.ACTION_SEND)
                sharingIntent.type = "text/plain"
                sharingIntent.putExtra(Intent.EXTRA_TEXT, article.link)
                startActivity(
                    Intent.createChooser(
                        sharingIntent,
                        "Select Share app"
                    )
                )
            }

        })
    }

    override fun upcastBaseViewModel(): BaseViewModel? {
        return return ViewModelProviders.of(this).get(ArticleViewModel::class.java)
    }
}