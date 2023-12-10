package com.example.punetest.adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater

import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.punetest.R
import com.example.punetest.model.ClosedIssue
import com.squareup.picasso.Picasso

class IssueAdapter(private var issues: List<ClosedIssue>) :
    RecyclerView.Adapter<IssueAdapter.IssueViewHolder>(), Filterable {

    private var filteredList: List<ClosedIssue> = issues

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IssueViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_layout, parent, false)
        return IssueViewHolder(itemView)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: IssueViewHolder, position: Int) {
        val issue = issues[position]
        holder.txtTitle.text = issue.title
        holder.txtCreatedAt.text = "Created: ${issue.created_at}"
        holder.txtClosedAt.text = "Closed: ${issue.closed_at}"
        holder.txtUser.text = "By : ${issue.user.login}"

        Picasso.get().load(issue.user.avatar_url).into(holder.userImage)
    }

    override fun getItemCount(): Int {
        return issues.size
    }

    class IssueViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtTitle: TextView = itemView.findViewById(R.id.txtTitle)
        val txtCreatedAt: TextView = itemView.findViewById(R.id.txtCreatedAt)
        val txtClosedAt: TextView = itemView.findViewById(R.id.txtClosedAt)
        val txtUser: TextView = itemView.findViewById(R.id.txtUser)
        val userImage: ImageView = itemView.findViewById(R.id.imageView)
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val filterResults = FilterResults()

                if (constraint == null || constraint.isEmpty()) {
                    filterResults.values = issues
                } else {
                    val filtered = issues.filter { issue ->
                        issue.title.contains(constraint, ignoreCase = true)
                    }

                    // If the filtered list is empty, reset to the original list
                    if (filtered.isEmpty()) {
                        filterResults.values = issues
                    } else {
                        filterResults.values = filtered
                    }
                }

                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                filteredList = results?.values as? List<ClosedIssue> ?: emptyList()
                issues = filteredList
                notifyDataSetChanged()
            }
        }
    }
}
