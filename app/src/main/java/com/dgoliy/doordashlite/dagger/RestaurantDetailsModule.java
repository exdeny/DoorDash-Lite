package com.dgoliy.doordashlite.dagger;

import com.dgoliy.doordashlite.common.schedulers.RxScheduler;
import com.dgoliy.doordashlite.common.dagger.RestaurantDetailsScope;
import com.dgoliy.doordashlite.components.restaurantdetails.RestaurantDetailsPresenter;
import com.dgoliy.doordashlite.components.restaurantdetails.RestaurantDetailsPresenterImpl;
import com.dgoliy.doordashlite.data.DataManager;

import dagger.Module;
import dagger.Provides;

/**
 * Created by dgoliy on 2/11/18.
 */

@Module
public class RestaurantDetailsModule {
    @Provides
    @RestaurantDetailsScope
    public RestaurantDetailsPresenter providePresenter(RxScheduler rxScheduler, DataManager dataManager) {
        return new RestaurantDetailsPresenterImpl(rxScheduler, dataManager);
    }
}
