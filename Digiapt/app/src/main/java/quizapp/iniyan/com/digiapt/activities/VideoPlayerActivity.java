package quizapp.iniyan.com.digiapt.activities;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import java.util.ArrayList;

import quizapp.iniyan.com.digiapt.R;
import quizapp.iniyan.com.digiapt.adapter.RelatedVideosListAdapter;
import quizapp.iniyan.com.digiapt.model.VideoListPojo;

public class VideoPlayerActivity extends AppCompatActivity implements RelatedVideosListAdapter.AdapterCallback {

    private String url, title, description;
    private VideoView simpleVideoView;
    private MediaController mediaControls;

    private RecyclerView rvRelatedVideosList;
    RecyclerView.LayoutManager layoutManager;

    private ArrayList<VideoListPojo> videoListPojos;

    private TextView tvTitle, tvDescription;

    private int position=0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        init();

        if (getIntent() != null) {

            videoListPojos = getIntent().getParcelableArrayListExtra("list");

            url = getIntent().getStringExtra("videourl");
            title = getIntent().getStringExtra("title");
            description = getIntent().getStringExtra("description");
            position = getIntent().getIntExtra("position",0);
        }

        if (mediaControls == null) {
            // create an object of media controller class
            mediaControls = new MediaController(VideoPlayerActivity.this);
            mediaControls.setAnchorView(simpleVideoView);

        }

        simpleVideoView.setMediaController(mediaControls);
        Uri uri = Uri.parse(url);
        mediaControls.requestFocus();
        simpleVideoView.setVideoURI(uri);
        simpleVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {

            @Override
            public void onPrepared(MediaPlayer mp) {
                mediaControls.show(0);
            }
        });
        simpleVideoView.pause();

        tvTitle.setText(title);
        tvDescription.setText(description);

        new RelatedVideosListAdapter(this);

        RelatedVideosListAdapter relatedVideosListAdapter = new RelatedVideosListAdapter(videoListPojos, getApplicationContext());
        rvRelatedVideosList.setAdapter(relatedVideosListAdapter);
        relatedVideosListAdapter.notifyDataSetChanged();

// implement on completion listener on video view
        simpleVideoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {


                  position++;

                if(videoListPojos.size()>position){

                    Uri uri = Uri.parse(videoListPojos.get(position).getUrl());

                    tvTitle.setText(videoListPojos.get(position).getTitle());
                    tvDescription.setText(videoListPojos.get(position).getDescription());

                    simpleVideoView.setVideoURI(uri);
                    simpleVideoView.start();

                }else


                Toast.makeText(getApplicationContext(), "Thank You...!!!", Toast.LENGTH_LONG).show(); // display a toast when an video is completed
            }
        });

        simpleVideoView.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                Toast.makeText(getApplicationContext(), "Oops An Error Occur While Playing Video...!!!", Toast.LENGTH_LONG).show(); // display a toast when an error is occured while playing an video
                return false;
            }
        });
    }

    private void init(){

        tvTitle = findViewById(R.id.tv_title);
        tvDescription = findViewById(R.id.tv_description);

        rvRelatedVideosList = findViewById(R.id.recyclerView);
        rvRelatedVideosList.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        rvRelatedVideosList.setLayoutManager(layoutManager);

        simpleVideoView = findViewById(R.id.simpleVideoView); // initiate a video view

    }

    @Override
    public void onVideoClickFromList(String url, String title, String description, int position) {

        Uri uri = Uri.parse(url);

        tvTitle.setText(title);
        tvDescription.setText(description);

        simpleVideoView.setVideoURI(uri);
        simpleVideoView.start();

    }
}
