package quizapp.iniyan.com.digiapt.presenter;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import quizapp.iniyan.com.digiapt.Utils.RxDisposables;
import quizapp.iniyan.com.digiapt.contracts.VideoListContract;
import quizapp.iniyan.com.digiapt.model.VideoListPojo;
import quizapp.iniyan.com.digiapt.network.RetrofitClient;

public class VideoViewPresenter extends RxDisposables<VideoListContract.view> implements VideoListContract.presenter {

    private VideoListContract.view view;

     @Inject
    RetrofitClient retrofitClient;

    @Inject
    public VideoViewPresenter(VideoListContract.view view) {
        super(view);
        this.view = view;
    }

    @Override
    public void loadVideosList() {

        addCompositeDisposable(retrofitClient.getApiInterface().getVideoList("pretty")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
               .subscribe(new Consumer<retrofit2.Response<List<VideoListPojo>>>() {
                   @Override
                   public void accept(retrofit2.Response<List<VideoListPojo>> videoListPojoResponse) throws Exception {
                       if(videoListPojoResponse.isSuccessful()){
                           if(videoListPojoResponse.body()!=null)
                               view.onVideoListLoaded(videoListPojoResponse.body());
                       }

                   }
               }, new Consumer<Throwable>() {
                   @Override
                   public void accept(Throwable throwable) throws Exception {
                       if (throwable instanceof IOException) {
                           view.showMessage("Check Your Internet Conncetion!");
                       } else if (throwable instanceof IllegalStateException) {
                           view.showMessage("Couldn\\'t fetch data from server.");
                       } else {
                           view.showMessage("Server Error! Please Try again Later!.");
                       }
                   }
               }));
    }
}
