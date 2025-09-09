package com.nandomiranda.themovies.view.adapter

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.nandomiranda.themovies.R
import com.nandomiranda.themovies.databinding.MovieListItemBinding
import com.nandomiranda.themovies.model.movie.Movie

private const val IMAGE_COMPLEMENT = "https://image.tmdb.org/t/p/w500"
class MovieAdapter : ListAdapter<Movie , MovieAdapter.ViewHolder>(DiffCallBack) {

    //metodos para saber que item se quito y cual se agrego al recyclerView
    companion object DiffCallBack: DiffUtil.ItemCallback<Movie>(){
        override fun areItemsTheSame(oldItem: Movie , newItem: Movie): Boolean {
            return oldItem.id==newItem.id
        }

        override fun areContentsTheSame(oldItem: Movie , newItem: Movie): Boolean {
            return oldItem == newItem
        }
    }

    //lambda que regresa un superhero al darle click a un item, se inicializara desde HeroListFragment
    lateinit var onItemClickListener: (Movie) -> Unit

    //los metodos onCreateViewHolder y onBindViewHolder se manda llamar para cada elemento de la lista
    //crea un viewHolder para cada elemento de la lista
    override fun onCreateViewHolder(
        parent: ViewGroup ,
        viewType: Int
    ): MovieAdapter.ViewHolder {
        val binding = MovieListItemBinding.inflate(LayoutInflater.from(parent.context))
        return ViewHolder(binding)
    }

    //se obtiene la posicion del elemento para pintarlo en pantalla
    override fun onBindViewHolder(holder: MovieAdapter.ViewHolder , position: Int) {
        val superhero = getItem(position)
        holder.bind(superhero)
    }

    //clase que trabaja los view del superhero_list_item
    inner class ViewHolder (private val binding: MovieListItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun  bind(movie: Movie){
            binding.movie = movie
            Glide.with(binding.movieImage.context)
                .load(IMAGE_COMPLEMENT+movie.imageUrl).listener(object :
                    RequestListener<Drawable> {
                    override fun onLoadFailed(
                        e: GlideException? ,
                        model: Any? ,
                        target: Target<Drawable>? ,
                        isFirstResource: Boolean
                    ): Boolean {
                        binding.movieImage.setImageResource(R.drawable.ic_baseline_image_not_supported) ///este solo es por si la app va a tronar
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable? ,
                        model: Any? ,
                        target: Target<Drawable>? ,
                        dataSource: DataSource? ,
                        isFirstResource: Boolean
                    ): Boolean {
                        return false
                    }

                }).error(R.drawable.ic_baseline_image_not_supported).into(binding.movieImage)

            binding.root.setOnClickListener {
                if(::onItemClickListener.isInitialized){
                    onItemClickListener(movie)
                }
            }
            binding.executePendingBindings()
        }
    }
}
