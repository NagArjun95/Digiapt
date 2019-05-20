package quizapp.iniyan.com.digiapt.network;


import java.util.List;

import io.reactivex.Observable;
import quizapp.iniyan.com.digiapt.model.VideoListPojo;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitInterface {
    @GET("/media.json")
    Observable<Response<List<VideoListPojo>>> getVideoList(@Query("print") String print);

}
