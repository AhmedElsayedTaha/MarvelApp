package com.marvelapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.marvelapp.Models.Result;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AvengersDetails extends AppCompatActivity {

    //Hello new branch
    @BindView(R.id.img)
    ImageView img;
    @BindView(R.id.name)
    TextView nameTv;

    Result result = new Result();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avengers_details);
        ButterKnife.bind(this);

        result = (Result) getIntent().getSerializableExtra("object");
        if(result!=null){
            String url = result.getThumbnail().getPath()+"."+result.getThumbnail().getExtension();
            Glide.with(this)
                    .load(url)
                    .apply(new RequestOptions()
                            .placeholder(R.drawable.placeholder))
                    .into(img);

            nameTv.setText(result.getName());
        }
    }
}
