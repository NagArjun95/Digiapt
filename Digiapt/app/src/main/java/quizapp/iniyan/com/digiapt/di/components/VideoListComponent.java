package quizapp.iniyan.com.digiapt.di.components;

import dagger.Component;
import quizapp.iniyan.com.digiapt.activities.VideoListActivity;
import quizapp.iniyan.com.digiapt.di.modules.VideoListModule;

@Component(modules = VideoListModule.class)
public interface VideoListComponent {
    void inject(VideoListActivity videoListActivity);
}
