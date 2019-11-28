package com.marvelapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.SearchView;
import android.widget.Toast;

import com.marvelapp.Adapters.MarvelAdapter;
import com.marvelapp.AppConsts.AppConst;
import com.marvelapp.AppUtlis.Utlies;
import com.marvelapp.Interfaces.MarvelInterface;
import com.marvelapp.Models.Marvel;
import com.marvelapp.Models.Result;
import com.marvelapp.MvpImplementation.MarvelInteractorImp;
import com.marvelapp.MvpImplementation.MarvelPresenterImp;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MainActivity extends AppCompatActivity implements MarvelInterface.MarvelView {

    MarvelInterface.MarvelPresenter presenter;
    MarvelAdapter adapter;
    @BindView(R.id.myRec)
    RecyclerView recyclerView;
    ProgressDialog progressDialog;
    Unbinder unbinder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        unbinder = ButterKnife.bind(this);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading..");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setCancelable(false);
        progressDialog.show();

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        presenter = new MarvelPresenterImp(new MarvelInteractorImp(),this);
        if(Utlies.isOnline(this))
        presenter.requestMarvelData(AppConst.TS,AppConst.API_KEY,AppConst.HASH_KEY);
    }

    @Override
    public void dataFetchedSuccessfully(final List<Result> results) {
        if(results!=null&&results.size()>0){
            adapter = new MarvelAdapter(MainActivity.this, results, new MarvelAdapter.showDetailsListner() {
                @Override
                public void showDetails(int position) {
                    progressDialog.show();
                    presenter.requestDetails(results.get(position).getId(),AppConst.TS,AppConst.API_KEY,AppConst.HASH_KEY);
                }
            });
            recyclerView.setAdapter(adapter);
        }
        progressDialog.dismiss();
    }

    @Override
    public void failedToGetMarvelObject(Throwable t) {
        Toast.makeText(MainActivity.this,"something went wrong",Toast.LENGTH_LONG).show();
        Log.e("error","error is "+t.getMessage());
        progressDialog.dismiss();
    }

    @Override
    public void detailsDataFetchedDetails(List<Result> resultList) {
        if(resultList!=null&&resultList.size()>0){
            Intent intent = new Intent(MainActivity.this,AvengersDetails.class);
            intent.putExtra("object",resultList.get(0));
            startActivity(intent);
        }
        progressDialog.dismiss();
    }

    @Override
    public void failedToGetDetails(Throwable t) {
        Toast.makeText(MainActivity.this,"something went wrong",Toast.LENGTH_LONG).show();
        Log.e("error","error is "+t.getMessage());
        progressDialog.dismiss();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
