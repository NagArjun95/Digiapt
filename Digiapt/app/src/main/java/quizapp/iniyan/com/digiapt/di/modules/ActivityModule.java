package quizapp.iniyan.com.digiapt.di.modules;

import android.app.Activity;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {
    private Activity activity;

    public ActivityModule(Activity activity) {
        this.activity = activity;
    }

    @Provides
    public Activity povideActivity(){
        return activity;
    }
}
