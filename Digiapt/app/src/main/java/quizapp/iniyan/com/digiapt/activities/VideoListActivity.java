package quizapp.iniyan.com.digiapt.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

import java.util.List;

import javax.inject.Inject;

import quizapp.iniyan.com.digiapt.R;
import quizapp.iniyan.com.digiapt.adapter.VideoListAdapter;
import quizapp.iniyan.com.digiapt.contracts.VideoListContract;
import quizapp.iniyan.com.digiapt.di.components.DaggerVideoListComponent;
import quizapp.iniyan.com.digiapt.di.modules.ActivityModule;
import quizapp.iniyan.com.digiapt.di.modules.VideoListModule;
import quizapp.iniyan.com.digiapt.model.VideoListPojo;
import quizapp.iniyan.com.digiapt.presenter.VideoViewPresenter;

public class VideoListActivity extends AppCompatActivity implements VideoListContract.view {

    String TAG = VideoListActivity.class.getSimpleName();
    public static final String GOOGLE_ACCOUNT = "google_account";
    private TextView profileName, profileEmail;

    @Inject
    VideoViewPresenter videoViewPresenter;

    @Inject
    RecyclerView.LayoutManager layoutManager;

    private RecyclerView rv_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_list);

        DaggerVideoListComponent.builder().videoListModule(new VideoListModule(this))
                .activityModule(new ActivityModule(this)).build().inject(this);


       init();

        setDataOnView();

        videoViewPresenter.loadVideosList();
    }

    private void init(){

        profileName = findViewById(R.id.profile_text);
        profileEmail = findViewById(R.id.profile_email);

        rv_list = findViewById(R.id.recyclerVideoList);
        rv_list.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        rv_list.setLayoutManager(layoutManager);

    }

    private void setDataOnView() {
        GoogleSignInAccount googleSignInAccount = getIntent().getParcelableExtra(GOOGLE_ACCOUNT);
        profileName.setText(googleSignInAccount.getDisplayName());
        profileEmail.setText(googleSignInAccount.getEmail());
    }

    @Override
    public void showMessage(String msg) {

        Toast.makeText(this, "" + msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onVideoListLoaded(List<VideoListPojo> videoListPojo) {

        VideoListAdapter videoListAdapter = new VideoListAdapter(videoListPojo, getApplicationContext());
        rv_list.setAdapter(videoListAdapter);
        videoListAdapter.notifyDataSetChanged();

    }
}
