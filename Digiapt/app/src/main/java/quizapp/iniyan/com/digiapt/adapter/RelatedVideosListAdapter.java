package quizapp.iniyan.com.digiapt.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import java.util.List;

import javax.inject.Inject;

import quizapp.iniyan.com.digiapt.R;
import quizapp.iniyan.com.digiapt.model.VideoListPojo;
import quizapp.iniyan.com.digiapt.viewHolder.VideoRelatedListViewHolder;

public class RelatedVideosListAdapter extends RecyclerView.Adapter<VideoRelatedListViewHolder> {

    private List<VideoListPojo> videoListPojos;

    private AdapterCallback mAdapterCallback;

    Context context;

    public RelatedVideosListAdapter(List<VideoListPojo> videoListPojos, Context context) {
        this.videoListPojos = videoListPojos;
        this.context = context;

    }

    @Inject
    public RelatedVideosListAdapter() {

    }

    public RelatedVideosListAdapter(Context context) {

        this.mAdapterCallback = ((AdapterCallback) context);

    }

    @NonNull
    @Override
    public VideoRelatedListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new VideoRelatedListViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.related_video_row_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull VideoRelatedListViewHolder videoRelatedListViewHolder, final  int i) {

        videoRelatedListViewHolder.tvTitle.setText(videoListPojos.get(i).getTitle());
        videoRelatedListViewHolder.tvDescription.setText(videoListPojos.get(i).getDescription());

        final Context context = videoRelatedListViewHolder.imgVideo.getContext();

        Glide.with(context).load(videoListPojos.get(i).getThumb()).placeholder(android.R.drawable.stat_notify_error).into(videoRelatedListViewHolder.imgVideo);

        videoRelatedListViewHolder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(context instanceof AdapterCallback){
                    ((AdapterCallback)context).onVideoClickFromList(videoListPojos.get(i).getUrl(),videoListPojos.get(i).getTitle(),
                            videoListPojos.get(i).getDescription(),i);
                }

            }
        });
    }

    public interface AdapterCallback  {
        void onVideoClickFromList(String url,  String title, String description,int position);
    }

    @Override
    public int getItemCount() {
        return videoListPojos.size();
    }
}

