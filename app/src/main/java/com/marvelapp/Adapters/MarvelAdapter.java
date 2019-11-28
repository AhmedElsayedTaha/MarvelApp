package com.marvelapp.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.marvelapp.Models.Result;
import com.marvelapp.R;

import java.util.List;

public class MarvelAdapter extends RecyclerView.Adapter<MarvelAdapter.MyViewHolder> {
    private Context context;
    private List<Result> resultList;
    private showDetailsListner showDetailsListner;

    public interface showDetailsListner{
        void showDetails(int position);
    }

    public MarvelAdapter(Context context, List<Result> resultList,showDetailsListner showDetailsListner) {
        this.context = context;
        this.resultList = resultList;
        this.showDetailsListner=showDetailsListner;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.avengers_layout,viewGroup,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder viewHolder, int i) {
        Result result = resultList.get(i);
        String url = result.getThumbnail().getPath()+"."+result.getThumbnail().getExtension();
        Glide.with(context)
                .load(url)
                .apply(new RequestOptions()
                        .placeholder(R.drawable.placeholder))
                .into(viewHolder.avengersImage);

        viewHolder.avengersImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDetailsListner.showDetails(viewHolder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return resultList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView avengersImage;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            avengersImage = itemView.findViewById(R.id.image);
        }
    }
}
