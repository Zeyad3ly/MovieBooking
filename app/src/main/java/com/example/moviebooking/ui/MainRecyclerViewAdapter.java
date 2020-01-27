package com.example.moviebooking.ui;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.moviebooking.R;
import com.example.moviebooking.data.MovieModel;
import com.squareup.picasso.Picasso;
import java.util.List;

public class MainRecyclerViewAdapter extends RecyclerView.Adapter<MainRecyclerViewAdapter.viewHolder> {
    List<MovieModel> movieModelList;
    Context context;

    public MainRecyclerViewAdapter(List<MovieModel> movieModelList, Context context) {
        this.movieModelList = movieModelList;
        this.context = context;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_recycler_item, parent, false);
        return new MainRecyclerViewAdapter.viewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        final MovieModel movieModel = movieModelList.get(position);
        if (movieModel.getImage() != null)
            Picasso.get().load(movieModel.getImage().toString()).into(holder.moviePoster);
        holder.movieName.setText(movieModel.getName().toString() + "");
        Log.e("MainAdapter",movieModel.getName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(context,SeatsSelectionActivity.class);
                i.putExtra("movieName",movieModel.getName());
                i.putExtra("movieId",movieModel.getId());
                i.putExtra("moviePic",movieModel.getImage());
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);

            }
        });

    }

    @Override
    public int getItemCount() {


        if (movieModelList == null) return 0;
        return movieModelList.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        ImageView moviePoster;
        TextView movieName;

        public viewHolder(@NonNull View itemView) {
            super(itemView);

            movieName = itemView.findViewById(R.id.movieName);
            moviePoster = itemView.findViewById(R.id.imgMovieSample);


        }
    }
}
