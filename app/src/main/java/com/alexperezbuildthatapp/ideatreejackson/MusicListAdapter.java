package com.alexperezbuildthatapp.ideatreejackson;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MusicListAdapter extends RecyclerView.Adapter<MusicListAdapter.ViewHolder>{

    private List<MusicList> listItems;
    private Context context;

    public MusicListAdapter(List<MusicList> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }

    //Inlfate the activity with data from the parent context in which case is the tracklist xml activity
    @NonNull
    @Override
    public MusicListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.tracklist,parent,false);
        return new ViewHolder(v);
    }

    //Use to grab the data for the view holder to present
    @Override
    public void onBindViewHolder(MusicListAdapter.ViewHolder holder, int position) {
        final MusicList trackinfo = listItems.get(position);

        holder.track_Title.setText(trackinfo.getMusic_Titile());
        holder.track_artist.setText(trackinfo.getMusic_Artist());

        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"Play Me!",Toast.LENGTH_LONG).show();
                Intent homeActivity = new Intent(context,TrackInfo.class);
                homeActivity.putExtra("trackTitle",trackinfo.getMusic_Titile());
                homeActivity.putExtra("artistName",trackinfo.getMusic_Artist());
                homeActivity.putExtra("previewURL",trackinfo.getPreviewURL());
                homeActivity.putExtra("duration",trackinfo.getDuration());
                context.startActivity(homeActivity);
            }
        });

    }

    @Override
    public int getItemCount() {

        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView track_Title;
        public ImageView track_Preview;
        public TextView track_artist;
        public TextView track_Album_Cover;

        public ConstraintLayout constraintLayout;

        public ViewHolder(View itemView){
            super(itemView);

            track_Title = itemView.findViewById(R.id.textView_TrackTitle);
            track_Preview = itemView.findViewById(R.id.imageView_Play);
            track_artist = itemView.findViewById(R.id.textView_Artist);

            constraintLayout = itemView.findViewById(R.id.tracklistData);

        }

    }
}
