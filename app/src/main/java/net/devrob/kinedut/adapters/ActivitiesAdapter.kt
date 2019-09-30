package net.devrob.kinedut.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.custom_activities_item.view.*
import net.devrob.kinedut.R

class ActivitiesAdapter(
    val adapterContext: Context,
    private val items: ArrayList<net.devrob.kinedut.models.Activity>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.custom_activities_item, parent, false)
        return ViewHolderActivity(v)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val activity = items[position]
        val viewHolder = holder as ViewHolderActivity

        Picasso
            .with(adapterContext)
            .load(activity.thumbnail)
            .fit()
            .centerCrop()
            .into(holder.ivImage)
        viewHolder.tvTitle?.text = activity.name
        viewHolder.tvDescription?.text = activity.purpose
        viewHolder.llAvances?.removeAllViews()
    }

    override fun getItemCount() = items.size

    class ViewHolderActivity(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivImage: AppCompatImageView? = itemView.ivImage
        val tvTitle: AppCompatTextView? = itemView.tvTitle
        val tvDescription: AppCompatTextView? = itemView.tvDescription
        val llAvances: LinearLayoutCompat? = itemView.llAvances
    }
}