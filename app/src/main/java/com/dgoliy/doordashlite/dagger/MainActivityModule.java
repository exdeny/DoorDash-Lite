package com.dgoliy.doordashlite.dagger;

import com.dgoliy.doordashlite.common.schedulers.RxScheduler;
import com.dgoliy.doordashlite.common.dagger.ApplicationScope;
import com.dgoliy.doordashlite.components.restaurantlist.RestaurantListPresenter;
import com.dgoliy.doordashlite.components.restaurantlist.RestaurantListPresenterImpl;
import com.dgoliy.doordashlite.data.DataManager;

import dagger.Module;
import dagger.Provides;

/**
 * Created by dgoliy on 2/11/18.
 */

@Module
public class MainActivityModule {
    @Provides
    @ApplicationScope
    public RestaurantListPresenter providePresenter(RxScheduler rxScheduler, DataManager dataManager) {
        return new RestaurantListPresenterImpl(rxScheduler, dataManager);
    }
}
