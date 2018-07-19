package com.alexperezbuildthatapp.ideatreejackson;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String URL_DATA = "https://itunes.apple.com/search?term=Michael+jackson";

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    private List<MusicList> listItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*Recycler View*/
        recyclerView = findViewById(R.id.music_tracklist);
        recyclerView.setHasFixedSize(true); //Every item in the recyclerview has a fixed size
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        listItems = new ArrayList<>();

        loadRecyclerViewData();
    }

    private void loadRecyclerViewData(){
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading TrackList Data...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                URL_DATA,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray array = jsonObject.getJSONArray("results");

                            for(int i = 0; i < array.length(); i++){
                                JSONObject o = array.getJSONObject(i);


                                MusicList item = new MusicList(
                                        o.getString("trackName"),
                                        o.getString("previewUrl"),
                                        o.getString("artistName"),
                                        o.getString("artworkUrl100"),
                                        o.getString("trackTimeMillis")
                                );
                                Log.d("Json_Music_Titles: ", o.getString("trackName"));
                                listItems.add(item);
                            }

                            adapter = new MusicListAdapter(listItems,getApplicationContext());
                            recyclerView.setAdapter(adapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //If an error would happen, a toast will state the error (Saving App From Crashing)
                        progressDialog.dismiss();
                        Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_LONG).show();
                        /*StyleableToast.makeText(getApplicationContext(),error.getMessage(),R.style.Lost_Toast).show();*/
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
