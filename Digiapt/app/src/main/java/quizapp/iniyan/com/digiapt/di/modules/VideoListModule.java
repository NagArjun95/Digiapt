package quizapp.iniyan.com.digiapt.di.modules;

import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import dagger.Module;
import dagger.Provides;
import quizapp.iniyan.com.digiapt.adapter.VideoListAdapter;
import quizapp.iniyan.com.digiapt.contracts.VideoListContract;
import quizapp.iniyan.com.digiapt.network.RetrofitClient;

@Module(includes = ActivityModule.class)
public class VideoListModule {

    private VideoListContract.view view;

    public VideoListModule(VideoListContract.view view) {
        this.view = view;
    }

    @Provides
    public VideoListContract.view provideRestaurantPresenterView(){
        return view;
    }

    @Provides
    public RetrofitClient provideRetrofitClient(){
        return new RetrofitClient();
    }

    @Provides
    public VideoListAdapter provideVideoListAdapter(){
        return new VideoListAdapter();
    }

    @Provides
    public RecyclerView.LayoutManager provideLayoutManager(Activity activity){
        return new LinearLayoutManager(activity);
    }
}
