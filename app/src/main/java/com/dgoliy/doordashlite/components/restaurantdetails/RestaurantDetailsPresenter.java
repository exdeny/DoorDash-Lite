package com.dgoliy.doordashlite.components.restaurantdetails;

import com.dgoliy.doordashlite.common.BaseContract;

/**
 * Created by dgoliy on 2/11/18.
 */

public interface RestaurantDetailsPresenter extends BaseContract.BasePresenter<RestaurantDetailsView> {
    void setup(int restaurantId);
}
