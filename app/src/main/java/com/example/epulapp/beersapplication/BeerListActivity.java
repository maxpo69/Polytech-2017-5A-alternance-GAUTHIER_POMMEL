package com.example.epulapp.beersapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;


import com.example.epulapp.beersapplication.Interface.OnPreTaskExecute;
import com.example.epulapp.beersapplication.Interface.OnTaskCompleted;
import com.example.epulapp.beersapplication.Model.Beer;
import com.example.epulapp.beersapplication.dummy.DummyContent;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * An activity representing a list of Beers. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link BeerDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
public class BeerListActivity extends AppCompatActivity {


    private static final String BASE_URL = "https://api.punkapi.com/v2/";

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beer_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        if (findViewById(R.id.beer_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;
        }
        if(!DummyContent.LOADED){
            Retrofit retrofit= new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            BiereAPI beerAPI = retrofit.create(BiereAPI.class);
            Call<ArrayList<Beer>> call = beerAPI.getBeers();

            call.enqueue(new Callback<ArrayList<Beer>>() {
                @Override
                public void onResponse(Call<ArrayList<Beer>> call, Response<ArrayList<Beer>> response) {
                    Log.d("response : ",response.toString());
                    try {
                        ArrayList<Beer> beers = response.body();
                        // Add some sample items.
                        for (Beer biere: beers) {
                            Log.d("Biere : ", biere.toString());
                            DummyContent.ITEMS.add(biere);
                            DummyContent.ITEM_MAP.put(String.valueOf(biere.getId()), biere);

                        }

                        ProgressBar loading = findViewById(R.id.loading);
                        loading.setVisibility(View.INVISIBLE);
                        View recyclerView = findViewById(R.id.beer_list);
                        assert recyclerView != null;
                        setupRecyclerView((RecyclerView) recyclerView);

                        DummyContent.LOADED = true;
                    } catch (Error e){
                        Log.e("E : ","error "+e);
                    }
                }

                @Override
                public void onFailure(Call<ArrayList<Beer>> call, Throwable t) {
                    Log.e("E : ","error "+t.getMessage());
                }
            });
        }
        else{
            ProgressBar loading = findViewById(R.id.loading);
            loading.setVisibility(View.INVISIBLE);
            View recyclerView = findViewById(R.id.beer_list);
            assert recyclerView != null;
            setupRecyclerView((RecyclerView) recyclerView);
        }


    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        recyclerView.setAdapter(new SimpleItemRecyclerViewAdapter(this, DummyContent.ITEMS, mTwoPane));
    }


    public class SimpleItemRecyclerViewAdapter
            extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {

        private final BeerListActivity mParentActivity;
        private final List<Beer> mValues;
        private final boolean mTwoPane;
        private final View.OnClickListener mOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Beer item = (Beer) view.getTag();
                if (mTwoPane) {
                    Bundle arguments = new Bundle();
                    arguments.putString(BeerDetailFragment.ARG_ITEM_ID, String.valueOf(item.getId()));
                    BeerDetailFragment fragment = new BeerDetailFragment();
                    fragment.setArguments(arguments);
                    mParentActivity.getSupportFragmentManager().beginTransaction()
                            .replace(R.id.beer_detail_container, fragment)
                            .commit();
                } else {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, BeerDetailActivity.class);
                    intent.putExtra(BeerDetailFragment.ARG_ITEM_ID, String.valueOf(item.getId()));

                    context.startActivity(intent);
                }
            }
        };

        SimpleItemRecyclerViewAdapter(BeerListActivity parent,
                                      List<Beer> items,
                                      boolean twoPane) {
            mValues = items;
            mParentActivity = parent;
            mTwoPane = twoPane;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.beer_list_content, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, final int position) {
            //  holder.mImageView.setSou
            holder.mNameView.setText(mValues.get(position).getName());
            holder.mDegreView.setText("Alc. "+mValues.get(position).getAbv() +"%");
            holder.itemView.setTag(mValues.get(position));
            holder.itemView.setOnClickListener(mOnClickListener);
            if(mValues.get(position).getImg()!=null){
                holder.mLoadImageView.setVisibility(View.INVISIBLE);
                holder.mImageView.setVisibility(View.VISIBLE);
                holder.mImageView.setImageBitmap(mValues.get(position).getImg());
            }
            else{
                if(!mValues.get(position).getDownloadStarted()){
                    DownloadImage di = new DownloadImage(new OnPreTaskExecute(){
                        @Override
                        public void onPreTaskExecute() {
                            mValues.get(position).setDownloadStarted(true);
                            holder.mImageView.setVisibility(View.INVISIBLE);
                            holder.mLoadImageView.setVisibility(View.VISIBLE);
                        }
                    },new OnTaskCompleted(){

                        @Override
                        public void onTaskCompleted() {
                            holder.mLoadImageView.setVisibility(View.GONE);
                            holder.mImageView.setVisibility(View.VISIBLE);
                            holder.mImageView.setImageBitmap(mValues.get(position).getImg());
                        }
                    }, mValues.get(position));

                    di.execute(mValues.get(position).getImage_url());
                }
                else{
                    holder.mLoadImageView.setVisibility(View.VISIBLE);
                    holder.mImageView.setVisibility(View.INVISIBLE);
                }
            }



        }

        @Override
        public int getItemCount() {
            return mValues.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            final TextView mNameView;
            final TextView mDegreView;
            final ImageView mImageView;
            final ProgressBar mLoadImageView;
            ViewHolder(View view) {
                super(view);
                mImageView = (ImageView) view.findViewById(R.id.image);
                mNameView = (TextView) view.findViewById(R.id.name);
                mDegreView = (TextView) view.findViewById(R.id.degre);
                mLoadImageView = (ProgressBar) view.findViewById(R.id.loadImg);
            }
        }
    }

}
