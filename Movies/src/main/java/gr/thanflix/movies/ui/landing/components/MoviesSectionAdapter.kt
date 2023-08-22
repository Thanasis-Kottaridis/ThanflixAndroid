package gr.thanflix.movies.ui.landing.components

import android.hardware.TriggerEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import gr.thanflix.domain.models.base.SectionModel
import gr.thanflix.domain.models.show.Show
import gr.thanflix.movies.databinding.MovieItemBinding
import gr.thanflix.movies.databinding.MoviesSectionItemsBinding

class MoviesSectionAdapter(
    private var moviesSections: List<SectionModel<Int, Show>>,
    var onItemClick: ((Int) -> Unit)? = null,
): RecyclerView.Adapter<MoviesSectionAdapter.DataViewHolder>() {
    inner class DataViewHolder(
        private var binding: MoviesSectionItemsBinding
    ): RecyclerView.ViewHolder(binding.root) {

        fun bind(result: SectionModel<Int, Show>) {
            binding.sectionTitle.text = itemView.context.getString(result.model)
            val childMembersAdapter = MoviesItemAdapter(result.items, onItemClick)
            binding.movieItemsRecyclerView.layoutManager = LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL,false)
            binding.movieItemsRecyclerView.adapter = childMembersAdapter
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val binding = MoviesSectionItemsBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return DataViewHolder(binding)
    }

    override fun getItemCount(): Int = moviesSections.size


    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(moviesSections[position])
    }

    fun addData(list: List<SectionModel<Int, Show>>) {
        moviesSections = list
        notifyDataSetChanged()
    }
}