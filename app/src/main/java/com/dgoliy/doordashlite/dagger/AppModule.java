package com.dgoliy.doordashlite.dagger;

import android.app.Application;

import com.dgoliy.doordashlite.common.schedulers.DDRxScheduler;
import com.dgoliy.doordashlite.common.schedulers.RxScheduler;
import com.dgoliy.doordashlite.common.dagger.ApplicationScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by dgoliy on 2/11/18.
 */
@Module
public class AppModule {
    private Application mApplication;

    public AppModule(Application application) {
        mApplication = application;
    }

    @Provides
    @ApplicationScope
    public Application provideApplication() {
        return mApplication;
    }

    @Provides
    @ApplicationScope
    public RxScheduler provideRxScheduler() {
        return createScheduler();
    }

    protected RxScheduler createScheduler() {
        return new DDRxScheduler();
    }
}
