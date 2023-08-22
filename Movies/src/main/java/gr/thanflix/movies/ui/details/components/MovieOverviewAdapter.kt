package gr.thanflix.movies.ui.details.components

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import gr.thanflix.domain.models.show.Overview
import gr.thanflix.movies.databinding.OverviewItemBinding

class MovieOverviewAdapter(
    private var overviewList: List<Overview>
): RecyclerView.Adapter<MovieOverviewAdapter.DataViewHolder>() {
    inner class DataViewHolder(
        private var binding: OverviewItemBinding
    ): RecyclerView.ViewHolder(binding.root) {

        fun bind(overview: Overview) {
            binding.overviewKeyTextView.text = overview.key
            binding.overviewValueTextView.text = overview.value ?: "NO_VALUE"
            binding.divider.visibility = if (adapterPosition < itemCount - 1) View.VISIBLE else View.GONE
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val binding = OverviewItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return DataViewHolder(binding)
    }

    override fun getItemCount(): Int = overviewList.size


    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(overviewList[position])
    }

    fun addData(list: List<Overview>) {
        overviewList = list
        notifyDataSetChanged()
    }
}