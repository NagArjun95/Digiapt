package quizapp.iniyan.com.digiapt.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import quizapp.iniyan.com.digiapt.R;
import quizapp.iniyan.com.digiapt.activities.VideoPlayerActivity;
import quizapp.iniyan.com.digiapt.model.VideoListPojo;
import quizapp.iniyan.com.digiapt.viewHolder.VideoListViewHolder;

public class VideoListAdapter extends RecyclerView.Adapter<VideoListViewHolder> {

    private List<VideoListPojo> videoListPojos;

    private Context context;

    public VideoListAdapter(List<VideoListPojo> videoListPojos, Context context) {
        this.videoListPojos = videoListPojos;
        this.context = context;
    }

    @Inject
    public VideoListAdapter() {

    }

    @NonNull
    @Override
    public VideoListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new VideoListViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.video_row_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull VideoListViewHolder videoListViewHolder, final  int i) {
        videoListViewHolder.tvTitle.setText(videoListPojos.get(i).getTitle());
        videoListViewHolder.tvDescription.setText(videoListPojos.get(i).getDescription());

        final Context context = videoListViewHolder.imgVideo.getContext();

        Glide.with(context).load(videoListPojos.get(i).getThumb()).placeholder(android.R.drawable.stat_notify_error).into(videoListViewHolder.imgVideo);

        videoListViewHolder.imgVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, VideoPlayerActivity.class);
                ArrayList<VideoListPojo> listofvalues = new ArrayList<>();
                listofvalues.addAll(videoListPojos);
                listofvalues.remove(i);
                intent.putParcelableArrayListExtra("list", listofvalues);
                intent.putExtra("videourl", videoListPojos.get(i).getUrl());
                intent.putExtra("title", videoListPojos.get(i).getTitle());
                intent.putExtra("description", videoListPojos.get(i).getDescription());
                intent.putExtra("position",i);

                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return videoListPojos.size();
    }
}
