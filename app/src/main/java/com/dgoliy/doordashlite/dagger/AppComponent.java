package com.dgoliy.doordashlite.dagger;

import com.dgoliy.doordashlite.ui.MainActivity;
import com.dgoliy.doordashlite.common.dagger.ApplicationScope;
import com.dgoliy.doordashlite.data.dagger.DataModule;
import com.dgoliy.doordashlite.ui.RestaurantListAdapter;

import dagger.Component;

/**
 * Created by dgoliy on 2/11/18.
 */

@ApplicationScope
@Component(
        modules = {
                AppModule.class, DataModule.class,
                ImageModule.class, MainActivityModule.class
        })
public interface AppComponent {
    void inject(MainActivity activity);

    void inject(RestaurantListAdapter adapter);

    RestaurantDetailsSubComponent plus(RestaurantDetailsModule module);
}
