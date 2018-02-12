package com.dgoliy.doordashlite.data.dagger;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.dgoliy.doordashlite.common.dagger.ApplicationScope;
import com.dgoliy.doordashlite.data.BuildConfig;
import com.dgoliy.doordashlite.data.DataManager;
import com.dgoliy.doordashlite.data.remote.DoorDashAPI;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by dgoliy on 2/11/18.
 */

@Module
public class DataModule {
    @Provides
    @ApplicationScope
    public DataManager provideDataManager(DoorDashAPI api) {
        return new DataManager(api);
    }

    @Provides
    @Nullable
    @ApplicationScope
    public HttpLoggingInterceptor provideLoggingInterceptor() {
        return createLoggingInterceptor();
    }

    @Provides
    @NonNull
    @ApplicationScope
    public DoorDashAPI provideDoorDashAPI() {
        return createDoorDashAPI();
    }

    protected DoorDashAPI createDoorDashAPI() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(createLoggingInterceptor())
                .build();
        return new Retrofit.Builder()
                .baseUrl(BuildConfig.API_ENDPOINT)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(DoorDashAPI.class);
    }

    protected HttpLoggingInterceptor createLoggingInterceptor() {
        // COMMENT:
        // we could forward these logs into a crash reporting tool for example,
        // so that we have a better idea of the causes that led to an app crash.
        return null;
    }
}
