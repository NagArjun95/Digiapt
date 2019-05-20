package quizapp.iniyan.com.digiapt.contracts;

import java.util.List;

import quizapp.iniyan.com.digiapt.model.VideoListPojo;

public interface VideoListContract {

    interface view{

        void showMessage(String msg);
        void onVideoListLoaded(List<VideoListPojo> videoListPojo);
    }
    interface presenter{

        void loadVideosList();
    }
}
