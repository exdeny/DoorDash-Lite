package com.dgoliy.doordashlite.dagger;

import com.dgoliy.doordashlite.common.dagger.RestaurantDetailsScope;
import com.dgoliy.doordashlite.ui.RestaurantDetailsActivity;

import dagger.Subcomponent;

/**
 * Created by dgoliy on 2/11/18.
 */

@RestaurantDetailsScope
@Subcomponent(
        modules = {
                RestaurantDetailsModule.class
        })
public interface RestaurantDetailsSubComponent {
    void inject(RestaurantDetailsActivity activity);
}
