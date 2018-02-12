package com.dgoliy.doordashlite.data.dagger;

import com.dgoliy.doordashlite.common.DDLog;

import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by dgoliy on 2/11/18.
 */

public class DebugDataModule extends DataModule {
    @Override
    protected HttpLoggingInterceptor createLoggingInterceptor() {
        return new HttpLoggingInterceptor(message -> DDLog.d("NetworkClient", message)).setLevel(HttpLoggingInterceptor.Level.BASIC);
    }
}
