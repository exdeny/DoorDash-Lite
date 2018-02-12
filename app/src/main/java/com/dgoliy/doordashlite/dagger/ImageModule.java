package com.dgoliy.doordashlite.dagger;

import android.app.Application;

import com.dgoliy.doordashlite.DDGlide;
import com.dgoliy.doordashlite.GlideRequests;
import com.dgoliy.doordashlite.common.dagger.ApplicationScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by dgoliy on 2/11/18.
 */
@Module
public class ImageModule {
    @Provides
    @ApplicationScope
    public GlideRequests provideGlide(Application application) {
        return DDGlide.with(application);
    }
}
