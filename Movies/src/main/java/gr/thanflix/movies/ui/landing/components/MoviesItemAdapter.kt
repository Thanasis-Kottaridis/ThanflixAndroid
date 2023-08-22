package gr.thanflix.movies.ui.landing.components

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import gr.thanflix.domain.models.show.Show
import gr.thanflix.movies.R
import gr.thanflix.movies.databinding.MovieItemBinding

open class MoviesItemAdapter(
    var movies: List<Show>,
    var onItemClick: ((Int) -> Unit)? = null,
): RecyclerView.Adapter<MoviesItemAdapter.DataViewHolder>() {

    inner class DataViewHolder(private var binding: MovieItemBinding): RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root
            itemView.setOnClickListener {
                onItemClick?.invoke(movies[adapterPosition].id ?: -1)
            }
        }

        fun bind(result: Show) {
            binding.movieName.text = result.title

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val binding = MovieItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return DataViewHolder(binding)
    }

    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(movies[position])
    }
}