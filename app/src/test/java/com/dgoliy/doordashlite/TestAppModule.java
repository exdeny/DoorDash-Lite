package com.dgoliy.doordashlite;

import android.app.Application;

import com.dgoliy.doordashlite.common.schedulers.RxScheduler;
import com.dgoliy.doordashlite.common.schedulers.TestRxScheduler;
import com.dgoliy.doordashlite.dagger.AppModule;

/**
 * Created by dgoliy on 2/11/18.
 */

public class TestAppModule extends AppModule {
    public TestAppModule(Application application) {
        super(application);
    }

    @Override
    protected RxScheduler createScheduler() {
        return new TestRxScheduler();
    }
}
