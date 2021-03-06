package quizapp.iniyan.com.digiapt.viewHolder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import quizapp.iniyan.com.digiapt.R;

public class VideoRelatedListViewHolder extends RecyclerView.ViewHolder {

    public TextView tvTitle,tvDescription;
    public ImageView imgVideo;
    public LinearLayout linearLayout;

    public VideoRelatedListViewHolder(@NonNull View itemView) {
        super(itemView);

        tvTitle = itemView.findViewById(R.id.tv_title);
        tvDescription = itemView.findViewById(R.id.tv_description);
        imgVideo = itemView.findViewById(R.id.image);

        linearLayout = itemView.findViewById(R.id.linearlayout);
    }
}
