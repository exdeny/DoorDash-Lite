package com.dgoliy.doordashlite;

import android.app.Application;

import com.dgoliy.doordashlite.common.DDLog;
import com.dgoliy.doordashlite.dagger.AppComponent;
import com.dgoliy.doordashlite.dagger.AppModule;
import com.dgoliy.doordashlite.dagger.DaggerAppComponent;
import com.dgoliy.doordashlite.dagger.ImageModule;
import com.dgoliy.doordashlite.dagger.MainActivityModule;
import com.dgoliy.doordashlite.dagger.RestaurantDetailsModule;
import com.dgoliy.doordashlite.dagger.RestaurantDetailsSubComponent;
import com.dgoliy.doordashlite.data.dagger.DataModule;

/**
 * Created by dgoliy on 2/11/18.
 */

public class DDLiteApplication extends Application {
    private final String TAG = "DDLiteApplication";

    private static DDLiteApplication instance;

    private AppComponent appComponent;
    private RestaurantDetailsSubComponent restaurantDetailsSubComponent;

    public static DDLiteApplication get() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        DDLog.d(TAG, "onCreate()");

        instance = this;

        appComponent = DaggerAppComponent
                .builder()
                .appModule(createAppModule())
                .dataModule(createDataModule())
                .mainActivityModule(new MainActivityModule())
                .imageModule(new ImageModule())
                .build();
    }

    protected DataModule createDataModule() {
        return new DataModule();
    }

    protected AppModule createAppModule() {
        return new AppModule(this);
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

    public RestaurantDetailsSubComponent getRestaurantDetailsSubComponent() {
        if (restaurantDetailsSubComponent == null)
            createRestaurantDetailsSubComponent();

        return restaurantDetailsSubComponent;
    }

    public RestaurantDetailsSubComponent createRestaurantDetailsSubComponent() {
        restaurantDetailsSubComponent = appComponent.plus(new RestaurantDetailsModule());
        return restaurantDetailsSubComponent;
    }

    public void releaseRestaurantDetailsSubComponent() {
        restaurantDetailsSubComponent = null;
    }
}
