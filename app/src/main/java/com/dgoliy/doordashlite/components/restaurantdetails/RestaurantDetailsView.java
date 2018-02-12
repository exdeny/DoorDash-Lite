package com.dgoliy.doordashlite.components.restaurantdetails;

import com.dgoliy.doordashlite.common.BaseContract;
import com.dgoliy.doordashlite.data.remote.model.Restaurant;

/**
 * Created by dgoliy on 2/11/18.
 */

public interface RestaurantDetailsView extends BaseContract.BaseView {
    void showError(String message);

    void updateUI(boolean isLoading, Restaurant restaurant);
}
