package net.devrob.kinedut.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.custom_articles_item.view.*
import net.devrob.kinedut.R
import net.devrob.kinedut.models.Article

class ArticlesAdapter (
    val context: Context,
    private var items: ArrayList<Article>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var selectListener: (Article) -> Unit = {}

    fun setOnSelectedListener(listener: (Article) -> Unit) {
        selectListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.custom_articles_item, parent, false)
        return ViewHolderArticle(v)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val article = items[position]
        val viewHolder = holder as ViewHolderArticle

        Picasso
            .with(context)
            .load(article.picture)
            .fit()
            .centerCrop()
            .into(viewHolder.ivBanner)

        viewHolder.tvTitle?.text = article.name
        viewHolder.tvDescription?.text = article.shortDescription
        viewHolder.cvArticle?.setOnClickListener {
            selectListener(article)
        }
    }

    override fun getItemCount() = items.size

    class ViewHolderArticle(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cvArticle: CardView? = itemView.cvArticle
        val ivBanner: AppCompatImageView? = itemView.ivBanner
        val tvTitle: AppCompatTextView? = itemView.tvTitle
        val tvDescription: AppCompatTextView? = itemView.tvDescription
    }
}