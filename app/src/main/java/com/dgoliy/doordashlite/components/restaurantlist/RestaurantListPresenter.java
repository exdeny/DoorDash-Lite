package com.dgoliy.doordashlite.components.restaurantlist;

import com.dgoliy.doordashlite.common.BaseContract;

/**
 * Created by dgoliy on 2/11/18.
 */

public interface RestaurantListPresenter extends BaseContract.BasePresenter<RestaurantListView> {
    void setup();

    void refresh();
}
